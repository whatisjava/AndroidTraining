package com.whatisjava.training.mediaselect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.whatisjava.training.databinding.ActivityMediaSelectBinding


class MediaSelectActivity : AppCompatActivity() {

    private val TAG = "MediaSelectActivity"

    private lateinit var binding: ActivityMediaSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaSelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            MediaSelectUtil.select(
                this@MediaSelectActivity,
                selectMimeType = SelectMimeType.ofImage(),
                selectionMode = SelectModeConfig.SINGLE,
                maxSelectNum = 1,
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
                    stringBuffer.append("是否压缩:" + media.isCompressed).append("\n")
                    stringBuffer.append("压缩:" + media.compressPath).append("\n")
                    stringBuffer.append("初始路径:" + media.path).append("\n")
                    stringBuffer.append("绝对路径:" + media.realPath).append("\n")
                    stringBuffer.append("是否裁剪:" + media.isCut).append("\n")
                    stringBuffer.append("是否开启原图:" + media.isOriginal).append("\n")
                    stringBuffer.append("原图路径:" + media.originalPath).append("\n")
                    stringBuffer.append("沙盒路径:" + media.sandboxPath).append("\n")
                    stringBuffer.append("水印路径:" + media.watermarkPath).append("\n")
                    stringBuffer.append("视频缩略图:" + media.videoThumbnailPath).append("\n")
                    stringBuffer.append("原始宽高: " + media.width + "x" + media.height).append("\n")
                    stringBuffer.append("裁剪宽高: " + media.cropImageWidth + "x" + media.cropImageHeight).append("\n")
                    stringBuffer.append("文件大小: " + media.size).append("\n")
                }
                binding.textView.text = stringBuffer.toString()
            }
        }

        override fun onCancel() {

        }
    }

}