package com.example.studylist.mvc;

import com.example.studylist.dto.GroupDTO;
import com.example.studylist.dto.SubjectDTO;
import com.example.studylist.dto.UserDTO;
import com.example.studylist.service.GroupService;
import com.example.studylist.service.SubjectService;
import com.example.studylist.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Hidden
@Controller
@RequestMapping("/records")
public class RecordController {

    private final GroupService groupService;
    private final SubjectService subjectService;
    private final UserService userService;

    public RecordController(GroupService groupService, SubjectService subjectService, UserService userService) {
        this.groupService = groupService;
        this.subjectService = subjectService;
        this.userService = userService;
    }

    @GetMapping("/show")
    public String showForm(Model model) {
        List<GroupDTO> groups = groupService.listAll();
        List<SubjectDTO> subjects = subjectService.listAll();
        log.info("Initial group list loaded");
        log.info("Initial subject list loaded");
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        return "recordSelection";
    }

    @GetMapping("/list")
    public String getList(@RequestParam(name = "groupNumber", required = false) Long groupNumber,
                          @RequestParam(name = "subjectNumber", required = false) Long subjectNumber,
                          Model model) {
        List<GroupDTO> groups = groupService.listAll();
        List<SubjectDTO> subjects = subjectService.listAll();

        log.info("Selected group ID: {}", groupNumber);
        log.info("Selected subject ID: {}", subjectNumber);

        if (groupNumber != null) {
            List<UserDTO> students;
            if (subjectNumber != null) {
                GroupDTO group = groupService.getOne(groupNumber);
                SubjectDTO subject = subjectService.getOne(subjectNumber);

                model.addAttribute("selectedGroupName", group.getTitle());
                model.addAttribute("selectedSubjectName", subject.getTitle());
                model.addAttribute("groupId", groupNumber);
                model.addAttribute("subjectId", subjectNumber);

                students = userService.findStudentsByGroupAndSubjectId(groupNumber, subjectNumber);
                userService.findTeacherByGroupAndSubjectId(groupNumber, subjectNumber)
                        .ifPresent(teacher -> model.addAttribute("teacher", teacher));
            } else {
                students = userService.findStudentsByGroupId(groupNumber);
                model.addAttribute("groupId", groupNumber);
            }
            model.addAttribute("students", students);
        }

        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        return "header";
    }


}
