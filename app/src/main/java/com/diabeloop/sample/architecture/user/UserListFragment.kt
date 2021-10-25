package com.diabeloop.sample.architecture.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diabeloop.architecture.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentUserListBinding.inflate(inflater, container, false)
        // set up adapter
        binding.userList.adapter = UserListAdapter(object : UserListAdapter.UserItemPresenter {
            override fun onClick(user: UserItemModel) {
                val action = UserListFragmentDirections.actionUserListFragmentToUserFormFragment(user.id)
                findNavController().navigate(action)
            }
        })
        // set update list update
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userList.collect {
                (binding.userList.adapter as? UserListAdapter)?.submitList(it)
            }
        }
        // set on click create
        binding.createUserButton.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToUserFormFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }
}