package edu.learn.basicspringbootapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequestDto(
        @NotNull @Size(min=1, max=30)
        String title,
        @Max(300)
        String description,
        @NotNull
        boolean completed
) {
}
