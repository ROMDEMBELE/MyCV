package com.diabeloop.sample.architecture.user

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.diabeloop.architecture.R
import com.diabeloop.architecture.databinding.ItemDiabetesTypeDropDownBinding
import com.diabeloop.sample.architecture.common.extension.displayIcon
import com.diabeloop.sample.architecture.common.extension.displayMessage
import com.diabeloop.sample.architecture.domain.user.DiabetesType

class DiabetesTypeAdapter(context: Context) : ArrayAdapter<DiabetesType>(context, R.layout.item_diabetes_type_drop_down, DiabetesType.values()) {

    var selectedDiabetesType : DiabetesType? = null

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: run {
            val binding = ItemDiabetesTypeDropDownBinding.inflate(
                LayoutInflater.from(context),
                parent, false
            )
            getItem(position)?.let {
                binding.message = it.displayMessage(context.resources)
                binding.itemDiabetesTypeText.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    it.displayIcon(context),
                    null
                )
            }
            return binding.root
        }
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            // nothing
        }

        override fun convertResultToString(resultValue: Any?): String? {
            selectedDiabetesType = (resultValue as? DiabetesType)
            return selectedDiabetesType?.displayMessage(context.resources)
        }

    }

}
