package bxnd.sori.service;

import bxnd.sori.dto.Submission.SubmissionRequest;
import bxnd.sori.dto.Submission.SubmissionResponse;
import bxnd.sori.entity.Assignment;
import bxnd.sori.entity.SubmittedAssignment;
import bxnd.sori.repository.AssignmentRepository;
import bxnd.sori.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SubmissionService {


    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    public SubmissionResponse createSubmission(SubmissionRequest request) {
        LocalDateTime now = LocalDateTime.now();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assignment assignment = assignmentRepository.findByTitle(request.title())
                .orElseThrow(() -> new IllegalArgumentException("과제를 찾을 수 없습니다."));
        LocalDateTime deadline = assignment.getDeadline();
        boolean isLate = deadline.isBefore(now);
        SubmittedAssignment submittedAssignment = SubmittedAssignment.builder()
                .title(request.title())
                .content(request.content())
                .author(assignment.getAuthor())
                .date_over_flag(isLate)
                .build();
        submissionRepository.save(submittedAssignment);

        return new SubmissionResponse(
                request.title(),
                request.content(),
                isLate
        );
    }
}