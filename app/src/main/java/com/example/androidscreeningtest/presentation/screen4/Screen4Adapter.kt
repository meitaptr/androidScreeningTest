package com.example.androidscreeningtest.presentation.screen4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.data.Guest
import kotlinx.android.synthetic.main.item_list_guest.view.*

/** 22/05/2022 Created by: meitaptr */
class Screen4Adapter (
    private val onItemClicked: (Guest) -> Unit,
    var data: List<Guest>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TAG = "Screen4Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_guest, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Logger.e(TAG, "onbindviewholder executed")
//        holder.itemView.tv_package_name.text = data[position].name
        holder.itemView.tv_guest_name.text = "${data[position].first_name} ${data[position].last_name}"

        holder.itemView.setOnClickListener {
            onItemClicked(data[position])
        }
    }


    class ItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    }
}
