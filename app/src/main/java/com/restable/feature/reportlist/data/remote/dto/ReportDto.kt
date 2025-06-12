package com.restable.feature.reportlist.data.remote.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.datetime.Instant

@Serializable
data class ReportDto(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("created_at") val createdAt: Instant,
    val duration: Float
)
