package com.linqcod.listmakerapp.ui.main.listdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linqcod.listmakerapp.databinding.ListItemViewholderBinding
import com.linqcod.listmakerapp.ui.main.model.TaskList

class ListItemsRecyclerViewAdapter (
    private val list: TaskList
) : RecyclerView.Adapter<ListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = ListItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.binding.textViewTask.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

}