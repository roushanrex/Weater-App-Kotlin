package com.codingwithjks.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.codingwithjks.notepad.ui.ViewModel.NoteViewModel
import com.codingwithjks.weatherapp.Adapter.NoteAdapter
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.databinding.ActivityFavListBinding



class Fav_list_Activity : AppCompatActivity() {

    private lateinit var noteList: ArrayList<Favorite>
    private lateinit var noteViewModel: NoteViewModel

    private lateinit var binding: ActivityFavListBinding
    private lateinit var noteAdapter: NoteAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        noteAdapter = NoteAdapter(applicationContext, ArrayList<Favorite>(), this)

        initialiseRecyclerView()


        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getCardsData(this)?.observe(this, Observer {
            noteAdapter.setData(it as ArrayList<Favorite>)
            noteList = it
        })

    }

    private fun initialiseRecyclerView() {
         binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = noteAdapter
        }
    }
}
