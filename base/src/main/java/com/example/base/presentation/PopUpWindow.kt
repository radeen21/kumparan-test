package com.example.base.presentation

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.base.R
import com.github.chrisbanes.photoview.PhotoView


class PhotoFullPopupWindow(
    ctx: Context,
    v: View?,
    imageUrl: GlideUrl?,
) :
    PopupWindow(
        (ctx.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.popup_photo_full,
            null
        ), ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    ) {
    var view: View
    var mContext: Context
    var photoView: PhotoView
    var loading: ProgressBar

    companion object {
        private val instance: PhotoFullPopupWindow? = null
    }

    init {

        mContext = ctx
        view = contentView
        val closeButton = view.findViewById(R.id.ib_close) as ImageButton
        isOutsideTouchable = true
        isFocusable = true
        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dismiss()
            }

        })
        //---------Begin customising this popup--------------------
        photoView = view.findViewById(R.id.image) as PhotoView
        loading = view.findViewById(R.id.loading)
        loading.visibility = View.GONE
        photoView.setMaximumScale(6f)
        // ImageUtils.setZoomable(imageView);
        //----------------------------
        Glide.with(view)
            .load(imageUrl)
            .into(photoView)
        showAtLocation(v, Gravity.CENTER, 0, 0)
    }
    //------------------------------
}
