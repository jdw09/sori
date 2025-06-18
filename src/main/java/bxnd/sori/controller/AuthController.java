package bxnd.sori.controller;

import bxnd.sori.dto.signup.SignupRequest;
import bxnd.sori.dto.login.LoginRequest;
import bxnd.sori.dto.signup.SignupResponse;
import bxnd.sori.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest request) {
        return memberService.signup(request.username(), request.password(), request.email());
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return memberService.login(request.username(), request.password());
    }
}
