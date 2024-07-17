package com.theraconnect.domain.video.entity;

import com.theraconnect.domain.schedule.entity.ExerciseSchedule;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class ExerciseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ExerciseVideoId;

    @ManyToOne
    @JoinColumn(name = "exercise_schedule_id")
    private ExerciseSchedule exerciseSchedule;

    private String videoPath;

    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

    @Column(nullable = false)
    private String title;

    private String description;

    private String warning;

    private int level;

    @Enumerated(EnumType.STRING)
    private Category category;
}
