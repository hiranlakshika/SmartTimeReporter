package com.restable.feature.reportlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restable.core.domain.model.Result
import com.restable.feature.reportlist.domain.repository.ReportListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportListViewModel @Inject constructor(private val repository: ReportListRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(ReportListState())
    val state: StateFlow<ReportListState> get() = _state

    init {
        getTimeReports()
    }

    private fun getTimeReports() = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }

        when (val result = repository.getTimeReports()) {
            is Result.Error -> {
                _state.update { it.copy(isLoading = false) }
            }

            is Result.Success -> {
                _state.update { it.copy(reports = result.data, isLoading = false) }
            }
        }
    }
}