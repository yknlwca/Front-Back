package com.theraconnect.domain.schedule.entity;


import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "EMR_base")
@Getter
@SuperBuilder
@NoArgsConstructor
public class EMRbase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emr_base_id")
    private Long EMRbaseId;

    private Float height;

    private Float weight;

    private String surgeryName;

    private LocalDate surgeryDate;

    private String surgeryComponent;

    // EMRbase N : 환자 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // EMRbase N : 치료사 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    // EMRbase 1 : EMRdetail N
    @OneToMany(mappedBy = "emRbase")
    @Builder.Default
    private List<EMRdetail> emRdetails = new ArrayList<>();
}
