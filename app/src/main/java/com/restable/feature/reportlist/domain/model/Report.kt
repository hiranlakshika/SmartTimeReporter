package com.restable.feature.reportlist.domain.model

data class Report(
    val id: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val duration: Float
)
