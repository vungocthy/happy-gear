package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.Role;
import com.notimplement.happygear.model.dto.RoleDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.RoleRepository;
import com.notimplement.happygear.service.RoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.findByRoleId(id);
        if(role!=null){
            return Mapper.toRoleDto(role);
        }
        return null;
    }

    @Override
    public List<RoleDto> getAllRoleDto() {
       return roleRepository.findAll()
               .stream()
               .map(Mapper::toRoleDto)
               .collect(Collectors.toList());
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Integer id) {
        Role role = roleRepository.findByRoleId(id);
        if(role!=null){
            return Mapper.toRoleDto(role);
        }
        return null;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = toRole(roleDto);
        Role res = roleRepository.save(role);
        return Mapper.toRoleDto(res);
    }

    @Override
    public RoleDto removeRole(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return Mapper.toRoleDto(role.get());
        }
        return null;
    }

    private Role toRole(RoleDto roleDto){
        if(roleDto!=null){
            Role role = new Role();
            role.setRoleId(roleDto.getRoleId());
            role.setRoleName(roleDto.getRoleName());
            role.setStatus(roleDto.getStatus());
            return role;
        }
        return null;
    }
}
