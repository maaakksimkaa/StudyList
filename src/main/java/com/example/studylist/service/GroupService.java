package com.example.studylist.service;

import com.example.studylist.dto.GroupDTO;
import com.example.studylist.model.Group;
import com.example.studylist.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupDTO> listAll() {
        return groupRepository.findAll().stream()
                .map(GroupDTO::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO getOne(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        return GroupDTO.toDTO(group);
    }

    public GroupDTO create(GroupDTO dto) {
        Group group = Group.toEntity(dto);
        return GroupDTO.toDTO(groupRepository.save(group));
    }

    public GroupDTO update(GroupDTO dto) {
        Group group = Group.toEntity(dto);
        return GroupDTO.toDTO(groupRepository.save(group));
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
