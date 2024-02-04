package com.example.studylist.service;

import com.example.studylist.dto.SubjectDTO;
import com.example.studylist.model.Subject;
import com.example.studylist.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public List<SubjectDTO> listAll() {
        return repository.findAll().stream()
                .map(SubjectDTO::toDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getOne(Long id) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        return SubjectDTO.toDTO(subject);
    }

    public SubjectDTO create(SubjectDTO dto) {
        Subject subject = Subject.toEntity(dto);
        return SubjectDTO.toDTO(repository.save(subject));
    }

    public SubjectDTO update(SubjectDTO dto) {
        Subject subject = Subject.toEntity(dto);
        return SubjectDTO.toDTO(repository.save(subject));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
