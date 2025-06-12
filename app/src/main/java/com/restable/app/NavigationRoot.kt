package com.restable.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.restable.feature.auth.presentation.LoginScreen
import com.restable.feature.reportdetail.presentation.ReportDetailScreen
import com.restable.feature.reportlist.presentation.ReportListScreen
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

@Serializable
data object ReportList : NavKey

@Serializable
data class ReportDetail(val id: String) : NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Login)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {

                is Login -> {
                    NavEntry(key = key) {
                        LoginScreen()
                    }
                }

                is ReportList -> {
                    NavEntry(key = key) {
                        ReportListScreen()
                    }
                }

                is ReportDetail -> {
                    NavEntry(key = key) {
                        ReportDetailScreen()
                    }
                }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        },
    )
}