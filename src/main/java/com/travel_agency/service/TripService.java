package com.travel_agency.service;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.mapper.HotelMapper;
import com.travel_agency.mapper.TripMapper;
import com.travel_agency.model.destination.Destination;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.model.trip.TripAlimentationEnum;
import com.travel_agency.model.trip.TripStatusEnum;
import com.travel_agency.model.trip.TripTypeEnum;
import com.travel_agency.repository.DestinationRepository;
import com.travel_agency.repository.TripRepository;
import com.travel_agency.repository.UserRepository;
import com.travel_agency.weather_checker.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripService {

    TripRepository tripRepository;
    UserRepository userRepository;
    private Map<Long, Integer> map = new HashMap<>();
    WeatherDataService weatherDataService;
    DestinationRepository destinationRepository;

    @Autowired
    public TripService(TripRepository tripRepository, UserRepository userRepository,
                       WeatherDataService weatherDataService, DestinationRepository destinationRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.weatherDataService = weatherDataService;
        this.destinationRepository = destinationRepository;
    }

    public List<TripDTO> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDTO> result = new ArrayList<>();
        for (Trip trip : trips) {
            trip.getDestination().setWeatherTemplate(weatherDataService.getDataByCityName(trip.getDestination().getCity()));
            result.add(TripMapper.INSTANCE.tripToDto(trip));
        }
        return result;
    }

    public TripDTO getTripById(Long id) {

        setCounterValue(id);
        Optional<Trip> trip = tripRepository.findById(id);

        trip.orElse(null).getDestination().setWeatherTemplate(weatherDataService.getDataByCityName(trip.orElse(null).getDestination().getCity()));

        return trip.map(TripMapper.INSTANCE::tripToDto).orElse(null);
    }

    public List<TripDTO> getTripsForUser(Long userId) {
        List<Trip> trips = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("There is no trips for user with given id")).getTrips();
        List<TripDTO> result = new ArrayList<>();
        for (Trip trip : trips) {
            trip.getDestination().setWeatherTemplate(weatherDataService.getDataByCityName(trip.getDestination().getCity()));
            result.add(TripMapper.INSTANCE.tripToDto(trip));
        }
        return result;
    }

    private final String lock = new String("id");

    public void setCounterValue(Long id) {
        synchronized (lock) {
            System.out.println("locking on :" + lock);
            tripRepository.findById(id).ifPresent(trip -> {
                incrementCounter(trip);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putInMap(trip);
            });
        }
    }

    private void incrementCounter(Trip trip) {
        trip.setCounter(trip.getCounter() + 1);
        tripRepository.save(trip);
    }

    private void putInMap(Trip trip) {
        map.put(trip.getId(), trip.getCounter());
    }

    public Integer getCounterForTripWithId(Long id) {
        return tripRepository.findById(id).get().getCounter();
    }

    public List<TripDTO> getTripByTripType(TripTypeEnum tripTypeEnum) {
        return tripRepository.findAllByTripType(tripTypeEnum)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(TripMapper.INSTANCE::tripToDto)
                .collect(Collectors.toList());
    }

    public List<TripDTO> getTripByAlimentation(TripAlimentationEnum alimentation) {
        return tripRepository.findAllByTripAlimentationEnum(alimentation)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(TripMapper.INSTANCE::tripToDto)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelsForTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return trip.getHotels()
                .stream()
                .map(HotelMapper.INSTANCE::hotelDto)
                .collect(Collectors.toList());
    }

    public List<TripDTO> getTripBySearchBox(LocalDateTime dateFrom, LocalDateTime dateTo, String continent) {

        return tripRepository.findAllByDateFromAfterAndDateToBeforeAndDestination_Continent(dateFrom, dateTo, continent)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(TripMapper.INSTANCE::tripToDto)
                .collect(Collectors.toList());
    }

    public TripDTO createAndSaveNewTrip(LocalDateTime dateFrom, LocalDateTime dateTo, BigDecimal priceAdult, BigDecimal priceChild,
                                        TripTypeEnum type, TripAlimentationEnum alimentation, String description, Integer limit, String url,
                                        String continent, String country, String city, String airport) {

        Destination destination = new Destination();
        destination.setContinent(continent);
        destination.setCountry(country);
        destination.setCity(city);
        destination.setAirport(airport);
        destinationRepository.save(destination);

        Trip trip = new Trip();
        trip.setDateFrom(dateFrom);
        trip.setDateTo(dateTo);
        trip.setPriceForAdult(priceAdult);
        trip.setPriceForChild(priceChild);
        trip.setTripType(type);
        trip.setTripAlimentationEnum(alimentation);
        trip.setDescription(description);
        trip.setStatus(TripStatusEnum.ACTIVE);
        trip.setPeopleLimit(limit);
        trip.setDestination(destination);
        trip.setImgUrl(url);
        trip.setCounter(0);

        tripRepository.save(trip);

        return TripMapper.INSTANCE.tripToDto(trip);
    }

    public TripTypeEnum getTypeValueFromEnumName(String type) {

        return Arrays.stream(TripTypeEnum.values())
                .filter(a -> a.name().equals(type))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }

    public TripAlimentationEnum getAlimentationValueFromEnumName(String alimentation) {

        return Arrays.stream(TripAlimentationEnum.values())
                .filter(a -> a.name().equals(alimentation))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

    }


}
