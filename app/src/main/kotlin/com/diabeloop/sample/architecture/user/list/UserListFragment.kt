package com.diabeloop.sample.architecture.user.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diabeloop.architecture.R
import com.diabeloop.architecture.databinding.FragmentUserListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : UserListPresenter, Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding by lazy {
        checkNotNull(_binding) { "ViewBinding ${FragmentUserListBinding::class} has been used outside view's lifecycle" }
    }
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        binding.presenter = this
        // set up adapter
        binding.userList.adapter = UserListAdapter(this)
        // set update list update
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.userList.collect {
                (binding.userList.adapter as? UserListAdapter)?.submitList(it)
            }
        }
        return binding.root
    }

    override fun onModifyUserClick(userId: Int) {
        val action = UserListFragmentDirections.actionUserListFragmentToSaveUserFragment(userId)
        findNavController().navigate(action)
    }

    override fun onDeleteUserClick(userId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            val bar = if (viewModel.deleteUser(userId)) {
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    getString(R.string.user_delete_success_message),
                    Snackbar.LENGTH_SHORT
                )
            } else {
                Snackbar.make(
                    requireContext(),
                    binding.root,
                    getString(R.string.user_delete_failure_message),
                    Snackbar.LENGTH_SHORT
                )
            }
            bar.show()
        }
    }

    override fun onCreateUserClick() {
        val action = UserListFragmentDirections.actionUserListFragmentToSaveUserFragment()
        findNavController().navigate(action)
    }
}
