package com.springboot.eCommerce.service.impl;

import com.springboot.eCommerce.dto.request.PermissionCreationRequest;
import com.springboot.eCommerce.dto.response.ApiResponse;
import com.springboot.eCommerce.dto.response.PermissionResponse;
import com.springboot.eCommerce.entity.Permission;
import com.springboot.eCommerce.repository.PermissionRepository;
import com.springboot.eCommerce.service.PermissionServiceInterface;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionServiceInterface {
     PermissionRepository permissionRepository;
     ModelMapper modelMapper;

    @Override
    public ApiResponse<PermissionResponse> create(PermissionCreationRequest request) {
        Permission permission = new Permission(request.getName(), request.getDescription());
        Permission permissionSaved = permissionRepository.save(permission);
        return new ApiResponse<>(modelMapper.map(permissionSaved, PermissionResponse.class));
    }

    @Override
    public ApiResponse<List<PermissionResponse>> getAll() {
        List<Permission> permissions = permissionRepository.findAll();

        List<PermissionResponse> permissionResponses = permissions.stream().map(
                permission -> modelMapper.map(permission, PermissionResponse.class)).toList();

        return new ApiResponse<>(permissionResponses);
    }

    @Override
    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }
}
