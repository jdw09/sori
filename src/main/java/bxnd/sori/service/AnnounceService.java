package bxnd.sori.service;

import bxnd.sori.dto.comment.GetAllAnnouncesResponse;
import bxnd.sori.repository.AnnounceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AnnounceService {
  private final AnnounceRepository announceRepository;

  public GetAllAnnouncesResponse getAllAnnounces() {
    return new GetAllAnnouncesResponse(announceRepository.findAll());
  }
}
