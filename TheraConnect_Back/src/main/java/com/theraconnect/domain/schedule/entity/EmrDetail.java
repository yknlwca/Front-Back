package com.theraconnect.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EmrDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emr_detail_id")
    private Long emrDetailId;

    // 차트 작성
    private LocalDateTime chartDate;

    // 증상
    private String symptoms;

    // 소견
    private String opinion;

    // 신체 부위
    private Integer bodyComponent;

    // EMRDetail N : EMRBase 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emr_base_Id", nullable = false)
    private EmrBase emrBase;

    // EMRDetail 1 : 진료 일정 1
    @OneToOne(mappedBy = "emrDetail")
    private MedicalSchedule medicalSchedule;

    // EMRDetail 1 : 운동 처방 N
    @OneToMany(mappedBy = "emrDetail")
    @Builder.Default
    private List<ExercisePrescription> exercisePrescriptions = new ArrayList<>();
}
