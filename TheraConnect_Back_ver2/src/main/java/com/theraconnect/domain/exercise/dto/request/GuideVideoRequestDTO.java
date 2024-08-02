package com.theraconnect.domain.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideVideoRequestDTO {

    private String videoPath;
    private String thumbnailPath;
    private LocalDateTime uploadTime;
    private String title;
    private String description;
    private String warning;
    private Set<String> categories;
    private Integer level;
    private Long therapistId;
    private Long exercisePrescriptionId;
    private Long exerciseResultId;
}