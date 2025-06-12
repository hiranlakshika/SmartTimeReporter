package com.restable.feature.reportlist.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReportDto(val id: String, val title: String, val description: String, val date: String)
