package com.virent.gweather.viewmodels

import androidx.lifecycle.ViewModel
import com.virent.gweather.core.data.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val auth: AuthenticationRepository
) : ViewModel() {

    val currentUser = auth.currentUser

}