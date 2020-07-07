package com.infoskillstechnology.sinetop.utils

import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.loadCenterCropImageFromUrl(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}