package com.linqcod.listmakerapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.linqcod.listmakerapp.databinding.MainActivityBinding
import com.linqcod.listmakerapp.ui.main.MainFragment
import com.linqcod.listmakerapp.ui.main.MainViewModel
import com.linqcod.listmakerapp.ui.main.MainViewModelFactory
import com.linqcod.listmakerapp.ui.main.listdetail.ListDetailActivity
import com.linqcod.listmakerapp.ui.main.model.TaskList

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener {

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 1
    }

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if(result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                viewModel.updateList(it.getParcelableExtra(INTENT_LIST_KEY)!!)
                viewModel.refreshLists()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this)))
            .get(MainViewModel::class.java)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(this))
                .commitNow()
        }

        binding.fab.setOnClickListener {
            showCreateListDialog()
        }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) {
            dialog, _ ->
            dialog.dismiss()
            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)
        }

        builder.create().show()
    }

    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        resultLauncher.launch(listDetailIntent)
    }

    override fun listItemTapped(list: TaskList) {
        showListDetail(list)
    }

}