package com.travel_agency.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.model.address.Address;
import com.travel_agency.model.reservation.Reservation;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.security.DTO.UserRoleNameEnum;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@EqualsAndHashCode
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=true)
    @Size(min=1)
    private String firstName;

    @Column(nullable=true)
    @Size(min=1)
    private String lastName;

    @Column(nullable = true)
    @Size(min=1)
    private String userName;

    @Column(nullable = false)
    @Email
    private String email;

    private String password;
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private UserRoleNameEnum roleName;

    @ManyToMany
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            })
    @JoinTable(name = "user_trip",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id"))
    @JsonIgnore
    private List<Trip> trips = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @Column(nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> reservations = new ArrayList<>();

//    @OneToOne(targetEntity = Address.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = true)
    private String photos;

    private String getPhotosImagePath;


    public User(Long id, @Size(min = 1) String firstName, @Size(min = 1) String lastName, @Size(min = 1) String userName, @Email String email, String password, String confirmPassword, UserRoleNameEnum roleName, Set<Role> roles, List<Trip> trips, List<Reservation> reservations, Address address, String photos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roleName = roleName;
        this.roles = roles;
        this.trips = trips;
        this.reservations = reservations;
        this.address = address;
        this.photos = photos;
    }

    public User(@Size(min = 1) String firstName, @Size(min = 1) String lastName, @Size(min = 1) String userName, @Email String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User() {
    }

    public User(@Size(min = 1) String userName, @Email String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserRoleNameEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoleNameEnum roleName) {
        this.roleName = roleName;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }
}
