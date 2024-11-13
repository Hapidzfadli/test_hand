package com.hand.train.springboot_demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateStudentRequest {
    @Size(min = 2, message = "name must be more than 2 characters")
    private String sName;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "birth must be in yyyy-MM-dd format")
    private String sBrith;

    @Pattern(regexp = "^(M|F)$", message = "sex must be M or F")
    private String sSex;
}