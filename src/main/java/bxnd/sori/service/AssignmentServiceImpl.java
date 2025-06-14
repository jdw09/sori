package bxnd.sori.service;

import bxnd.sori.dto.AssignmentRequestDto;
import bxnd.sori.dto.AssignmentResponseDto;
import bxnd.sori.entity.Assignment;
import bxnd.sori.entity.Member;
import bxnd.sori.repository.AssignmentRepository;
import bxnd.sori.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentResponseDto createAssignment(AssignmentRequestDto requestDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        // JWT 인증된 사용자 이름 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 사용자 찾기 (userNm 기준)
        Member author = memberRepository.findByUserNm(username)
                .orElseThrow(() -> new RuntimeException("작성자 정보가 없습니다."));

        Assignment assignment = Assignment.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .deadline(LocalDateTime.parse(requestDto.getDueDate(), formatter))
                .author(author) //
                .build();

        Assignment saved = assignmentRepository.save(assignment);

        return AssignmentResponseDto.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .content(saved.getContent())
                .dueDate(saved.getDeadline().format(formatter))
                .build();
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
}