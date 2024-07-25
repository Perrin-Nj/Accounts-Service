package com.perrin.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold failed response information"
)
public class ErrorResponseDto {
    @Schema(
            description = "Failed API path",
            example = "api/v1/create"
    )
    private String apiPath;
    @Schema(
            description = "Error code error ",
            example = "500"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Failed API error message",
            example = "Internal Server error"
    )
    private String errorMessage;
    @Schema(
            description = "Time error occurred",
            example = "2024-07-01T00:00:00"
    )
    private LocalDateTime errorTime;
}
