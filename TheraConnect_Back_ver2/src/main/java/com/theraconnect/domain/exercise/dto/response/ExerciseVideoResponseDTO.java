package com.theraconnect.domain.exercise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseVideoResponseDTO {

    private Long exerciseVideoId;
    private String videoTitle;
    private String videoPath;
    private LocalDateTime uploadTime;
    private String thumbnailPath;
    private Integer accuracy;
    private Long exerciseResultId;
}