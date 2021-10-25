package com.diabeloop.sample.architecture.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diabeloop.architecture.databinding.FragmentUserFormBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFormFragment : Fragment() {

    private val userFormViewModel: UserFormViewModel by viewModels()
    var userId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentUserFormBinding.inflate(inflater, container, false)
        UserFormFragmentArgs.fromBundle(requireArguments()).let {
            userId = if (it.userId > 0) it.userId else null
        }
        viewLifecycleOwner.lifecycleScope.launch {
            userFormViewModel.loadUser(userId).collect {
                binding.userFormModel = it
            }
        }

        binding.setSaveUserButtonListener {
            binding.userFormModel?.let {
                userFormViewModel.saveUser(it)
                findNavController().navigateUp()
            }
        }

        binding.diabetesTypeAutocomplete.setAdapter(DiabetesTypeAdapter(requireContext()))
        return binding.root
    }
}
