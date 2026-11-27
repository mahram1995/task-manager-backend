package com.example.dailytask.dto;


import com.example.dailytask.entity.TaskEnums;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDomain {

    private Long id;

    private String taskName;

    private String description;

    private String client;

    private LocalDate createDate;

    private TaskEnums.DevelopmentStatus devStatus;

    private String createBy;

    private LocalDate expectedDeliveryDate;

    private LocalDate deliveryDate;

    private LocalDate expectedStartDate;

    private LocalDate actualStartDate;

    private LocalDate expectedEndDate;

    private LocalDate actualEndDate;

    private TaskEnums.Priority priority;

    private TaskEnums.DeliveryStatus deliveryStatus;
}

