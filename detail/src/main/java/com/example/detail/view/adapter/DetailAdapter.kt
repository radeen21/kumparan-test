package com.example.detail.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.detail.R
import com.example.domain.entities.CommentEntites
import kotlinx.android.synthetic.main.item_comment.view.*

class DetailAdapter(private val commentEntitiesList: MutableList<CommentEntites>) :
    RecyclerView.Adapter<DetailAdapter.MyViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    internal lateinit var context: Context

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(commentEntitiesList: CommentEntites) {
            itemView.tvUserComment.text = commentEntitiesList.email
            itemView.tvComment.text = commentEntitiesList.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View? = null
        when (viewType) {
            ITEM -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comment, parent, false)
            LOADING -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
        }
        context = parent.context
        return MyViewHolder(itemView!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postDataList = commentEntitiesList[position]
        when (getItemViewType(position)) {
            ITEM -> holder.bind(postDataList)
        }
    }

    override fun getItemCount(): Int {
        return commentEntitiesList.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == commentEntitiesList.size - 1 && isLoadingAdded) LOADING else ITEM
    }
}