package com.travel_agency.model.user;

import com.travel_agency.security.DTO.UserRoleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleNameEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRoleNameEnum getName() {
        return name;
    }

    public void setName(UserRoleNameEnum name) {
        this.name = name;
    }
}
