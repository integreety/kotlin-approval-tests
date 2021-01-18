package com.integreety.activityengine.rest

import com.integreety.activityengine.api.request.ActivityRequest
import com.integreety.activityengine.api.shared.question.InputQuestion
import com.integreety.activityengine.config.objectmapper.ObjectMapperCreator
import com.integreety.activityengine.dto.ActivityDto
import com.integreety.activityengine.service.ActivityService
import com.oneeyedmen.okeydoke.Approver
import com.oneeyedmen.okeydoke.junit5.KotlinApprovalsExtension
import io.mockk.every
import io.mockk.mockk
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.ZonedDateTime
import java.util.*

@ExtendWith(KotlinApprovalsExtension::class)
class ActivityControllerShould {

    private val activityId = randomAlphanumeric(36)
    private val objectWriter = ObjectMapperCreator().create().writerWithDefaultPrettyPrinter()
    private val activityService = mockk<ActivityService>()
    private val underTest = ActivityRestController(activityService)

    @Test
    fun `return an approved format of Activity without questions`(approver: Approver) {
        every { activityService.find(activityId) } returns anActivity()

        val result = underTest.find(activityId)

        approver.assertApproved(objectWriter.writeValueAsString(result))
    }

    @Test
    fun `return an approved format of Activity with questions`(approver: Approver) {
        every { activityService.find(activityId) } returns anActivityWithQuestions()

        val result = underTest.find(activityId)

        approver.assertApproved(objectWriter.writeValueAsString(result))
    }

    @Test
    fun `accept an approved format of Activity without questions`(approver: Approver) {
        every { activityService.save(any()) } returns anActivity()
        val activityRequest = ActivityRequest(lessonId = UUID.randomUUID().toString())

        val result = underTest.save(activityRequest)

        approver.assertApproved(objectWriter.writeValueAsString(result))
    }

    @Test
    fun `accept an approved format of Activity with questions`(approver: Approver) {
        every { activityService.save(any()) } returns anActivityWithQuestions()
        val easyRandom = EasyRandom(EasyRandomParameters().collectionSizeRange(1, 1))
        val activityRequest = easyRandom.nextObject(ActivityRequest::class.java)

        val result = underTest.save(activityRequest)

        approver.assertApproved(objectWriter.writeValueAsString(result))
    }

    private fun anActivity(): ActivityDto {
        return ActivityDto(
                id = "id",
                lessonId = "lessonId",
                maxScore = 5,
                createdAt = ZonedDateTime.parse("2020-10-21T10:11:12Z"),
        )
    }

    private fun anActivityWithQuestions(): ActivityDto {
        return anActivity().copy(
                inputQuestions = listOf(
                        InputQuestion(
                                id = "id",
                                question = "question1",
                                standardAnswer = "standardAnswer",
                                maxScore = 5,
                                displayOrder = 1
                        )
                ),
        )
    }
}