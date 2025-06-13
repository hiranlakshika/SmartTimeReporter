package com.restable.feature.reportlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.restable.R
import com.restable.feature.reportlist.presentation.components.ReportListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportListScreen(viewModel: ReportListViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.time_reports),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }) { innerPadding ->
        if (state.value.isLoading) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(items = state.value.reports, key = { it.id }) {
                    ReportListItem(
                        title = it.title,
                        description = it.description,
                        duration = "${it.duration} ${stringResource(R.string.hours)}",
                        date = it.createdAt
                    )
                }
            }
        }

    }
}