package edu.learn.basicspringbootapi.dto;

import java.time.LocalDate;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDate createdAt
) {
}
