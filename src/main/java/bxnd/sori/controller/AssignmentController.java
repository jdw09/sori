package bxnd.sori.controller;

import bxnd.sori.dto.assignment.AssignmentRequest;
import bxnd.sori.dto.assignment.AssignmentResponse;
import bxnd.sori.entity.Assignment;
import bxnd.sori.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public List<Assignment> getAll() {
        return assignmentService.getAllAssignments();
    }

    @PostMapping
    public AssignmentResponse create(@Valid @RequestBody AssignmentRequest requestDto) {
        return assignmentService.createAssignment(requestDto);
    }
}
