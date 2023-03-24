package com.diabeloop.sample.architecture.user.list

/**
 * Presenter interface for [UserListFragment].
 */
interface UserListPresenter {

    /**
     * Callback for the "edit" button click.
     *
     * @param userId identifier of the item to modify.
     */
    fun onModifyUserClick(userId: Int)

    /**
     * Callback for "delete" button click.
     *
     * @param userId identifier of the item to delete.
     */
    fun onDeleteUserClick(userId: Int)

    /**
     * Callback for "create" button click.
     */
    fun onCreateUserClick()
}
