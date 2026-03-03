package com.gordeev.taskmanager.tasks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(
            name = "task_seq",
            sequenceName = "tasks_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 3, max = 100, message = "Имя задачи должно быть минимум 3 символа и максимум 100 символов")
    private String name;

    @Column(length = 500)
    @Size(max = 500, message = "Описани должно быть максимум 500 символов")
    private String description;

    private Boolean done = false;
}
