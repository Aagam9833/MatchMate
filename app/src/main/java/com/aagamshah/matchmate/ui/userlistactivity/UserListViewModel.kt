package com.aagamshah.matchmate.ui.userlistactivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aagamshah.matchmate.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.fetchUsers().collect()
        }
    }

}