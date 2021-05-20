package com.krizk.popcornapp

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {

    imageUrl?.let {
        val imageUri = Uri.parse("https://image.tmdb.org/t/p/")
            .buildUpon()
            .appendPath("w500")
            .appendPath(imageUrl.replace("/",""))
            .build()

        Glide.with(imageView.context)
            .load(imageUri)
            .into(imageView)
    }
}