package bxnd.sori.service;


import bxnd.sori.dto.comment.CommentRequest;
import bxnd.sori.dto.comment.CommentResponse;
import bxnd.sori.entity.Member;
import bxnd.sori.entity.PostComment;
import bxnd.sori.exception.errorcode.AuthErrorCode;
import bxnd.sori.repository.CommentRepository;
import bxnd.sori.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentResponse addComment(CommentRequest commentRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Member author = memberRepository.findByUserNm(username)
                .orElseThrow(() -> AuthErrorCode.USER_NOT_FOUND.defaultException("작성자 정보가 없습니다."));

        PostComment comment = PostComment.builder()
                .content(commentRequest.content())
                .author(author)
                .build();

        PostComment saved = commentRepository.save(comment);

        return new CommentResponse(
                saved.getId(),
                saved.getContent(),
                saved.getAllAnnounce()
        );
    }
}
