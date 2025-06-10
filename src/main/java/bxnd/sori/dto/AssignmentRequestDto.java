package bxnd.sori.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentRequestDto {
    private String title;
    private String content;
    private String dueDate;
}
