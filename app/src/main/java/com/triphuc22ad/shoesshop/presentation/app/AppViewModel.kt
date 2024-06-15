package com.triphuc22ad.shoesshop.presentation.app

import androidx.lifecycle.ViewModel
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    val state: StateFlow<AppUiState> = appStateRepository.appUiState

    fun showBottomBar(isShowed: Boolean) {
        appStateRepository.showBottomBar(isShowed)
    }

    fun showAdminBottomBar(isShowed: Boolean) {
        appStateRepository.showAdminBottomBar(isShowed)
    }

    fun updateUserInfo(userInfo: UserInfoResponse) {
        appStateRepository.updateUserInfo(userInfo)
    }

    fun updateNotify(message: String) {
        appStateRepository.updateNotify(message)
    }

    fun resetState() {
        appStateRepository.resetAppState()
    }
}