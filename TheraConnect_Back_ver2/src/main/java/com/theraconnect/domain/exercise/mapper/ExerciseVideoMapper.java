package com.theraconnect.domain.exercise.mapper;

import com.theraconnect.domain.exercise.dto.request.ExerciseVideoRequestDTO;
import com.theraconnect.domain.exercise.dto.response.ExerciseVideoResponseDTO;
import com.theraconnect.domain.exercise.entity.ExerciseResult;
import com.theraconnect.domain.exercise.entity.ExerciseVideo;

public class ExerciseVideoMapper {

    public static ExerciseVideo toEntity(ExerciseVideoRequestDTO dto) {
        return ExerciseVideo.builder()
                .videoTitle(dto.getVideoTitle())
                .videoPath(dto.getVideoPath())
                .thumbnailPath(dto.getThumbnailPath())
                .accuracy(dto.getAccuracy())
                .exerciseResult(ExerciseResult.builder().exerciseResultId(dto.getExerciseResultId()).build())
                .build();
    }

    public static ExerciseVideoResponseDTO toResponseDTO(ExerciseVideo entity) {
        return ExerciseVideoResponseDTO.builder()
                .exerciseVideoId(entity.getExerciseVideoId())
                .videoTitle(entity.getVideoTitle())
                .videoPath(entity.getVideoPath())
                .uploadTime(entity.getUploadTime())
                .thumbnailPath(entity.getThumbnailPath())
                .accuracy(entity.getAccuracy())
                .exerciseResultId(entity.getExerciseResult() != null ? entity.getExerciseResult().getExerciseResultId() : null)
                .build();
    }
}