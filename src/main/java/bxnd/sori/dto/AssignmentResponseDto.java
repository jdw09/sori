package bxnd.sori.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponseDto {
    private Long id;
    private String title;
    private String content;
    private String dueDate;
}
