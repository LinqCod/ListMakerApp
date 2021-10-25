package com.linqcod.listmakerapp.ui.main.listdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linqcod.listmakerapp.R

class ListDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}