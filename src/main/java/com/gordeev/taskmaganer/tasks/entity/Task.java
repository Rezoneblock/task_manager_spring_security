package com.gordeev.taskmaganer.tasks.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "tasks_id_seq",
            allocationSize = 1, // регулируемо, очевидно
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    private Boolean done;
}
