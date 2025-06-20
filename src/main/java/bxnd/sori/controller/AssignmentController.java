package bxnd.sori.controller;

import bxnd.sori.dto.assignment.AssignmentRequest;
import bxnd.sori.dto.assignment.AssignmentResponse;
import bxnd.sori.dto.assignments.AssignmentsResponse;
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
    public AssignmentsResponse getAll() {
        return assignmentService.getAllAssignments();
    }

    @PostMapping
    public AssignmentResponse create(@Valid @RequestBody AssignmentRequest request) {
        return assignmentService.createAssignment(request);
    }
}
