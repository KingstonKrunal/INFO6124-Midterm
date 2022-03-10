package com.example.krunalshah.krunaldogpound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class myAdapter(private val mList: List<DogsViewModel>) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val myView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

//        val recycleView: RecyclerView = myView.findViewById(R.id.dogPoundRecyclerView)
//
//        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
//        recycleView.layoutManager = linearLayoutManager

        return ViewHolder(myView)
    }

//    private fun LinearLayoutManager(myAdapter: myAdapter): LinearLayoutManager {
//        return LinearLayoutManager(myAdapter)
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val DogsViewModel = mList[position]
        holder.textView.text = DogsViewModel.text
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textDisplay)
    }
}