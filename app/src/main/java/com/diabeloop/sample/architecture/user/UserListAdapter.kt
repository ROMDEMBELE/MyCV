package com.diabeloop.sample.architecture.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diabeloop.architecture.databinding.ItemUserBinding


class UserListAdapter(val presenter: UserItemPresenter) : ListAdapter<UserItemModel, UserListAdapter.UserItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false), presenter)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // inner class go last before companion object
    inner class UserItemViewHolder(private val binding: ItemUserBinding, val presenter: UserItemPresenter) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserItemModel) {
            binding.user = user
            binding.presenter = presenter
        }
    }

    interface UserItemPresenter {
        fun onClick(user: UserItemModel)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItemModel>() {
            override fun areItemsTheSame(oldItem: UserItemModel, newItem: UserItemModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItemModel, newItem: UserItemModel): Boolean {
                return oldItem.firstName == newItem.firstName
                        && oldItem.lastName == oldItem.lastName
                        && oldItem.diabetesType == newItem.diabetesType
            }

        }
    }
}