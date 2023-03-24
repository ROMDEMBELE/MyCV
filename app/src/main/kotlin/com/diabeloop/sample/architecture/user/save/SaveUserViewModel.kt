package com.diabeloop.sample.architecture.user.save

import androidx.lifecycle.ViewModel
import com.diabeloop.sample.architecture.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * ViewModel for [SaveUserFragment].
 */
@HiltViewModel
class SaveUserViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    /**
     * Return uiModel.
     */
    fun uiModel(id: Int?): Flow<SaveUserUiModel> =
        userRepository.getUser(id).map { it?.toUserUiModel() ?: SaveUserUiModel() }

    /**
     * Save user in database.
     */
    suspend fun saveUser(uiModel: SaveUserUiModel): Boolean = userRepository.save(uiModel.toUser())
}
