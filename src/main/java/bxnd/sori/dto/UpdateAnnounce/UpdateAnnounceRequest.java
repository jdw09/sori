package bxnd.sori.dto.UpdateAnnounce;

import jakarta.annotation.Nullable;
import org.hibernate.validator.constraints.Length;

public record UpdateAnnounceRequest(
    @Nullable @Length(message = "공지 제목은 4~30글자입니다.", min = 4, max = 30) String title,
    @Nullable @Length(message = "공지 내용은 4~30글자입니다.", min = 4, max = 30) String content
) {}
