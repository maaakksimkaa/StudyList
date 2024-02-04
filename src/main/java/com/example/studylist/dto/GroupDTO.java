package com.example.studylist.dto;

import com.example.studylist.model.Group;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDTO {
    private Long id;
    private String title;

    public static GroupDTO toDTO(Group group) {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setTitle(group.getTitle());
        return dto;
    }
}
