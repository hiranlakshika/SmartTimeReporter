package com.restable.feature.reportlist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ReportListScreen(viewModel: ReportListViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()


    Column {
        if (state.value.isLoading) {
            Text(text = "Loading...")
        }
        Text(text = "Report List Screen")
    }
}