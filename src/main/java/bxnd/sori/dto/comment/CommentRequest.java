package bxnd.sori.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties
public record CommentRequest(
        @NotBlank(message = "댓글 내용은 필수 입력 사항입니다.")
        String content,
        @NotNull(message = "POST ID는 필수 사항입니다.")
        Long post_id
) {}
