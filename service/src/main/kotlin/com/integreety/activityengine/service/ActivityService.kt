package com.integreety.activityengine.service

import com.integreety.activityengine.api.shared.question.InputQuestion
import com.integreety.activityengine.config.logger.log
import com.integreety.activityengine.dto.ActivityDto
import com.integreety.activityengine.service.calculate.calculateMaxScore
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomUtils.nextInt
import org.springframework.stereotype.Service
import java.time.ZonedDateTime.now
import java.util.UUID.randomUUID

@Service
class ActivityService {

    fun find(activityId: String): ActivityDto {
        log().info("Received request for activityId:{}", activityId)
        return ActivityDto(id = activityId, lessonId = randomUUID().toString(), maxScore = nextInt(), inputQuestions = listOf(
                InputQuestion(
                        id = randomUUID().toString(),
                        displayOrder = 1,
                        question = randomAlphanumeric(20),
                        standardAnswer = randomAlphanumeric(20),
                        maxScore = nextInt())
        ))
    }

    fun save(activityDto: ActivityDto): ActivityDto {
        log().info("Saving activity:{}", activityDto)
        return activityDto.copy(
                id = randomUUID().toString(),
                createdAt = now(),
                maxScore = activityDto.calculateMaxScore(),
                inputQuestions = activityDto.inputQuestions
        )
    }
}
