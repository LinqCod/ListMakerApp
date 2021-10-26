package com.linqcod.listmakerapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linqcod.listmakerapp.databinding.ListSelectionViewholderBinding
import com.linqcod.listmakerapp.ui.main.model.TaskList

class ListSelectionRecyclerViewAdapter(
    private val lists: MutableList<TaskList>,
    private val clickListener: ListSelectionRecyclerViewClickListener
) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].title
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun listsUpdated() {
        notifyItemInserted(lists.size - 1)
    }
}