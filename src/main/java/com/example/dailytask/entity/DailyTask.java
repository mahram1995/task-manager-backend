package com.example.dailytask.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

import com.example.dailytask.entity.TaskEnums.*;

@Entity
@Table(name = "DAILY_TASK")
@Getter
@Setter
@NoArgsConstructor
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_task_seq")
    @SequenceGenerator(name = "daily_task_seq", sequenceName = "DAILY_TASK_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "TASK_NAME", nullable = false, length = 200)
    private String taskName;
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;
    @Column(name = "CLIENT", length = 150)
    private String client;
    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "DEV_STATUS", length = 40)
    private DevelopmentStatus devStatus;
    @Column(name = "CREATE_BY", nullable = false, length = 100)
    private String createBy;
    @Column(name = "EXPECTED_DELIVERY_DATE")
    private LocalDate expectedDeliveryDate;
    @Column(name = "DELIVERY_DATE")
    private LocalDate deliveryDate;
    @Column(name = "EXPECTED_START_DATE")
    private LocalDate expectedStartDate;
    @Column(name = "ACTUAL_START_DATE")
    private LocalDate actualStartDate;
    @Column(name = "EXPECTED_END_DATE")
    private LocalDate expectedEndDate;
    @Column(name = "ACTUAL_END_DATE")
    private LocalDate actualEndDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY", length = 30)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    @Column(name = "DELIVERY_STATUS", length = 40)
    private DeliveryStatus deliveryStatus;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskNote> notes = new ArrayList<>();
}
