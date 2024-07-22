package com.theraconnect.domain.member.entity;

import com.theraconnect.domain.exercise.entity.ExerciseResult;
import com.theraconnect.domain.schedule.entity.EMRbase;
import com.theraconnect.domain.schedule.entity.ExercisePrescription;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String loginId;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Character gender;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String phoneNumber;

    private String provider;

    private String accountId;

    private String profileImagePath;

    @Enumerated(EnumType.STRING)
    private UserStatus userState;

    // 환자 N 치료사 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    // 환자 1 : EMR N
    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<EMRbase> emRbases = new ArrayList<>();

    // 환자 1 : 진료 일정 N
    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<MedicalSchedule> medicalSchedules = new ArrayList<>();

    // 환자 1 : 운동 처방 N
    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<ExercisePrescription> exercisePrescriptions = new ArrayList<>();

    // 환자 1 : 운동 결과 N
    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<ExerciseResult> exerciseResults = new ArrayList<>();
}
