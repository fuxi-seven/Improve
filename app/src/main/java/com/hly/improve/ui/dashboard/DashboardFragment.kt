package com.hly.improve.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hly.improve.AndroidJsActivity
import com.hly.improve.ComposeActivity
import com.hly.improve.databinding.FragmentDashboardBinding
import com.hly.improve.mvi.ui.main.view.MviActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        initData()
        binding.jumpBtn.setOnClickListener { jumpToComposeActivity() }
        binding.mviBtn.setOnClickListener { jumpToMviActivity() }
        binding.jsBtn.setOnClickListener { jumpToAndroidJsActivity() }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        val numbers = intArrayOf(45, 30, 25)
        val names = arrayOf("dog", "pig", "cat")
        binding.pieView.setData(numbers, names)
    }

    private fun jumpToComposeActivity() {
        val intent = Intent(this.context, ComposeActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToMviActivity() {
        val intent = Intent(this.context, MviActivity::class.java)
        startActivity(intent)
    }

    private fun jumpToAndroidJsActivity() {
        val intent = Intent(this.context, AndroidJsActivity::class.java)
        startActivity(intent)
    }
}