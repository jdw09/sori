package bxnd.sori.controller;

import bxnd.sori.dto.Submission.SubmissionRequest;
import bxnd.sori.dto.Submission.SubmissionResponse;
import bxnd.sori.dto.assignment.AssignmentRequest;
import bxnd.sori.dto.assignment.AssignmentResponse;
import bxnd.sori.dto.assignments.AssignmentsResponse;
import bxnd.sori.entity.Assignment;
import bxnd.sori.entity.SubmittedAssignment;
import bxnd.sori.service.AssignmentService;
import bxnd.sori.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final SubmissionService submissionService;

    @GetMapping
    public AssignmentsResponse getAll() {
        return assignmentService.getAllAssignments();
    }

    @PostMapping
    public AssignmentResponse create(@Valid @RequestBody AssignmentRequest request) {
        return assignmentService.createAssignment(request);
    }

    @PostMapping("/submit")
    public SubmissionResponse submit(@Valid @RequestBody SubmissionRequest request) {
        return submissionService.createSubmission(request);
    }
}
