package ru.asshands.softwire.tsykunov.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import ru.asshands.softwire.tsykunov.R
import com.bumptech.glide.request.target.Target



fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .fitCenter()
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}


fun ImageView.loadImageWithPb(url: String,progressBar:View) {
    Glide
        .with(this)
        .load(url)
        .fitCenter()
        .error(R.drawable.ic_baseline_broken_image_24)
        .listener(object : RequestListener<Drawable> {
            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .into(this)
}