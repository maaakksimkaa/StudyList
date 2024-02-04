package com.example.studylist.model;

import com.example.studylist.dto.GroupDTO;
import com.example.studylist.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_GROUPS_TEACHER"))
    private User teacher;*/

    public static Group toEntity(GroupDTO dto) {
        Group group = new Group();
        group.setId(dto.getId());
        group.setTitle(dto.getTitle());
        return group;
    }
}
