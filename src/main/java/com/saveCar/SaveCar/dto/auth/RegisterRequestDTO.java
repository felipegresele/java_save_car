package com.saveCar.SaveCar.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
