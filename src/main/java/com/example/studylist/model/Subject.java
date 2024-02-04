package com.example.studylist.model;

import com.example.studylist.dto.SubjectDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_SUBJECTS_TEACHER"))
    private User teacher;

    public static Subject toEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setTitle(dto.getTitle());
        subject.setDescription("Description");
        return subject;
    }
}
