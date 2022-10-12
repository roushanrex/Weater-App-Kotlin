package com.codingwithjks.weatherapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.weatherapp.Fav_list_Activity
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.R


class NoteAdapter(private val context: Context, private var noteList:ArrayList<Favorite>, private val listener: Fav_list_Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }

    override fun getItemCount(): Int =noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=noteList[position]
        holder.city.text=note.description
        holder.name.text=note.description

    }

    fun setData(noteList: ArrayList<Favorite>)
    {
        this.noteList=noteList
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val city:TextView=itemView.findViewById(R.id.city)
        val name:TextView=itemView.findViewById(R.id.name)


    }
}