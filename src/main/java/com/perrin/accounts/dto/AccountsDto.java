package com.perrin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Accounts Schema",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "AccountNumber should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber, should have 10 digits")
    @Schema(
            description = "Schema to hold Account information",
            example = "908123873"
    )
    private Long accountNumber;

    @Schema(
            description = "Schema to hold Account type infos",
            example = "Saving"
    )
    @NotBlank(message = "AccountType should not be blank")
    private String accountType;

    @Schema(
            description = "Schema to hold Account branch address infos",
            example = "Yaoundé, Emana"
    )
    @NotBlank(message = "BranchAddress should not be blank")
    private String branchAddress;
}
