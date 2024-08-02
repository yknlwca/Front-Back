package com.theraconnect.domain.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseVideoRequestDTO {

    private String videoTitle;
    private String videoPath;
    private String thumbnailPath;
    private Integer accuracy;
    private Long exerciseResultId;
}