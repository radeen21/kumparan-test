package com.example.detail.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.base.presentation.PhotoFullPopupWindow
import com.example.detail.R
import com.example.domain.entities.PhotoDataEntities
import kotlinx.android.synthetic.main.item_photo.view.*


class PhotoAdapter(private val photoEntitiesList: MutableList<PhotoDataEntities?>) :
    RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    internal lateinit var context: Context

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(photoEntitiesList: PhotoDataEntities?, position: Int, size: Int) {
            val url = GlideUrl(
                photoEntitiesList?.url, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )

            if (position == 3) {
                itemView.tvPhotosCounter.text = "+${size - 3}"
            }

            Glide.with(itemView)
                .load(url)
                .into(itemView.ivPhoto)

            itemView.ivPhoto.setOnClickListener {
                PhotoFullPopupWindow(itemView.context, itemView, url )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View? = null
        when (viewType) {
            ITEM -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
            LOADING -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
        }
        context = parent.context
        return MyViewHolder(itemView!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postDataList = photoEntitiesList[position]
        when (getItemViewType(position)) {
            ITEM -> holder.bind(postDataList, position, photoEntitiesList.size)
        }
    }

    override fun getItemCount(): Int {
        return if(photoEntitiesList.size > 4) 4 else photoEntitiesList.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == photoEntitiesList.size - 1 && isLoadingAdded) LOADING else ITEM
    }
}