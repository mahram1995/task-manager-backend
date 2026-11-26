package com.example.dailytask.dto;

import jakarta.validation.constraints.*;

import java.time.*;

import com.example.dailytask.entity.TaskEnums.*;

public final class ApiDtos {
    private ApiDtos() {
    }

    public record LoginRequest(@NotBlank String userName, @NotBlank String password) {
    }

    public record LoginResponse(String token, String userName) {
    }

    public record RegisterRequest(
            String userName,
            String password
    ) {
    }

    public record TaskRequest(@NotBlank @Size(max = 200) String taskName, @Size(max = 2000) String description,
                              @Size(max = 150) String client, DevelopmentStatus devStatus,
                              LocalDate expectedDeliveryDate, LocalDate deliveryDate, LocalDate expectedStartDate,
                              LocalDate actualStartDate, LocalDate expectedEndDate, LocalDate actualEndDate,
                              Priority priority, DeliveryStatus deliveryStatus) {
    }

    public record TaskResponse(Long id, String taskName, String description, String client, LocalDate createDate,
                               DevelopmentStatus devStatus, String createBy, LocalDate expectedDeliveryDate,
                               LocalDate deliveryDate, LocalDate expectedStartDate, LocalDate actualStartDate,
                               LocalDate expectedEndDate, LocalDate actualEndDate, Priority priority,
                               DeliveryStatus deliveryStatus) {
    }

    public record NoteRequest(@NotBlank @Size(max = 2000) String noteDescription) {
    }

    public record NoteResponse(Long id, String noteDescription, LocalDateTime noteDate) {
    }
}
