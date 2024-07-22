package com.theraconnect.domain.schedule.entity;


import com.theraconnect.domain.exercise.entity.GuideVideo;
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
public class ExercisePrescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercisePrescriptionId;

    // 시작 날짜
    private LocalDate startDate;

    // 끝 날짜
    private LocalDate endDate;

    // 세트 수
    private Integer setCount;

    // 운동 처방 N : 환자 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // 운동 처방 N : EMRdetail 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emr_detail_id")
    private EMRdetail emRdetail;

    // 운동 처방 1 : 가이드 영상 N
    @OneToMany(mappedBy = "exercisePrescription")
    @Builder.Default
    private List<GuideVideo> guideVideoList = new ArrayList<>();
}
