package bxnd.sori.controller;

import bxnd.sori.dto.CreateAnnounce.CreateAnnounceRequest;
import bxnd.sori.dto.CreateAnnounce.CreateAnnounceResponse;
import bxnd.sori.dto.DeleteAnnounce.DeleteAnnouneResponse;
import bxnd.sori.dto.GetAllAnnounces.GetAllAnnouncesResponse;
import bxnd.sori.dto.GetAnnounceById.GetAnnounceByIdResponse;
import bxnd.sori.dto.UpdateAnnounce.UpdateAnnounceRequest;
import bxnd.sori.dto.UpdateAnnounce.UpdateAnnounceResponse;
import bxnd.sori.service.AnnounceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announces")
@RequiredArgsConstructor
public class AnnounceController {
  private final AnnounceService announceService;

  @GetMapping
  public GetAllAnnouncesResponse getAllAnnounces() {
    return announceService.getAllAnnounces();
  }

  @GetMapping("/{announceId}")
  public GetAnnounceByIdResponse getAnnounceById(@PathVariable("announceId") long announceId) {
    return announceService.getAnnounceById(announceId);
  }

  @PostMapping
  public CreateAnnounceResponse createAnnounce(@Valid @RequestBody CreateAnnounceRequest request) {
    return announceService.createAnnounce(request);
  }

  @PatchMapping("/{announceId}")
  public UpdateAnnounceResponse updateAnnounce(@PathVariable("announceId") long announceId, @Valid @RequestBody UpdateAnnounceRequest request) {
    return announceService.updateAnnounce(announceId, request);
  }

  @DeleteMapping("/{announceId}")
  public DeleteAnnouneResponse deleteAnnounce(@PathVariable("announceId") long announceId) {
    return announceService.deleteAnnounce(announceId);
  }
}
