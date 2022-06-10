package com.lexmasterteam.notesapproomdb.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lexmasterteam.notesapproomdb.R
import com.lexmasterteam.notesapproomdb.databinding.CardViewBinding
import com.lexmasterteam.notesapproomdb.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    private var noteList = emptyList<Note>()

    inner class MyViewHolder(val binding: CardViewBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.binding.tvTitle.text = currentItem.title
        holder.binding.tvDescription.text = currentItem.description
        holder.binding.cvNote.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }
    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged() // aktualziacja adaptera
    }
}