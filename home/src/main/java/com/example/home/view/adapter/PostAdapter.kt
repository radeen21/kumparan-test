package com.example.home.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.PostEntites
import com.example.home.R
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private val postDataList: MutableList<PostEntites>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private var isLoadingAdded = false
    internal lateinit var context: Context

    var onClickListener: OnClickListener? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(postEntites: PostEntites) {
            itemView.tvTitle.text = postEntites.title
            itemView.tvBody.text = postEntites.body
            itemView.tvAuthorName.text = postEntites.user?.name
            itemView.tvAuthorNameIcon.text = postEntites.user?.name?.first().toString()
            itemView.tvAuthorCompany.text = postEntites.user?.company?.name
        }
        fun setOnClickListener(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView: View? = null
        when (viewType) {
            ITEM -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            LOADING -> itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)
        }
        context = parent.context
        return MyViewHolder(itemView!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val postDataList = postDataList[position]
        when (getItemViewType(position)) {
            ITEM -> holder.bind(postDataList)
        }
        holder.setOnClickListener(View.OnClickListener {
            onClickListener?.onItemClicked(
                postDataList
            )
        })
    }

    interface OnClickListener {
        fun onItemClicked(data: PostEntites?)
    }

    override fun getItemCount(): Int {
        return postDataList.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == postDataList.size - 1 && isLoadingAdded) LOADING else ITEM
    }

}