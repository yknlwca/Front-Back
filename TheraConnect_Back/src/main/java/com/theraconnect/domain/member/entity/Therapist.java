package com.theraconnect.domain.member.entity;


import com.theraconnect.domain.exercise.entity.GuideVideo;
import com.theraconnect.domain.schedule.entity.EMRbase;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long therapistId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    private Character gender;

    private String profileImagePath;

    private String belong;

    @Enumerated(EnumType.STRING)
    private UserStatus userState;

    private Character workDay;

    private String career;

    @Column(nullable = false)
    private String phoneNumber;

    // 치료사 1 : 환자 N
    @OneToMany(mappedBy = "therapist")
    @Builder.Default
    private List<Patient> patients = new ArrayList<>();

    // 치료사 1 : 진료 일정 N
    @OneToMany(mappedBy = "therapist")
    @Builder.Default
    private List<MedicalSchedule> medicalSchedules = new ArrayList<>();

    // 치료사 1 : EMR base N
    @OneToMany(mappedBy = "therapist")
    @Builder.Default
    private List<EMRbase> emRbases = new ArrayList<>();

    // 치료사 1: Guide Video N
    @OneToMany(mappedBy = "therapist")
    @Builder.Default
    private List<GuideVideo> guideVideos = new ArrayList<>();
}
