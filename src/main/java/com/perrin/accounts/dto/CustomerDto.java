package com.perrin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Schema(
        name = "Customer",
        description = "Schema to hold Customer details"
)
public class CustomerDto {

    @Schema(
            description = "Customer name",
            example = "Nj Perrin"
    )
    @NotBlank(message = "name cannot be blank")
    @Size(min = 5, max = 30, message = "The length should be between 5 and 30")
    private String name;

    @Schema(
            description = "Customer email",
            example = "perrin.nj.wandji@gmail.com"
    )
    @Email(message = "Email address should be valid value")
    @NotBlank(message = "Email should not be blank")
    private String email;

    @Schema(
            description = "Customer phone number",
            example = "237670754483"
    )
    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Mobile number should have 12 digits")
    private String mobileNumber;

    @Schema(
            description = "Customer account details"
    )
    @Valid
    private AccountsDto accountsDto;
}
