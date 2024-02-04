package com.example.studylist.service;

import com.example.studylist.dto.RoleDTO;
import com.example.studylist.model.Role;
import com.example.studylist.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<RoleDTO> listAll() {
        return repository.findAll().stream()
                .map(RoleDTO::toDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getOne(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        return RoleDTO.toDTO(role);
    }

    public RoleDTO create(RoleDTO dto) {
        Role role = Role.toEntity(dto);
        return RoleDTO.toDTO(repository.save(role));
    }

    public RoleDTO update(RoleDTO dto) {
        Role role = Role.toEntity(dto);
        return RoleDTO.toDTO(repository.save(role));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
