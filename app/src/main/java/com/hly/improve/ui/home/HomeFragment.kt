package com.hly.improve.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hly.improve.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        initData()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        var mDatas = ArrayList<Float>()
        mDatas.add(1f)
        mDatas.add(2f)
        mDatas.add(3f)
        mDatas.add(4f)
        var mColors = ArrayList<Int>()
        mColors.add(0xff83ccd2.toInt())
        mColors.add(0xffc0e1ce.toInt())
        mColors.add(0xfffac55e.toInt())
        mColors.add(0xffef805f.toInt())
        binding.ppCircle.setData(mDatas, mColors)
    }
}