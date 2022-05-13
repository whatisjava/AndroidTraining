package com.whatisjava.training.imageloader

import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.videoFrameMillis
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.squareup.picasso.Picasso
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityImageLoadBinding

class ImageLoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageLoadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageLoadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // https://sp.simullink.com/FhvXiuJi1xpKoiDETrKUEjJPsWTy

        val imageUrl = "https://sp.simullink.com/FkOlv_Pxmrv6KzFSCAzPrQWLxmRT"
        val videoUrl = "https://ae.simullink.com/lhzUyDEfgfwxlQfpYVj1vF9r1q4-_m3u8"

        binding.simpleDraweeView.setImageURI(imageUrl)

        val picasso = Picasso.get()
        picasso.setIndicatorsEnabled(true)
        picasso.load(imageUrl).placeholder(R.drawable.bg_default_image).error(R.drawable.bg_default_image).into(binding.imageView)

        val request = ImageRequest.Builder(this)
            .data(imageUrl)
            .crossfade(true)
            .crossfade(1000)
            .target(binding.imageView1)
            .transformations(RoundedCornersTransformation(8F, 8f, 8f, 8f))
            .build()
        val disposable = imageLoader.enqueue(request)
        // Cancel the request.
//        disposable.dispose()

//        val result = imageLoader.execute(request)

//        binding.imageView1.load(imageUrl) {
//            crossfade(true)
//            placeholder(R.drawable.bg_default_image)
//            transformations()
//        }

        val imageLoader = ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
                add(SvgDecoder.Factory())
                add(VideoFrameDecoder.Factory())
            }
            .build()

        val gifRequest = ImageRequest.Builder(this)
            .data(R.drawable.success)
            .target(binding.imageView2)
            .build()
        imageLoader.enqueue(gifRequest)

        val svgRequest = ImageRequest.Builder(this)
            .data(R.drawable.ic_wait)
            .target(binding.imageView3)
            .build()
        imageLoader.enqueue(svgRequest)








//        Blurry.with(this)
//            .radius(25)
//            .sampling(8)
//            .color(Color.argb(66, 255, 255, 0))
//            .async()
//            .animate(500)
//            .onto(binding.linearLayout)

        // Sync
//        val bitmap = Blurry.with(this)
//            .radius(10)
//            .sampling(8)
//            .capture(binding.imageView).get()
//        binding.imageView1.setImageDrawable(BitmapDrawable(resources, bitmap))

        // Async
//        Blurry.with(this)
//            .radius(25)
//            .sampling(4)
//            .color(Color.argb(66, 255, 255, 0))
//            .capture(binding.imageView)
//            .getAsync {
//                binding.imageView1.setImageDrawable(BitmapDrawable(resources, it))
//            }
    }

}