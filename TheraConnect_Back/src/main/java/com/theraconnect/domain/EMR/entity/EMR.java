package com.theraconnect.domain.EMR.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String treatPart;
}
