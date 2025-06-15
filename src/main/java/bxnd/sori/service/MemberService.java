package bxnd.sori.service;

import bxnd.sori.Jwt.JwtProvider;
import bxnd.sori.entity.Member;
import bxnd.sori.exception.errorcode.AuthErrorCode;
import bxnd.sori.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signup(String userNm, String password, String email) {
        if (memberRepository.findByUserNm(userNm).isPresent()) {
            // throw new RuntimeException("이미 존재하는 사용자입니다.");
            throw AuthErrorCode.ALREADY_EXISTS_USERNAME.defaultException();
        }

        Member member = Member.builder()
                .userNm(userNm)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role("USER")
                .build();

        memberRepository.save(member);
    }

    public String login(String userNm, String password) {
        Member member = memberRepository.findByUserNm(userNm)
                .orElseThrow(AuthErrorCode.USER_NOT_FOUND::defaultException);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw AuthErrorCode.INVALID_CREDENTIALS.defaultException();
        }

        return jwtProvider.createAccessToken(member.getUserNm(), member.getRole());
    }
}