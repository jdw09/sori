package bxnd.sori.service;

import bxnd.sori.dto.CreateAnnounce.CreateAnnounceRequest;
import bxnd.sori.dto.CreateAnnounce.CreateAnnounceResponse;
import bxnd.sori.dto.GetAllAnnounces.GetAllAnnouncesResponse;
import bxnd.sori.dto.GetAnnounceById.GetAnnounceByIdResponse;
import bxnd.sori.dto.UpdateAnnounce.UpdateAnnounceRequest;
import bxnd.sori.dto.UpdateAnnounce.UpdateAnnounceResponse;
import bxnd.sori.entity.Announce;
import bxnd.sori.entity.Member;
import bxnd.sori.exception.errorcode.AnnounceErrorCode;
import bxnd.sori.exception.errorcode.AuthErrorCode;
import bxnd.sori.repository.AnnounceRepository;
import bxnd.sori.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AnnounceService {
  private final AnnounceRepository announceRepository;
  private final MemberRepository memberRepository;

  public GetAllAnnouncesResponse getAllAnnounces() {
    return new GetAllAnnouncesResponse(announceRepository.findAll());
  }

  public GetAnnounceByIdResponse getAnnounceById(Long id) {
    Optional<Announce> announce = announceRepository.findById(id);
    if (announce.isEmpty()) throw AnnounceErrorCode.ANNOUNCE_NOT_FOUND.defaultException();
    return new GetAnnounceByIdResponse(announce.get());
  }

  public CreateAnnounceResponse createAnnounce(CreateAnnounceRequest request) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    System.out.println(username);
    Optional<Member> author = memberRepository.findByUserNm(username);
    if(author.isEmpty()) throw AuthErrorCode.USER_NOT_FOUND.defaultException();
    Announce newAnnounce = Announce.builder()
        .title(request.title())
        .content(request.content())
        .author(author.get())
        .build();
    announceRepository.save(newAnnounce);
    return new CreateAnnounceResponse(newAnnounce.getId());
  }

  public UpdateAnnounceResponse updateAnnounce(Long announceId, UpdateAnnounceRequest request) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<Member> authorOpt = memberRepository.findByUserNm(username);
    if(authorOpt.isEmpty()) throw AuthErrorCode.USER_NOT_FOUND.defaultException();
    Member author = authorOpt.get();
    Optional<Announce> announceOpt = announceRepository.findById(announceId);
    if(announceOpt.isEmpty()) throw AnnounceErrorCode.ANNOUNCE_NOT_FOUND.defaultException();
    Announce announce = announceOpt.get();
    if(!announce.getAuthor().getId().equals(author.getId())) throw AuthErrorCode.PERMISSION_DENIED.defaultException();
    if(request.title() != null) announce.setTitle(request.title());
    if(request.content() != null) announce.setContent(request.content());
    announceRepository.save(announce);
    return new UpdateAnnounceResponse(announce.getId());
  }
}
