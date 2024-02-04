package com.example.studylist.dto;
import com.example.studylist.model.User;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {

    private Long id;
    private RoleDTO role;
    private String login;
    private String password;
    private GroupDTO group;
    private SubjectDTO subject;
    private String FIO;
    private String grades;
    private String attendance;
    private String comments;

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setFIO(user.getFIO());
        dto.setGrades(user.getGrades());
        dto.setAttendance(user.getAttendance());
        dto.setComments(user.getComments());
        dto.setGroup(GroupDTO.toDTO(user.getGroup()));
        dto.setSubject(SubjectDTO.toDTO(user.getSubject()));
        dto.setRole(RoleDTO.toDTO(user.getRole()));
        return dto;
    }
}
