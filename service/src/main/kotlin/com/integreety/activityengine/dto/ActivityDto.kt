package com.integreety.activityengine.dto

import com.integreety.activityengine.api.shared.question.InputQuestion
import java.time.ZonedDateTime
import java.util.*

data class ActivityDto(
        val id: String? = null,
        val lessonId: String,
        val maxScore: Int? = null,
        val inputQuestions: List<InputQuestion> = ArrayList(),
        val createdAt: ZonedDateTime? = null
)