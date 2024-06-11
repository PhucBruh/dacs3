package com.triphuc22ad.shoesshop.presentation.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triphuc22ad.shoesshop.data.model.UserInfoResponse
import com.triphuc22ad.shoesshop.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appStateRepository: AppStateRepository,
) : ViewModel() {
    val state: StateFlow<AppUiState> = appStateRepository.appUiState

    fun showBottomBar(isShowed: Boolean) {
//        viewModelScope.launch {
//            _state.value = _state.value.copy(
//                showBottomBar = isShowed
//            )
//        }
        appStateRepository.showBottomBar(isShowed)
    }

    fun updateUserInfo(userInfo: UserInfoResponse) {
//        viewModelScope.launch {
//            val role = if (userInfo.roles.contains("ROLE_ADMIN")) "ADMIN" else "USER"
//            _state.value = _state.value.copy(
//                user = User(
//                    firstName = userInfo.firstName,
//                    lastName = userInfo.lastName,
//                    email = userInfo.email,
//                    phone = userInfo.phone,
//                    role = role
//                )
//            )
//        }
        appStateRepository.updateUserInfo(userInfo)
    }

    fun updateNotify(message: String) {
//        _state.value = _state.value.copy(notify = message)
//        appStateRepository.updateNotify(message)
    }
}