package com.travel_agency.service;

import com.travel_agency.dto.ParticipantDTO;
import com.travel_agency.mapper.ParticipantMapper;
import com.travel_agency.model.participant.Participant;
import com.travel_agency.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantDTO getParticipantById(Long id) {
        return participantRepository.findById(id)
                .map(ParticipantMapper.INSTANCE::participantDto)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(ParticipantMapper.INSTANCE::participantDto)
                .collect(Collectors.toList());
    }

    public void saveParticipant(ParticipantDTO participantDTO){
        Participant participant = ParticipantMapper.INSTANCE.dtoToParticipant(participantDTO);
        participantRepository.save(participant);
    }

    public void deleteParticipantById(Long id){
        Participant participant = participantRepository.findById(id).orElseThrow(NoSuchElementException::new);
        participantRepository.delete(participant);
    }





}
