package com.theraconnect.domain.member.entity;

import com.theraconnect.domain.EMR.entity.EMR;
import com.theraconnect.domain.schedule.entity.ExerciseSchedule;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theraphist_id", nullable = false)
    private Theraphist theraphist;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private String provider;    // 연동된 소셜 사이트

    private String accountId;

    // 필요 없을 수도
    private String profileImagePath;

    private int height;

    private int weigt;

    private String main_symtoms;

    private String sugeryHistory;

    @Column(nullable = false)
    private int userState;  // 0 OR 1

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ExerciseSchedule> exerciseSchedules = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EMR> emrs = new ArrayList<>();
}
