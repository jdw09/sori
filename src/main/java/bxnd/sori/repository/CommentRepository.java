package bxnd.sori.repository;

import bxnd.sori.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<PostComment, Long> {
}
