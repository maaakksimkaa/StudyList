package com.example.studylist.dto;

import java.util.Set;

import com.example.studylist.model.Subject;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO {

    private Long id;
    private String title;

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setTitle(subject.getTitle());
        return dto;
    }

}
