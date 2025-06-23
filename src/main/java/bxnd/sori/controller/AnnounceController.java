package bxnd.sori.controller;

import bxnd.sori.dto.comment.GetAllAnnouncesResponse;
import bxnd.sori.service.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
}
