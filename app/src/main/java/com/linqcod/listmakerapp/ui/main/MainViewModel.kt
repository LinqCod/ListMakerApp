package com.linqcod.listmakerapp.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.linqcod.listmakerapp.ui.main.model.TaskList

class MainViewModel(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    lateinit var onListAdded: (() -> Unit)
    lateinit var onTaskAdded: (() -> Unit)

    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    lateinit var list: TaskList

    private fun retrieveLists(): MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for(taskList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }

        return taskLists
    }

    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.title, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }

    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.title, list.tasks.toHashSet()).apply()
        lists.add(list)
    }

    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }

    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }

}