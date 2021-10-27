package com.linqcod.listmakerapp.ui.main.listdetail

import androidx.lifecycle.ViewModel
import com.linqcod.listmakerapp.ui.main.model.TaskList

class ListDetailViewModel : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)

    lateinit var list: TaskList

    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}