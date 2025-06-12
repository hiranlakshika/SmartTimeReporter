package com.restable.feature.auth.data

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject

class SupabaseRemoteLoginApi @Inject constructor(private val supabaseClient: SupabaseClient) :
    RemoteLoginApi {
    override suspend fun login(email: String, password: String) {
        try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    override suspend fun logout() {
        try {
            supabaseClient.auth.signOut()
        } catch (e: Exception) {
            println(e)
        }
    }
}