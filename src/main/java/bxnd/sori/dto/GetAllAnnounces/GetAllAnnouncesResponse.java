package bxnd.sori.dto.GetAllAnnounces;

import bxnd.sori.entity.Announce;
import java.util.List;

public record GetAllAnnouncesResponse(
    List<Announce> announces
) {}