package com.hly.improve.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hly.improve.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView(notificationsViewModel.dataList)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(list: ArrayList<String>) {
        //1.去掉activity_main.xml中的ConstraintLayout的android:paddingTop="?attr/actionBarSize" //上面有间隔
        //2.将nav_host_fragment_activity_main的width和height设为0dp //滑动后RecyclerView底部显示不全
        val adapter = this.context?.let { MyAdapter(it, list) }
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.overScrollMode = ScrollView.OVER_SCROLL_NEVER
        //设置布局为垂直线性布局
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
    }
}