package com.diabeloop.sample.architecture.user.list

import androidx.lifecycle.ViewModel
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * ViewModel for [UserListFragment].
 *
 * @property userRepository
 */
@HiltViewModel
class UserListViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    /**
     * Return list of users.
     */
    val userList: Flow<List<UserItemModel>> =
        userRepository.getList().map { it.map { user -> user.toUserItem() } }

    /**
     * Delete user.
     *
     * @param userId
     * @return flag true if user successfully deleted.
     */
    suspend fun deleteUser(userId: Int): Boolean = userRepository.delete(userId)
}
