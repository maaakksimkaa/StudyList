package com.example.studylist.model;

import javax.persistence.*;

import com.example.studylist.dto.GroupDTO;
import com.example.studylist.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(name = "FK_USER_ROLES")
    )
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "group_id",
            foreignKey = @ForeignKey(name = "FK_USER_GROUPS")
    )
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "subject_id",
            foreignKey = @ForeignKey(name = "FK_USER_SUBJECTS")
    )
    private Subject subject;

    @Column(name = "fio")
    private String FIO;

    @Column(name = "grades")
    private String grades;

    @Column(name = "attendance")
    private String attendance;

    @Column(name = "comments")
    private String comments;

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setFIO(dto.getFIO());
        user.setComments(dto.getComments());
        user.setAttendance(dto.getAttendance());
        user.setGrades(dto.getGrades());
        user.setGroup(Group.toEntity(dto.getGroup()));
        user.setSubject(Subject.toEntity(dto.getSubject()));
        user.setRole(Role.toEntity(dto.getRole()));
        return user;
    }
}
