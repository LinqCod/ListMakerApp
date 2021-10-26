package com.linqcod.listmakerapp.ui.main.listdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linqcod.listmakerapp.MainActivity
import com.linqcod.listmakerapp.R
import com.linqcod.listmakerapp.ui.main.model.TaskList

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.title

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}