package com.aagamshah.matchmate.presentation.profilelistactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.matchmate.common.Resource
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.aagamshah.matchmate.domain.repository.ProfileRepository
import com.aagamshah.matchmate.domain.repository.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<ProfileModel>>>()
    val users: LiveData<Resource<List<ProfileModel>>> = _users

    fun fetchProfiles() {
        viewModelScope.launch(Dispatchers.IO) {
            _users.postValue(profileRepository.fetchProfiles())
        }
    }

    fun storeProfilesOffline(profiles: List<ProfileModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.saveProfiles(profiles)
        }
    }

    fun saveProfileChoice(id: String, isAccepted: Boolean?) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfileChoice(id, isAccepted)
        }
    }

    fun fetchOfflineProfiles() {
        viewModelScope.launch(Dispatchers.IO) {
            val localProfiles = profileRepository.fetchOfflineProfiles()
            _users.postValue(Resource.Success(localProfiles))
        }
    }

}