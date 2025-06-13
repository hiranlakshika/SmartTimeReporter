package com.restable.feature.auth.data

import com.restable.core.domain.model.Result
import com.restable.core.domain.model.error.DataError
import com.restable.network.util.safeSupabaseCall
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserSession
import javax.inject.Inject

class SupabaseRemoteLoginApi @Inject constructor(private val supabaseClient: SupabaseClient) :
    RemoteLoginApi {
    override suspend fun login(
        email: String,
        password: String
    ): Result<UserSession?, DataError.NetworkError> {
        return safeSupabaseCall {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            supabaseClient.auth.currentSessionOrNull()
        }
    }

    override suspend fun logout(): Result<Unit, DataError.NetworkError> {
        return safeSupabaseCall {
            supabaseClient.auth.signOut()
        }
    }
}