package com.theraconnect.domain.EMR.entity;

import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Theraphist;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class EMR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emrId;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    private String comment;

    private String bodyPart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theraphist_id", nullable = false)
    private Theraphist theraphist;
}
