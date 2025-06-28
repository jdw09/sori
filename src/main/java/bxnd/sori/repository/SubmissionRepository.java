package bxnd.sori.repository;

import bxnd.sori.entity.SubmittedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<SubmittedAssignment, Long> {
}
