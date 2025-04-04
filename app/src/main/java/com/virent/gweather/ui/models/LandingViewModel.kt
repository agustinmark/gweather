package com.virent.gweather.ui.models

import androidx.lifecycle.ViewModel
import com.virent.gweather.data.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val auth: AuthenticationRepository
) : ViewModel() {

    val currentUser = auth.currentUser

}