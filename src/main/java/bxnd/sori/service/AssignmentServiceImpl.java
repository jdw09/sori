package bxnd.sori.service;

import bxnd.sori.dto.AssignmentRequestDto;
import bxnd.sori.dto.AssignmentResponseDto;
import bxnd.sori.entity.Assignment;
import bxnd.sori.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl {

    private final AssignmentRepository assignmentRepository;

    public void createAssignment(AssignmentRequestDto requestDto) {
//        Assignment assignment = Assignment.builder()
//                .title(requestDto.getTitle())
//                .content(requestDto.getContent())
//                .dueDate(requestDto.getDueDate())
//                .build();
//
//        Assignment saved = assignmentRepository.save(assignment);
//
//        return AssignmentResponseDto.builder()
//                .id(saved.getId())
//                .title(saved.getTitle())
//                .content(saved.getContent())
//                .dueDate(saved.getDueDate())
//              .build();
        return;
    }
}
