package bxnd.sori.controller;

import bxnd.sori.dto.AssignmentRequestDto;
import bxnd.sori.dto.AssignmentResponseDto;
import bxnd.sori.entity.Assignment;
import bxnd.sori.service.AssignmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentServiceImpl assignmentService;

    @GetMapping
    public List<Assignment> getAll() {
        return assignmentService.getAllAssignments();
    }

    @PostMapping
    public AssignmentResponseDto create(@RequestBody AssignmentRequestDto requestDto) {
        return assignmentService.createAssignment(requestDto);
    }
}
