package bxnd.sori.controller;

import bxnd.sori.dto.SignupRequestDto;
import bxnd.sori.dto.LoginRequestDto;
import bxnd.sori.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto request) {
        memberService.signup(request.getUsername(), request.getPassword(), request.getEmail());
        return "회원가입 성공!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto request) {
        return memberService.login(request.getUsername(), request.getPassword());
    }
}
