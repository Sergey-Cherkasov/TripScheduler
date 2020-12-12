package br.svcdev.tripscheduler.common

import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader : IImageLoader<ImageView> {
    override fun loadLogo(airlineName: String?, container: ImageView) {
        val url = "http://pics.avs.io/200/200/$airlineName.png"
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}