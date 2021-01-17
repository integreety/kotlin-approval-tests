package com.integreety.activityengine.service

import com.integreety.activityengine.api.shared.question.InputQuestion
import com.integreety.activityengine.config.logger.log
import com.integreety.activityengine.dto.ActivityDto
import com.integreety.activityengine.service.calculate.calculateMaxScore
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.UUID.randomUUID

@Service
class ActivityService {

    fun find(activityId: String): ActivityDto {
        return ActivityDto(activityId, randomUUID().toString(), 10, listOf(
                InputQuestion(randomUUID().toString(), 1, randomAlphanumeric(20), randomAlphanumeric(20), 10)
        ))
    }

    fun save(activityDto: ActivityDto): ActivityDto {
        log().info("Saving activity:{}", activityDto)
        return activityDto.copy(id = randomUUID().toString(), createdAt = ZonedDateTime.now(), maxScore = activityDto.calculateMaxScore())
    }
}
