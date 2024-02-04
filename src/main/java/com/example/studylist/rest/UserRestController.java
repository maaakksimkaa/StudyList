package com.example.studylist.rest;

import com.example.studylist.dto.UserDTO;
import com.example.studylist.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/user")
public class UserRestController {
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public String registration(@RequestBody UserDTO dto) {
        log.info(dto.getId().toString());
        service.create(dto);
        return "Регистрация успешна!";
    }

}
