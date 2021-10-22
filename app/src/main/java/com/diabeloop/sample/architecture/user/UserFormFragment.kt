package com.diabeloop.sample.architecture.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.diabeloop.architecture.R
import com.diabeloop.architecture.databinding.FragmentUserFormBinding

class UserFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUserFormBinding.inflate(inflater, container, false)

        binding.userFormModel = UserFormModel(null, "Gille","Cookie")
        return binding.root
    }
}