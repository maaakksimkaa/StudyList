package com.example.studylist;

import com.example.studylist.dto.GroupDTO;
import com.example.studylist.model.Group;
import com.example.studylist.repository.GroupRepository;
import com.example.studylist.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAllGroupsTest() {
        Group group1 = new Group(1L, "2151","2151"); // Установите нужные значения
        Group group2 = new Group(1L, "2152","2152"); // Установите нужные значения
        when(groupRepository.findAll()).thenReturn(Arrays.asList(group1, group2));

        List<GroupDTO> groupDTOList = groupService.listAll();

        assertNotNull(groupDTOList);
        assertEquals(2, groupDTOList.size());
    }

    @Test
    void getOneGroupTest() {
        Group group = new Group(1L, "2152","2152"); // Установите нужные значения
        group.setId(1L);
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        GroupDTO groupDTO = groupService.getOne(1L);

        assertNotNull(groupDTO);
        assertEquals(1L, groupDTO.getId());
    }

    @Test
    void createGroupTest() {
        GroupDTO newGroupDTO = new GroupDTO(1L, "2152"); // Установите нужные значения
        Group group = Group.toEntity(newGroupDTO);
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        GroupDTO createdGroupDTO = groupService.create(newGroupDTO);

        assertNotNull(createdGroupDTO);
    }

    @Test
    void updateGroupTest() {
        GroupDTO updateGroupDTO = new GroupDTO(1L, "2152"); // Установите нужные значения
        Group group = Group.toEntity(updateGroupDTO);
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        GroupDTO updatedGroupDTO = groupService.update(updateGroupDTO);

        assertNotNull(updatedGroupDTO);
    }

    @Test
    void deleteGroupTest() {
        doNothing().when(groupRepository).deleteById(1L);

        assertDoesNotThrow(() -> groupService.delete(1L));
    }
}