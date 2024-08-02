package com.theraconnect.domain.exercise.mapper;

import com.theraconnect.domain.exercise.dto.request.GuideVideoRequestDTO;
import com.theraconnect.domain.exercise.dto.response.GuideVideoResponseDTO;
import com.theraconnect.domain.exercise.entity.Category;
import com.theraconnect.domain.exercise.entity.ExerciseResult;
import com.theraconnect.domain.exercise.entity.GuideVideo;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.schedule.entity.ExercisePrescription;

import java.util.stream.Collectors;

public class GuideVideoMapper {

    public static GuideVideo toEntity(GuideVideoRequestDTO dto) {
        return GuideVideo.builder()
                .videoPath(dto.getVideoPath())
                .thumbnailPath(dto.getThumbnailPath())
                .uploadTime(dto.getUploadTime())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .warning(dto.getWarning())
                .categories(dto.getCategories().stream().map(Category::valueOf).collect(Collectors.toSet()))
                .level(dto.getLevel())
                .therapist(dto.getTherapistId() != null ? Therapist.builder().therapistId(dto.getTherapistId()).build() : null)
                .exercisePrescription(dto.getExercisePrescriptionId() != null ? ExercisePrescription.builder().exercisePrescriptionId(dto.getExercisePrescriptionId()).build() : null)
                .exerciseResult(dto.getExerciseResultId() != null ? ExerciseResult.builder().exerciseResultId(dto.getExerciseResultId()).build() : null)
                .build();
    }

    public static GuideVideoResponseDTO toResponseDTO(GuideVideo entity) {
        return GuideVideoResponseDTO.builder()
                .guideVideoId(entity.getGuideVideoId())
                .videoPath(entity.getVideoPath())
                .thumbnailPath(entity.getThumbnailPath())
                .uploadTime(entity.getUploadTime())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .warning(entity.getWarning())
                .categories(entity.getCategories().stream().map(Category::name).collect(Collectors.toSet()))
                .level(entity.getLevel())
                .therapistId(entity.getTherapist() != null ? entity.getTherapist().getTherapistId() : null)
                .exercisePrescriptionId(entity.getExercisePrescription() != null ? entity.getExercisePrescription().getExercisePrescriptionId() : null)
                .exerciseResultId(entity.getExerciseResult() != null ? entity.getExerciseResult().getExerciseResultId() : null)
                .build();
    }
}