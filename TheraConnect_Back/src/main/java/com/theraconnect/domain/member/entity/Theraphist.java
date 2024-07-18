package com.theraconnect.domain.member.entity;

import com.theraconnect.domain.EMR.entity.EMR;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
public class Theraphist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theraphistId;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private String belong;

    @Column(nullable = false)
    private int userState;

    @OneToMany(mappedBy = "theraphist", cascade = CascadeType.PERSIST, orphanRemoval = false)
    @Builder.Default
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "theraphist", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EMR> emrs = new ArrayList<>();
}
