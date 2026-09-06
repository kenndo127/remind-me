package com.kenneth.remind_me.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonRequestDto {
    @NotBlank(message = "A person must have a name")
    private String name;
}
