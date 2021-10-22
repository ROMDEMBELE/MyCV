package com.diabeloop.sample.architecture.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userFormModel = UserFormModel(null, null, null)

    fun saveUser() {
        viewModelScope.launch {
            userRepository.save(userFormModel.toUser())
            //vers mon fragment
        }
    }
}
