package com.example.studylist.service;

import com.example.studylist.dto.UserDTO;
import com.example.studylist.model.Group;
import com.example.studylist.model.Role;
import com.example.studylist.model.Subject;
import com.example.studylist.model.User;
import com.example.studylist.repository.GroupRepository;
import com.example.studylist.repository.RoleRepository;
import com.example.studylist.repository.SubjectRepository;
import com.example.studylist.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<UserDTO> listAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getOne(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        return UserDTO.toDTO(user);
    }

    public UserDTO create(UserDTO dto) {
        User user = User.toEntity(dto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return UserDTO.toDTO(userRepository.save(user));
    }

    public UserDTO update(UserDTO dto) {
        User user = User.toEntity(dto);
        return UserDTO.toDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> updateAll(List<UserDTO> dtos) {
        List<User> usersToUpdate = dtos.stream()
                .map(dto -> {

                    User existingUser = userRepository.findById(dto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found: " + dto.getId()));
                    if (dto.getFIO() != null) existingUser.setFIO(dto.getFIO());
                    if (dto.getGrades() != null) existingUser.setGrades(dto.getGrades());
                    if (dto.getAttendance() != null)
                        existingUser.setAttendance(dto.getAttendance());
                    if (dto.getComments() != null) existingUser.setComments(dto.getComments());
                    return existingUser;
                })
                .collect(Collectors.toList());

        List<User> updatedUsers = userRepository.saveAll(usersToUpdate);

        return updatedUsers.stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findByRoleId(Long roleId) {
        return userRepository.findByRoleId(roleId).stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findStudentsByGroupId(Long groupId) {
        return userRepository.findStudentsByGroupId(groupId).stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findTeacherByGroupAndSubjectId(Long groupId, Long subjectId) {
        return userRepository.findTeacherByGroupAndSubjectId(groupId, subjectId)
                .map(UserDTO::toDTO);
    }

    public List<UserDTO> findStudentsByGroupAndSubjectId(Long groupId, Long subjectId) {
        return userRepository.findStudentsByGroupAndSubjectId(groupId, subjectId).stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

}
