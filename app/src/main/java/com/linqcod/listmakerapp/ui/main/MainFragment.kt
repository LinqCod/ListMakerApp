package com.linqcod.listmakerapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.linqcod.listmakerapp.databinding.MainFragmentBinding
import com.linqcod.listmakerapp.ui.main.adapter.ListSelectionRecyclerViewAdapter
import com.linqcod.listmakerapp.ui.main.model.TaskList

class MainFragment() : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    lateinit var clickListener: MainFragmentInteractionListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)

        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)

        binding.listRecyclerView.adapter = recyclerViewAdapter

        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }

    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }


}