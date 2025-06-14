package bxnd.sori.service;

import bxnd.sori.Jwt.JwtProvider;
import bxnd.sori.entity.User;
import bxnd.sori.entity.Member;
import bxnd.sori.repository.UserRepository;
import bxnd.sori.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void signup(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        userRepository.save(user);

        Member member = Member.builder()
                .userNm(username)
                .password(user.getPassword())
                .email(email)
                .role("USER")
                .createdAt(LocalDateTime.now())
                .build();
        memberRepository.save(member);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        return jwtProvider.createAccessToken(user.getUsername());
    }
}