package com.diabeloop.sample.architecture.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFormViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun loadUser(id: Int?): Flow<UserFormModel> =
        id?.let {
            userRepository.getUser(id).map { it.toUserFormModel() }
        } ?: run {
            flow {
                emit(UserFormModel())
            }
        }

    fun saveUser(userFormModel: UserFormModel) {
        viewModelScope.launch {
            userRepository.save(userFormModel.toUser())
        }
    }
}

