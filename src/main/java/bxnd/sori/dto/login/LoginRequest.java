package bxnd.sori.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginRequest (
    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    @Length(message="사용자 이름은 최소 6글자, 최대 16글자입니다.", min = 4, max = 16)
    String username,
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(message = "비밀번호는 최소 8글자여야 하고, 숫자를 1개 이상 포함해야 합니다.", regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    String password
) {}