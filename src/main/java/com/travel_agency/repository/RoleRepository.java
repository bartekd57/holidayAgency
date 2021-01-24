package com.travel_agency.repository;

import com.travel_agency.model.user.Role;
import com.travel_agency.security.DTO.UserRoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoleNameEnum roleName);


}
