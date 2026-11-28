package com.mislbd.sprint.dto;


import com.mislbd.sprint.entity.Enums;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDomain {

    private Long id;
    private String taskName;
    private String description;
    private String client;
    private Enums.DevelopmentStatus devStatus;
    private Enums.QAStatus qaStatus;
    private String supportMits;
    private String interMits;
    private String createBy;
    private String updateBy;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private LocalDate expectedDeliveryDate;
    private LocalDate deliveryDate;
    private LocalDate expectedStartDate;
    private LocalDate actualStartDate;
    private LocalDate expectedEndDate;
    private LocalDate actualEndDate;
    private Enums.Priority priority;
    private Enums.DeliveryStatus deliveryStatus;
    private Long version;
}

