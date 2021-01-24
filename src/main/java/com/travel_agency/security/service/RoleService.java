package com.travel_agency.security.service;

import com.travel_agency.model.user.Role;
import com.travel_agency.repository.RoleRepository;
import com.travel_agency.security.DTO.UserRoleNameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(UserRoleNameEnum roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
