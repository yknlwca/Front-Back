package com.theraconnect.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "EMR_detail")
@Getter
@SuperBuilder
public class EMRdetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emr_detail_id")
    private Long EMRdetailId;

    // 차트 작성
    private LocalDateTime chartDate;

    // 증상
    private String symptoms;

    // 소견
    private String opinion;

    // 신체 부위
    private Integer bodyComponent;

    // EMRdetail N : EMRbase 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emr_base_Id", nullable = false)
    private EMRbase emRbase;

    // EMRdetail 1 : 진료 일정 1
    @OneToOne(mappedBy = "emRdetail")
    private MedicalSchedule medicalSchedule;

    // EMRdetail 1 : 운동 처방 N
    @OneToMany(mappedBy = "emRdetail")
    @Builder.Default
    private List<ExercisePrescription> exercisePrescriptions = new ArrayList<>();
}
