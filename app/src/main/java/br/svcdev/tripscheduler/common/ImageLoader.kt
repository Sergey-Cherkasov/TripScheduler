package br.svcdev.tripscheduler.common

import android.widget.ImageView
import br.svcdev.tripscheduler.common.interfaces.IImageLoader
import com.bumptech.glide.Glide

class ImageLoader : IImageLoader<ImageView> {
    override fun loadLogo(airlineName: String?, container: ImageView, lenght: Int, width: Int) {
        val url = "http://pics.avs.io/$lenght/$width/$airlineName.png"
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}