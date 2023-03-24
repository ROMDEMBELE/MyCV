package com.diabeloop.sample.architecture.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diabeloop.architecture.databinding.ItemUserBinding

/**
 * Adapter for [User] list.
 *
 * @property presenter
 */
class UserListAdapter(private val presenter: UserListPresenter) :
    ListAdapter<UserItemModel, UserListAdapter.UserItemViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            presenter
        )

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * ViewHolder for [UserItemModel].
     *
     * @property binding
     * @property presenter
     */
    // inner class go last before companion object
    inner class UserItemViewHolder(
        private val binding: ItemUserBinding,
        private val presenter: UserListPresenter,
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Bind viewHolder with item value.
         */
        fun bind(user: UserItemModel) {
            binding.user = user
            binding.presenter = presenter
        }
    }

    companion object {
        /**
         * User item comparator.
         */
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItemModel>() {
            override fun areItemsTheSame(oldItem: UserItemModel, newItem: UserItemModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UserItemModel,
                newItem: UserItemModel,
            ): Boolean =
                oldItem.firstName == newItem.firstName &&
                        oldItem.lastName == newItem.lastName &&
                        oldItem.type == newItem.type
        }
    }
}
