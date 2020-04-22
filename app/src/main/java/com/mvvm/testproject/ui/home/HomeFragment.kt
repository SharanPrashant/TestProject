package com.mvvm.testproject.ui.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.testproject.R
import com.mvvm.testproject.ui.home.Homepageadapter

class HomeFragment : Fragment() {
    private lateinit var linearLayoutManager: GridLayoutManager
    private lateinit var homeViewModel: HomeViewModel
    var data = MutableLiveData<List<String>>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerview)
        linearLayoutManager = GridLayoutManager(getActivity(),2)
        recyclerView.layoutManager = linearLayoutManager

        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        /*homeViewModel.getMutableLiveData().observe(viewLifecycleOwner, Observer{

        });*/

        homeViewModel.getMutableLiveData()
            .observe(requireActivity(), Observer<List<String>> { itemList ->
                // update UI
                data.value = itemList
                recyclerView.adapter = Homepageadapter(itemList)
            })
        return root
    }
}
