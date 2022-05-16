package com.whatisjava.training.mediaselect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.whatisjava.training.databinding.ActivityMediaSelectBinding
import com.whatisjava.training.imageloader.ImageLoaderUtil


class MediaSelectActivity : AppCompatActivity() {

    private val TAG = "MediaSelectActivity"

    private lateinit var binding: ActivityMediaSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaSelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
//            MediaSelectUtil.select(
//                this@MediaSelectActivity,
//                selectMimeType = SelectMimeType.ofImage(),
//                selectionMode = SelectModeConfig.SINGLE,
//                crop = true,
//                maxSelectNum = 1,
//                onResultCallbackListener = onResultCallbackListener
//            )
            MediaSelectUtil.select(
                this@MediaSelectActivity,
                selectMimeType = SelectMimeType.ofVideo(),
                selectionMode = SelectModeConfig.SINGLE,
                onResultCallbackListener = onResultCallbackListener
            )
        }
    }

    private val onResultCallbackListener = object : OnResultCallbackListener<LocalMedia> {
        override fun onResult(result: ArrayList<LocalMedia>?) {
            if (!result.isNullOrEmpty()) {
                val stringBuffer = StringBuffer()
                result.forEach { media ->
                    stringBuffer.append("文件名: " + media.fileName).append("\n")
                    stringBuffer.append("文件大小: " + media.size).append("\n")
                    stringBuffer.append("原始宽高: " + media.width + "x" + media.height).append("\n")
                    stringBuffer.append("初始路径:" + media.path).append("\n")
                    stringBuffer.append("绝对路径:" + media.realPath).append("\n")
                    if (media.isOriginal) {
                        stringBuffer.append("原图路径:" + media.originalPath).append("\n")
                    }
                    if (media.isCompressed) {
                        stringBuffer.append("压缩路径:" + media.compressPath).append("\n")
                    }
                    if (media.isCut) {
                        stringBuffer.append("裁剪路径:" + media.cutPath).append("\n")
                        stringBuffer.append("裁剪宽高: " + media.cropImageWidth + "x" + media.cropImageHeight).append("\n")
                    }
                    if (!media.sandboxPath.isNullOrBlank()) {
                        stringBuffer.append("沙盒路径:" + media.sandboxPath).append("\n")
                    }
                    if (!media.videoThumbnailPath.isNullOrBlank()) {
                        stringBuffer.append("视频缩略图路径:" + media.videoThumbnailPath).append("\n")
                    }
                    if (!media.watermarkPath.isNullOrBlank()) {
                        stringBuffer.append("水印路径:" + media.watermarkPath).append("\n")
                    }

                }
                binding.textView.text = stringBuffer.toString()
                ImageLoaderUtil.instance.loadImage(this@MediaSelectActivity, result[0].compressPath?:result[0].videoThumbnailPath, imageView = binding.cutImage)
            }
        }

        override fun onCancel() {

        }
    }

}