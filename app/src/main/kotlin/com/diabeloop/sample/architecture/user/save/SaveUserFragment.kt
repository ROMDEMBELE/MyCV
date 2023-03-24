package com.diabeloop.sample.architecture.user.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diabeloop.architecture.R
import com.diabeloop.architecture.databinding.FragmentSaveUserBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Fragment, display form to save or modify user.
 */
@AndroidEntryPoint
class SaveUserFragment : SaveUserPresenter, Fragment() {

    private var _binding: FragmentSaveUserBinding? = null
    private val binding by lazy {
        checkNotNull(_binding) {
            "ViewBinding ${FragmentSaveUserBinding::class} has been used outside view's lifecycle"
        }
    }

    private val viewModel: SaveUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSaveUserBinding.inflate(inflater, container, false)
        binding.presenter = this
        val userId = SaveUserFragmentArgs.fromBundle(requireArguments()).userId
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycleScope.launchWhenCreated {
                viewModel.uiModel(userId).collect {
                    binding.uiModel = it
                }
            }
        }
        return binding.root
    }

    override fun onSaveButtonClick() {
        binding.uiModel?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                val bar = if (viewModel.saveUser(it)) {
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        getString(R.string.user_save_success_message),
                        Snackbar.LENGTH_SHORT
                    )
                } else {
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        getString(R.string.user_save_failure_message),
                        Snackbar.LENGTH_SHORT
                    )
                }
                bar.show()
                findNavController().navigateUp()
            }
        }
    }
}
