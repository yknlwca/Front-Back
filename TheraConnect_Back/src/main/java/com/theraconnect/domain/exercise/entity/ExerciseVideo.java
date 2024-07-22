package com.theraconnect.domain.exercise.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class ExerciseVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_video_id")
    private Long exerciseVideoId;

    private String videoTitle;

    private String videoPath;

    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

    private String thumbnailPath;

    private Integer accuracy;

    // 운동 영상 N : 운동 결과 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_result_id")
    private ExerciseResult exerciseResult;
}
