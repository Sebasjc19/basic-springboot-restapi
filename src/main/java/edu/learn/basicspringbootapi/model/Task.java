package edu.learn.basicspringbootapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String title;

    @Size(max = 300)
    @Column(length = 300)
    private String description;

    @NotNull
    @Column(nullable = false)
    private boolean completed;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
}
