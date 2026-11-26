package com.example.dailytask.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="TASK_NOTE") @Getter @Setter @NoArgsConstructor
public class TaskNote { @Id @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="task_note_seq") @SequenceGenerator(name="task_note_seq",sequenceName="TASK_NOTE_SEQ",allocationSize=1) private Long id; @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="TASK_ID",nullable=false) private DailyTask task; @Column(name="NOTE_DATE",nullable=false) private LocalDateTime noteDate; @Column(name="NOTE_DESCRIPTION",nullable=false,length=2000) private String noteDescription; }
