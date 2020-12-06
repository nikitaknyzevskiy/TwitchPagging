package com.example.twitchgametest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.twitchgametest.R
import com.example.twitchgametest.adapter.GameAdapter
import com.example.twitchgametest.entity.full.GameFull
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment(), Observer<PagedList<GameFull>> {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val adapter = GameAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game_list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.games().flow.collectLatest { data ->
                adapter.submitData(data)
            }
        }
    }

    override fun onChanged(data: PagedList<GameFull>) {
    }

}