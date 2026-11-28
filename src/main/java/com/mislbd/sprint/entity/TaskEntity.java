package com.mislbd.sprint.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

import com.mislbd.sprint.entity.Enums.*;

@Entity
@Table(name = "DAILY_TASK")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_task_seq")
    @SequenceGenerator(name = "daily_task_seq", sequenceName = "DAILY_TASK_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "TASK_NAME", nullable = false, length = 500)
    private String taskName;
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;
    private String client;
    @Enumerated(EnumType.STRING)
    private DevelopmentStatus devStatus;
    @Enumerated(EnumType.STRING)
    private QAStatus qaStatus;
    private String createBy;
    private String updateBy;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String supportMits;
    private String interMits;
    private LocalDate expectedDeliveryDate;
    private LocalDate deliveryDate;
    private LocalDate expectedStartDate;
    private LocalDate actualStartDate;
    private LocalDate expectedEndDate;
    private LocalDate actualEndDate;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskNoteEntity> notes = new ArrayList<>();
    @Version
    @Column(name = "VERSION")
    private Long version;

}
