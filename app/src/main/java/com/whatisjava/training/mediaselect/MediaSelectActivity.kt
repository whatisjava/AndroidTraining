package com.whatisjava.training.mediaselect

import android.R
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.PictureSelectionConfig.selectorStyle
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.engine.*
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.utils.SdkVersionUtils
import com.luck.picture.lib.utils.StyleUtils
import com.whatisjava.training.databinding.ActivityMediaSelectBinding
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine
import top.zibin.luban.*


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
                activity = this@MediaSelectActivity,
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
                    Log.i(TAG, "文件名: " + media.getFileName());
                    Log.i(TAG, "是否压缩:" + media.isCompressed());
                    Log.i(TAG, "压缩:" + media.getCompressPath());
                    Log.i(TAG, "初始路径:" + media.getPath());
                    Log.i(TAG, "绝对路径:" + media.getRealPath());
                    Log.i(TAG, "是否裁剪:" + media.isCut());
                    Log.i(TAG, "裁剪:" + media.getCutPath());
                    Log.i(TAG, "是否开启原图:" + media.isOriginal());
                    Log.i(TAG, "原图路径:" + media.getOriginalPath());
                    Log.i(TAG, "沙盒路径:" + media.getSandboxPath());
                    Log.i(TAG, "水印路径:" + media.getWatermarkPath());
                    Log.i(TAG, "视频缩略图:" + media.getVideoThumbnailPath());
                    Log.i(TAG, "原始宽高: " + media.getWidth() + "x" + media.getHeight());
                    Log.i(TAG, "裁剪宽高: " + media.getCropImageWidth() + "x" + media.getCropImageHeight());
                    Log.i(TAG, "文件大小: " + media.getSize());

                    stringBuffer.append(media.availablePath).append("\n")
                }
                binding.textView.text = stringBuffer.toString()
            }
        }

        override fun onCancel() {

        }
    }

}