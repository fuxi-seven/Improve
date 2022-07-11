package com.hly.improve.ui.motion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.hly.improve.databinding.FragmentMotionBinding
import com.hly.improve.motion.AnimeActivity
import com.hly.improve.motion.CarMotionActivity
import com.hly.improve.motion.CardMotionActivity


class MotionLayoutFragment : Fragment() {

    private var _binding: FragmentMotionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val motionsViewModel =
            ViewModelProvider(this).get(MotionLayoutViewModel::class.java)

        _binding = FragmentMotionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(motionsViewModel.text)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView(title : LiveData<String>) {
        binding.title.text = title.value
        binding.tv1.setOnClickListener {
            val intent = Intent(this.context, AnimeActivity::class.java)
            startActivity(intent)
        }
        binding.tv2.setOnClickListener {
            val intent = Intent(this.context, CarMotionActivity::class.java)
            startActivity(intent)
        }
        binding.tv3.setOnClickListener {
            val intent = Intent(this.context, CardMotionActivity::class.java)
            startActivity(intent)
        }
    }
}