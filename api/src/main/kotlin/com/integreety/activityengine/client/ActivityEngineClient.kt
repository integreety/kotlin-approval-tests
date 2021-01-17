package com.integreety.activityengine.client

import com.integreety.activityengine.api.request.ActivityRequest
import com.integreety.activityengine.api.response.ActivityResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "activityEngineClient", url = "\${activityEngine.url}")
interface ActivityEngineClient {

    @GetMapping("/activities/{id}")
    fun find(@PathVariable("id") activityId: String): ResponseEntity<ActivityResponse>

    @PostMapping("/activities")
    fun save(activityRequest: ActivityRequest): ResponseEntity<ActivityResponse>
}