package com.diabeloop.sample.architecture.user

import androidx.lifecycle.ViewModel
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    val userList = userRepository.getList().map { it.map { user -> user.toUserItem() } }
}