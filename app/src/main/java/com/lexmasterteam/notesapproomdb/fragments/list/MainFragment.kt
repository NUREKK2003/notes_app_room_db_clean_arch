package com.lexmasterteam.notesapproomdb.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lexmasterteam.notesapproomdb.R
import com.lexmasterteam.notesapproomdb.databinding.FragmentMainBinding
import com.lexmasterteam.notesapproomdb.viewmodel.NoteViewModel


class MainFragment : Fragment() {

    private lateinit var mNoteViewModel:NoteViewModel
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        // RecyclerView
        val adapter = NoteAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        // ViewModel

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"Succesfully removed everything!",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_, _->

        }
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want delete everything?")
        builder.create().show()
    }
}