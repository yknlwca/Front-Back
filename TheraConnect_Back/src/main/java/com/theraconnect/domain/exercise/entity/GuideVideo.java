package com.theraconnect.domain.exercise.entity;


import com.theraconnect.domain.schedule.entity.ExercisePrescription;
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
public class GuideVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_video_id")
    private Long guideVideoId;

    private String videoPath;

    private String thumbnailPath;

    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

    private String title;

    private String description;

    private String warning;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer level;

    // 가이드 영상 N : 치료사 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapist_id")
    private com.TheraConnect.domain.member.entity.Therapist therapist;

    // 가이드 영상 N : 운동 처방 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_prescription_id")
    private ExercisePrescription exercisePrescription;

    // 가이드 영상 N : 운동 결과 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_result_id")
    private ExerciseResult exerciseResult;

}
