package com.linqcod.listmakerapp.ui.main.listdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.linqcod.listmakerapp.MainActivity
import com.linqcod.listmakerapp.databinding.ListDetailFragmentBinding
import com.linqcod.listmakerapp.ui.main.MainViewModel
import com.linqcod.listmakerapp.ui.main.MainViewModelFactory
import com.linqcod.listmakerapp.ui.main.listdetail.adapter.ListItemsRecyclerViewAdapter
import com.linqcod.listmakerapp.ui.main.model.TaskList

class ListDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var binding:ListDetailFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ListDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))).get(MainViewModel::class.java)

        val list: TaskList? = arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)
        if(list != null) {
            viewModel.list = list
            requireActivity().title = list.title
        }

        val recyclerviewAdapter = ListItemsRecyclerViewAdapter(viewModel.list)
        binding.listItemsRecyclerview.adapter = recyclerviewAdapter
        binding.listItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.onTaskAdded = {
            recyclerviewAdapter.notifyDataSetChanged()
        }

    }

}