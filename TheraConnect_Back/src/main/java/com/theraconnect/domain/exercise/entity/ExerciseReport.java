package com.theraconnect.domain.exercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@NoArgsConstructor
public class ExerciseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_report_id")
    private Long exerciseReportId;

    private String exerciseName;

    private LocalDateTime createTime;

    // 최고 영상 경로
    private String bestVideoPath;

    // 최고 환자 이미지 경로
    private String bestImagePatientPath;

    // 최고 치료사 이미지 경로
    private String bestImageTherapistPath;

    // 환자 시작 가동 범위
    private Integer motionRangeStartPatient;

    // 치료사 시작 가동 범위
    private Integer motionRangeStartTherapist;

    // 환자 끝 가동 범위
    private Integer motionRangeEndPatient;

    // 치료사 끝 가동 범위
    private Integer motionRangeEndTherapist;

    private String description;

    // 보고서 N : 운동 결과 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_result_id")
    private ExerciseResult exerciseResult;
}

