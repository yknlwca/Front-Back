package com.theraconnect.domain.video.entity;

import com.theraconnect.domain.schedule.entity.ExerciseSchedule;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Enabled
@Getter
@Setter
@Builder
public class PatientVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientVideoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_schedule_id", nullable = false)
    private ExerciseSchedule exerciseSchedule;

    @Column(nullable = false)
    private String videoPath;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

}
