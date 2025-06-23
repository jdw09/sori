package bxnd.sori.dto.CreateAnnounce;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateAnnounceRequest(
  @NotBlank(message = "공지 제목은 필수 입력값입니다.") @Length(message = "공지 제목은 4~30글자입니다.", min = 4, max = 30) String title,
  @NotBlank(message = "공지 내용은 필수 입력값입니다.") @Length(message = "공지 내용은 4~30글자입니다.", min = 4, max = 30) String content
) {}
