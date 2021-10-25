package com.diabeloop.sample.architecture.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.diabeloop.architecture.databinding.FragmentUserFormBinding
import com.diabeloop.sample.architecture.common.extension.displayMessage
import com.diabeloop.sample.architecture.domain.user.DiabetesType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFormFragment : Fragment() {

    private val userFormViewModel: UserFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentUserFormBinding.inflate(inflater, container, false)

        binding.userFormModel = this.userFormViewModel.userFormModel

        binding.setSaveUserButtonListener {
            userFormViewModel.saveUser()
        }

        binding.diabetesTypeAutocomplete.setAdapter(DiabetesTypeAdapter(requireContext()))
        return binding.root
    }
}
