package com.example.detail.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.detail.R
import com.example.domain.entities.AlbumDataEntities
import kotlinx.android.synthetic.main.item_album.view.*

class ProfileAdapter (private val albumEntitiesList: MutableList<AlbumDataEntities>) :
    RecyclerView.Adapter<ProfileAdapter.MyViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    internal lateinit var context: Context
    lateinit var linearLayoutManager: LinearLayoutManager

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(albumEntitiesList: AlbumDataEntities) {
            itemView.tvAlbumTitle.text = albumEntitiesList.title

            val photoAdapter = PhotoAdapter(albumEntitiesList.photo)
            itemView.rvPhoto.adapter = photoAdapter
            linearLayoutManager = GridLayoutManager(itemView.context, 2)
            itemView.rvPhoto.layoutManager = linearLayoutManager
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View? = null
        when (viewType) {
            ITEM -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_album, parent, false)
            LOADING -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
        }
        context = parent.context
        return MyViewHolder(itemView!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postDataList = albumEntitiesList[position]
        when (getItemViewType(position)) {
            ITEM -> holder.bind(postDataList)
        }
    }

    override fun getItemCount(): Int {
        return albumEntitiesList.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == albumEntitiesList.size - 1 && isLoadingAdded) LOADING else ITEM
    }
}