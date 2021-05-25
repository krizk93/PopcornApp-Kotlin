package com.krizk.popcornapp

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {

    imageUrl?.let {
        val imageUri = Uri.parse(imageView.context.getString(R.string.base_image_url))
            .buildUpon()
            .appendPath(imageView.context.getString(R.string.image_size_500))
            .appendPath(imageUrl.replace("/",""))
            .build()

        Glide.with(imageView.context)
            .load(imageUri)
            .into(imageView)
    }
}