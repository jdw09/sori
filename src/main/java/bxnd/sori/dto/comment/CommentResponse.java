package bxnd.sori.dto.comment;

import bxnd.sori.entity.AllAnnounce;
import bxnd.sori.entity.Member;

public record CommentResponse(
    Long id,
    String content,
    AllAnnounce allAnnounce
){}
