package bxnd.sori.controller;

import bxnd.sori.dto.AssignmentRequestDto;
import bxnd.sori.dto.AssignmentResponseDto;
import bxnd.sori.service.AssignmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentServiceImpl assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @RequestBody AssignmentRequestDto requestDto) {
        AssignmentResponseDto response = assignmentService.createAssignment(requestDto);
        return ResponseEntity.ok(response);
    }
}
