package com.theraconnect.domain.schedule.entity;


import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
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
@NoArgsConstructor
@AllArgsConstructor
public class EmrBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emr_base_id")
    private Long emrBaseId;

    private Float height;

    private Float weight;

    private String surgeryName;

    private LocalDate surgeryDate;

    private String surgeryComponent;

    // EMRBase N : 환자 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // EMRBase N : 치료사 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    // EMRBase 1 : EMRDetail N
    @OneToMany(mappedBy = "emrBase")
    @Builder.Default
    private List<EmrDetail> emrDetails = new ArrayList<>();
}
