package com.example.mynotes.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotes.Model.Notes
import com.example.mynotes.R
import com.example.mynotes.ViewModel.NotesViewModel
import com.example.mynotes.databinding.FragmentHomeBinding
import com.example.mynotes.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()

    lateinit var mAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)



        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
            mAdapter = NotesAdapter(requireContext(), notesList)
            binding.rvAllNotes.adapter = mAdapter
        }

        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
                mAdapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = mAdapter
            }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
                mAdapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = mAdapter
            }
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
                mAdapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = mAdapter
            }
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggeredGridLayoutManager
                mAdapter = NotesAdapter(requireContext(), notesList)
                binding.rvAllNotes.adapter = mAdapter
            }
        }


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter notes here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(query: String?) {
        val newFilteredList = arrayListOf<Notes>()

        for(i in oldMyNotes){
            if (i.title.contains(query!!) || i.subTitle.contains(query!!)){
                newFilteredList.add(i)
            }
        }

        mAdapter.filtering(newFilteredList)
    }
}