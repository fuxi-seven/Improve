package com.hly.improve.mvi.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hly.improve.databinding.ActivityMviBinding
import com.hly.improve.mvi.data.api.RetrofitBuilder
import com.hly.improve.mvi.data.model.User
import com.hly.improve.mvi.ui.main.adapter.MainAdapter
import com.hly.improve.mvi.ui.main.intent.MainIntent
import com.hly.improve.mvi.ui.main.intent.MainState
import com.hly.improve.mvi.ui.main.viewmodel.MainViewModel
import com.hly.improve.mvi.ui.main.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MviActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMviBinding
    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        binding.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                //发生Intent到ViewModel
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
        binding.buttonHint.setOnClickListener {
            lifecycleScope.launch {
                //发生Intent到ViewModel
                mainViewModel.userIntent.send(MainIntent.PreFetch)
            }
        }
    }


    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(binding.recyclerView.context,
                    (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding.recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this.viewModelStore, ViewModelFactory(RetrofitBuilder.apiService)).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            //监听State变化来更新UI,所有的collect方法都是suspend修饰的，所以扔了协程里
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Prepare -> {
                        binding.mviText.visibility = View.VISIBLE
                        binding.mviImg.visibility = View.VISIBLE
                    }
                    is MainState.Loading -> {
                        binding.mviText.visibility = View.GONE
                        binding.mviImg.visibility = View.GONE
                        binding.buttonHint.visibility = View.GONE
                        binding.buttonFetchUser.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MviActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}