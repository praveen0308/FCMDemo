package com.jmm.brsap.fcmdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jmm.brsap.fcmdemo.repositories.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
):ViewModel(){

    val userRoleId=userPreferencesRepository.userRoleId.asLiveData()
    val userName = userPreferencesRepository.userName.asLiveData()
    val userRoleID = userPreferencesRepository.userRoleId.asLiveData()
    val welcomeStatus = userPreferencesRepository.welcomeStatus.asLiveData()

    fun updateWelcomeStatus(status:Int)=viewModelScope.launch {
        userPreferencesRepository.updateWelcomeStatus(status)
    }

    fun updateUserId(status:Int)=viewModelScope.launch {
        userPreferencesRepository.updateUserId(status)
    }


    fun updateUserName(userName:String)=viewModelScope.launch {
        userPreferencesRepository.updateUserName(userName)
    }

    fun updateUserRoleID(roleId:Int)=viewModelScope.launch {
        userPreferencesRepository.updateUserRoleId(roleId)
    }


}