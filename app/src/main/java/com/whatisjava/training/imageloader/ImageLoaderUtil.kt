package com.whatisjava.training.imageloader

import android.content.Context
import android.net.Uri
import android.os.Build
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.whatisjava.training.R
import java.io.File

class ImageLoaderUtil private constructor(private val imageLoadStrategy: (Context, Any, Int, Int?, ImageView) -> Unit) {

    fun loadImage(context: Context, imageRes: Any, placeholder: Int, error: Int? = null, imageView: ImageView) =
        imageLoadStrategy.invoke(context, imageRes, placeholder, error, imageView)

    companion object {
        val instance: ImageLoaderUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageLoaderUtil(::glideStrategy)
        }
    }
}

fun picassoStrategy(context: Context, imageRes: Any, placeholder: Int, error: Int? = null, imageView: ImageView) {
    when (imageRes) {
        is String -> {
            Picasso.get().load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
        }
        is Int -> {
            Picasso.get().load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
        }
        is File -> {
            Picasso.get().load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
        }
        is Uri -> {
            Picasso.get().load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
        }
        else -> {

        }
    }
}

fun glideStrategy(context: Context, imageRes: Any, placeholder: Int, error: Int? = null, imageView: ImageView) {
    Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//    when(imageRes){
//        is String -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        is Int -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        is Drawable -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        is File -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        is Bitmap -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        is Uri -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//        else -> {
//            Glide.with(context).load(imageRes).placeholder(placeholder).error(error ?: R.drawable.ic_wait).into(imageView)
//        }
//    }
}

fun coilStrategy(context: Context, imageRes: Any, placeholder: Int, error: Int? = null, imageView: ImageView) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
            add(SvgDecoder.Factory())
            add(VideoFrameDecoder.Factory())
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(imageRes)
        .crossfade(true)
        .crossfade(1000)
        .placeholder(placeholder)
        .error(error ?: R.drawable.ic_wait)
        .target(imageView)
        .transformations(RoundedCornersTransformation(8F, 8f, 8f, 8f))
        .build()
    val disposable = imageLoader.enqueue(request)

}