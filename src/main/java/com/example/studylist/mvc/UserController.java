package com.example.studylist.mvc;

import com.example.studylist.dto.UserDTO;
import com.example.studylist.dto.UserForm;
import com.example.studylist.service.GroupService;
import com.example.studylist.service.RoleService;
import com.example.studylist.service.SubjectService;
import com.example.studylist.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Hidden
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/update-users")
    public String editUsersForm(@RequestParam Long groupId, @RequestParam Long subjectId, Model model) {
        List<UserDTO> userDTOs = userService.findStudentsByGroupAndSubjectId(groupId, subjectId);
        UserForm userForm = new UserForm(userDTOs, groupId, subjectId);
        model.addAttribute("userForm", userForm);
        return "redaction";
    }

    @PostMapping("/update-users")
    public String updateUsers(@ModelAttribute("userForm") UserForm userForm,
                              @RequestParam(name = "groupNumber", required = false) Long groupNumber,
                              @RequestParam(name = "subjectNumber", required = false) Long subjectNumber,
                              RedirectAttributes redirectAttributes) {
        userService.updateAll(userForm.getUsers());
        redirectAttributes.addFlashAttribute("successMessage", "Пользователи успешно обновлены");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/records/list");
        uriBuilder.queryParam("groupNumber", groupNumber);
        uriBuilder.queryParam("subjectNumber", subjectNumber);

        return "redirect:" + uriBuilder.toUriString();
    }

}
