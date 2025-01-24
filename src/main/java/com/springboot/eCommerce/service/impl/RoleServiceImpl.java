package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.RoleRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.RoleResponse;
import com.springboot.eCommerce.entity.Permission;
import com.springboot.eCommerce.entity.Role;
import com.springboot.eCommerce.repository.PermissionRepository;
import com.springboot.eCommerce.repository.RoleRepository;
import com.springboot.eCommerce.service.RoleServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleServiceInterface {
    PermissionRepository permissionRepository;
     RoleRepository roleRepository;
     ModelMapper modelMapper;

    @Override
    public ApiResponse<RoleResponse> create(RoleRequest request) {
        Role role = modelMapper.map(request, Role.class);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        Role savedRole = roleRepository.save(role);
        return new ApiResponse<>(modelMapper.map(role, RoleResponse.class));
    }

    @Override
    public ApiResponse<List<RoleResponse>> getAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> roleResponses = roles.stream().map(role -> modelMapper.map(role, RoleResponse.class)).toList();
        return new ApiResponse<>(roleResponses);
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }
}
