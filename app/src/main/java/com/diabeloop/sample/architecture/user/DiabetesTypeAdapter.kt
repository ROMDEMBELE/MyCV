package com.diabeloop.sample.architecture.user

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.diabeloop.architecture.R
import com.diabeloop.architecture.databinding.ItemDiabetesTypeDropDownBinding
import com.diabeloop.sample.architecture.common.extension.displayIcon
import com.diabeloop.sample.architecture.common.extension.displayMessage
import com.diabeloop.sample.architecture.domain.user.DiabetesType

class DiabetesTypeAdapter(context: Context) : ArrayAdapter<DiabetesType>(context, R.layout.item_diabetes_type_drop_down, DiabetesType.values()) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.let {
            it.tag as ItemDiabetesTypeDropDownBinding
        } ?: run {
            ItemDiabetesTypeDropDownBinding.inflate(
                LayoutInflater.from(context),
                parent, false
            )
        }
        binding.message = getItem(position)?.displayMessage(context.resources)
        binding.iconRes = getItem(position)?.displayIcon(context.resources)
        binding.root.tag = binding
        return binding.root
    }

}
