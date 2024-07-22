package com.theraconnect.domain.exercise.entity;


import com.theraconnect.domain.member.entity.Patient;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class ExerciseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_result_id")
    private Long exerciseResultId;

    private LocalDate exerciseDate;

    // 운동 결과 N : 환자 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_account_id", nullable = false)
    private Patient patient;

    // 운동 결과 1 : 가이드 영상 N
    @OneToMany(mappedBy = "exerciseResult")
    @Builder.Default
    private List<GuideVideo> guideVideos = new ArrayList<>();

    // 운동 결과 1 : 운동 영상 N
    @OneToMany(mappedBy = "exerciseResult")
    @Builder.Default
    private List<ExerciseVideo> exerciseVideos = new ArrayList<>();

    // 운동 결과 1 : 운동 보고서 N
    @OneToMany(mappedBy = "exerciseResult")
    @Builder.Default
    private List<ExerciseReport> exerciseReports = new ArrayList<>();
}
