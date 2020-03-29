package com.example.bottomnavigation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation.R
import com.example.bottomnavigation.databinding.FragmentStateWiseBinding
import com.example.bottomnavigation.network.DataManager
import com.example.bottomnavigation.network.dataclasses.AllDataResponse
import kotlinx.android.synthetic.main.fragment_state_wise.*

interface ItemSelectListener {
    fun onItemClick(stateWiseData: AllDataResponse.Statewise, clickPosition: Int)
}

class StateWiseFragment : Fragment() {

    private lateinit var binding: FragmentStateWiseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStateWiseBinding.inflate(inflater, container, false)
        activity?.title = getString(R.string.title_states_stats)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inflateUI()
    }

    private fun initPaymentModeAdapter(recycleView: RecyclerView?, conFeeResponse: List<AllDataResponse.Statewise>) {
        val stateWiseAdapter = StateWiseAdapter(conFeeResponse, itemClickListener)
        val layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        recycleView?.layoutManager = layoutManager
        recycleView?.adapter = stateWiseAdapter
    }

    val itemClickListener = object : ItemSelectListener {
        override fun onItemClick(stateWiseData: AllDataResponse.Statewise, clickPosition: Int) {
        }
    }

    fun inflateUI() {
        val allDataAResponse = DataManager.allDataAResponse
        allDataAResponse?.statewise?.let {
            initPaymentModeAdapter(stateWiseRv, it)
        }
        Log.i("testApi", allDataAResponse.toString())

    }

    companion object {
        val TAG: String = StateWiseFragment::class.java.simpleName
        fun newInstance() = StateWiseFragment()
    }

}