package com.apex.codeassesment.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.databinding.SimpleItemTextBinding

class SimpleItemListAdapter (private val list: List<User>,
                             private val listener: (User) -> Unit
): RecyclerView.Adapter<SimpleItemListAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TemplateViewHolder(
        SimpleItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.binding.apply {
            text1.text = (list[position].name.toString())
            text1.setOnClickListener {
                listener.invoke(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TemplateViewHolder(val binding: SimpleItemTextBinding): RecyclerView.ViewHolder(binding.root) {
    }
}