package com.restable.feature.reportlist.presentation

import com.restable.feature.reportlist.domain.model.Report

data class ReportListState(val reports: List<Report> = emptyList(), val isLoading: Boolean = false)
