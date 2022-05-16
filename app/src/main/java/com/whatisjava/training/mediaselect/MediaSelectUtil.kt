package com.whatisjava.training.mediaselect

import android.R.attr
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.luck.picture.lib.animators.AnimationType
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.PictureSelectionConfig
import com.luck.picture.lib.config.SelectLimitType
import com.luck.picture.lib.config.VideoQuality
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.engine.CropFileEngine
import com.luck.picture.lib.engine.UriToFileTransformEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.interfaces.OnSelectLimitTipsListener
import com.luck.picture.lib.utils.DateUtils
import com.luck.picture.lib.utils.SandboxTransformUtils
import com.luck.picture.lib.utils.ToastUtils
import com.whatisjava.training.mediaselect.imageEngine.GlideEngine
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File


object MediaSelectUtil {

    private var mContext: Context? = null

    fun select(
        context: Context? = null,
        activity: Activity? = null,
        fragment: Fragment? = null,
        selectMimeType: Int,
        selectionMode: Int,
        selectedData: List<LocalMedia>? = null,
        maxSelectNum: Int? = null,
        minSelectNum: Int? = null,
        recordVideoMaxSecond: Int? = null,
        recordVideoMinSecond: Int? = null,
        onResultCallbackListener: OnResultCallbackListener<LocalMedia>
    ) {
        mContext = context?: (activity?.baseContext?: fragment?.context!!)
        val pictureSelector = when {
            activity != null -> PictureSelector.create(activity)
            fragment != null -> PictureSelector.create(fragment)
            else -> PictureSelector.create(context)
        }
        pictureSelector.openGallery(selectMimeType) // SelectMimeType.ofImage()
//            .setSelectorUIStyle() // 设置相册主题
//            .setLanguage() // 设置相册语言
            .setImageEngine(GlideEngine.createGlideEngine()) // 设置相册图片加载引擎
            .setCompressEngine(ImageFileCompressEngine()) // 设置相册压缩引擎
            .setCropEngine(ImageFileCropEngine()) // 设置相册裁剪引擎
            .setSandboxFileEngine(MeSandboxFileEngine()) // 设置相册沙盒目录拷贝引擎
//            .setOriginalFileEngine() // 设置相册图片原图处理引擎
//            .setExtendLoaderEngine() // 设置相册数据源加载引擎
//            .setCameraInterceptListener() // 拦截相机事件，实现自定义相机
//            .setEditMediaInterceptListener() // 拦截资源编辑事件，实现自定义编辑
//            .setPermissionsInterceptListener() // 拦截相册权限处理事件，实现自定义权限
            .setSelectLimitTipsListener(MeOnSelectLimitTipsListener()) // 拦截选择限制事件，可实现自定义提示
//            .setSelectFilterListener() // 拦截不支持的选择项
            .isCameraForegroundService(true) // 拍照时是否开启一个前台服务
//            .setRequestedOrientation() // 设置屏幕旋转方向
            .setSelectedData(selectedData) // 相册已选数据
            .setRecyclerAnimationMode(AnimationType.ALPHA_IN_ANIMATION) // 相册列表动画效果
            .setImageSpanCount(4) // 相册列表每行显示个数
            .isDisplayCamera(true) // 是否显示相机入口
            .isPageStrategy(true) // 是否开启分页模式
            .isOriginalControl(true)
            .setSelectionMode(selectionMode) // 单选或是多选  SelectModeConfig.SINGLE
            .setMaxSelectNum(maxSelectNum ?: 9) // 图片最大选择数量
            .setMinSelectNum(minSelectNum ?: 1) // 图片最小选择数量
//            .setMaxVideoSelectNum() // 视频最大选择数量
//            .setMinVideoSelectNum() // 视频最小选择数量
            .setRecordVideoMaxSecond(recordVideoMaxSecond ?: 30) // 视频录制最大时长
            .setRecordVideoMinSecond(recordVideoMinSecond ?: 3) // 视频录制最小时长
//            .setFilterVideoMaxSecond() // 过滤视频最大时长
            .setFilterVideoMinSecond(3) // 过滤视频最小时长
//            .setSelectMaxDurationSecond() // 选择最大时长视频或音频
//            .setSelectMinDurationSecond() // 选择最小时长视频或音频
            .setVideoQuality(VideoQuality.VIDEO_QUALITY_HIGH) // 系统相机录制视频质量
            .isQuickCapture(true) // 使用系统摄像机录制后，是否支持使用系统播放器立即播放视频
//            .isPreviewAudio() // 是否支持音频预览
            .isPreviewImage(true) // 是否支持预览图片
            .isPreviewVideo(true) // 是否支持预览视频
            .isPreviewFullScreenMode(true) // 预览点击全屏效果
            .isEmptyResultReturn(true) // 支持未选择返回
            .isWithSelectVideoImage(true) // 是否支持视频图片同选
            .isSelectZoomAnim(true) // 选择缩略图缩放效果
            .isOpenClickSound(false) // 是否开启点击音效
            .isCameraAroundState(false) // 是否开启前置摄像头；系统相机 只支持部分机型
            .isCameraRotateImage(true) // 拍照是否纠正旋转图片
            .isGif(true) // 是否显示gif文件
            .isWebp(true) // 是否显示webp文件
            .isBmp(true) // 是否显示bmp文件
//            .isHidePreviewDownload(true) // 是否隐藏预览下载功能
//            .isAutoScalePreviewImage(true) // 预览图片自动放大充满屏幕
//            .setOfAllCameraType() // isWithSelectVideoImage模式下相机优先使用权
            .isMaxSelectEnabledMask(true) // 达到最大选择数是否开启禁选蒙层
            .isSyncCover(false) // isPageModel模式下是否强制同步封面，默认false
            .isAutomaticTitleRecyclerTop(true) // 点击相册标题是否快速回到第一项
            .isFastSlidingSelect(true) // 快速滑动选择
            .isDirectReturnSingle(false) // 单选时是否立即返回
            .setCameraImageFormat("JPEG") // 拍照图片输出格式
            .setCameraImageFormatForQ("JPEG") // 拍照图片输出格式，Android Q以上
            .setCameraVideoFormat("MP4") // 拍照视频输出格式
            .setCameraVideoFormatForQ("MP4") // 拍照视频输出格式，Android Q以上
//            .setOutputCameraDir() // 使用相机输出路径
//            .setOutputAudioDir() // 使用录音输出路径
//            .setOutputCameraImageFileName() // 图片输出文件名
//            .setOutputCameraVideoFileName() // 视频输出文件名
//            .setOutputAudioFileName() // 录音输出文件名
//            .setQuerySandboxDir() // 查询指定目录下的资源
//            .isOnlyObtainSandboxDir() // 是否只查询指定目录下的资源
//            .setFilterMaxFileSize() // 过滤最大文件
//            .setFilterMinFileSize(1) // 过滤最小文件
//            .setSelectMaxFileSize() // 最大可选文件大小
//            .setSelectMinFileSize() // 最小可选文件大小
//            .setQueryOnlyMimeType() // 查询指定文件类型
//            .setSkipCropMimeType() // 跳过不需要裁剪的类型
            .forResult(onResultCallbackListener)
    }

    /**
     * 拦截自定义提示
     */
    private class MeOnSelectLimitTipsListener : OnSelectLimitTipsListener {
        override fun onSelectLimitTips(context: Context, config: PictureSelectionConfig, limitType: Int): Boolean {
            if (limitType == SelectLimitType.SELECT_NOT_SUPPORT_SELECT_LIMIT) {
                ToastUtils.showToast(context, "暂不支持的选择类型")
                return true
            }
            return false
        }
    }

    /**
     * 自定义沙盒文件处理
     */
    private class MeSandboxFileEngine : UriToFileTransformEngine {
        override fun onUriToFileAsyncTransform(context: Context?, srcPath: String?, mineType: String?, call: OnKeyValueResultCallbackListener?) {
            call?.onCallback(srcPath, SandboxTransformUtils.copyPathToSandbox(context, srcPath, mineType))
        }
    }

    /**
     * 自定义压缩
     */
    private class ImageFileCompressEngine : CompressFileEngine {
        override fun onStartCompress(context: Context, source: ArrayList<Uri>, call: OnKeyValueResultCallbackListener) {
            Luban.with(context).load(source).ignoreBy(100).setRenameListener { filePath ->
                val indexOf = filePath.lastIndexOf(".")
                val postfix = if (indexOf != -1) filePath.substring(indexOf) else ".jpg"
                DateUtils.getCreateFileName("CMP_") + postfix
            }.setCompressListener(object : OnNewCompressListener {
                override fun onStart() {}
                override fun onSuccess(source: String, compressFile: File) {
                    if (call != null) {
                        call.onCallback(source, compressFile.absolutePath)
                    }
                }

                override fun onError(source: String, e: Throwable) {
                    if (call != null) {
                        call.onCallback(source, null)
                    }
                }
            }).launch()
        }
    }

    /**
     * 创建自定义输出目录
     *
     * @return
     */
    private fun getSandboxPath(): String {
        val externalFilesDir = mContext?.getExternalFilesDir("")
        val customFile = File(externalFilesDir?.absolutePath, "Sandbox")
        if (!customFile.exists()) {
            customFile.mkdirs()
        }
        return customFile.absolutePath + File.separator
    }

    private fun getNotSupportCrop(): Array<String> = arrayOf(PictureMimeType.ofGIF(), PictureMimeType.ofWEBP())

    private val aspect_ratio_x = -1
    private val aspect_ratio_y = -1
    /**
     * 配制UCrop，可根据需求自我扩展
     *
     * @return
     */
    private fun buildOptions(): UCrop.Options {
        val options = UCrop.Options()
        options.setHideBottomControls(false)
        options.setFreeStyleCropEnabled(true)
        options.setShowCropFrame(true)
        options.setShowCropGrid(true)
        options.setCircleDimmedLayer(true)
        options.withAspectRatio(aspect_ratio_x.toFloat(), aspect_ratio_y.toFloat())
        options.setCropOutputPathDir(getSandboxPath())
        options.isCropDragSmoothToCenter(false)
        options.isUseCustomLoaderBitmap(false)
        options.setSkipCropMimeType(*getNotSupportCrop())
        options.isForbidCropGifWebp(true)
        options.isForbidSkipMultipleCrop(false)
        options.setMaxScaleMultiplier(100f)
//        if (selectorStyle != null && selectorStyle.getSelectMainStyle().getStatusBarColor() != 0) {
//            val mainStyle: SelectMainStyle = selectorStyle.getSelectMainStyle()
//            val isDarkStatusBarBlack = mainStyle.isDarkStatusBarBlack
//            val statusBarColor = mainStyle.statusBarColor
//            options.isDarkStatusBarBlack(isDarkStatusBarBlack)
//            if (StyleUtils.checkStyleValidity(statusBarColor)) {
//                options.setStatusBarColor(statusBarColor)
//                options.setToolbarColor(statusBarColor)
//            } else {
//                options.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey))
//                options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey))
//            }
//            val titleBarStyle: TitleBarStyle = selectorStyle.getTitleBarStyle()
//            if (StyleUtils.checkStyleValidity(titleBarStyle.titleTextColor)) {
//                options.setToolbarWidgetColor(titleBarStyle.titleTextColor)
//            } else {
//                options.setToolbarWidgetColor(ContextCompat.getColor(getContext(), R.color.ps_color_white))
//            }
//        } else {
//            options.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey))
//            options.setToolbarColor(ContextCompat.getColor(getContext(), R.color.ps_color_grey))
//            options.setToolbarWidgetColor(ContextCompat.getColor(getContext(), R.color.ps_color_white))
//        }
        return options
    }

    /**
     * 自定义裁剪
     */
    private class ImageFileCropEngine : CropFileEngine {
        override fun onStartCrop(fragment: Fragment, srcUri: Uri, destinationUri: Uri, dataSource: java.util.ArrayList<String>, requestCode: Int) {
            val options: UCrop.Options = buildOptions()
            val uCrop = UCrop.of(srcUri, destinationUri, dataSource)
            uCrop.withOptions(options)
            uCrop.setImageEngine(object : UCropImageEngine {
                override fun loadImage(context: Context, url: String, imageView: ImageView) {
                    if (!ImageLoaderUtils.assertValidRequest(context)) {
                        return
                    }
                    Glide.with(context).load(url).override(180, 180).into(imageView)
                }

                override fun loadImage(context: Context, url: Uri, maxWidth: Int, maxHeight: Int, call: UCropImageEngine.OnCallbackListener<Bitmap>?) {
                    if (!ImageLoaderUtils.assertValidRequest(context)) {
                        return
                    }
                    Glide.with(context).asBitmap().override(maxWidth, maxHeight).load(url).into(object : CustomTarget<Bitmap?>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                            call?.onCall(resource)
                        }

                        override fun onLoadFailed(errorDrawable: Drawable?) {
                            call?.onCall(null)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })
                }
            })
            uCrop.start(fragment.activity!!, fragment, requestCode)
        }
    }

}

/*

LocalMedia对象中包含6种路径，具体说明如下:

    getPath(); 指从MediaStore查询返回的路径；SDK_INT >=29 返回content://类型；其他情况返回绝对路径。

    getRealPath(); 绝对路径；SDK_INT >=29且处于沙盒环境下直接使用会报FileNotFoundException异常；

    getOriginalPath(); 原图路径；isOriginalImageControl(true);
    且勾选了原图选项时返回；但SDK_INT >=29且未实现.setSandboxFileEngine();
    直接使用会报FileNotFoundException异常；

    getCompressPath(); 压缩路径；实现了setCompressEngine();时返回；

    getCutPath(); 裁剪或编辑路径；实现了setCropEngine();或setEditMediaInterceptListener();时返回；

    getSandboxPath(); SDK_INT >=29且实现了.setSandboxFileEngine();返回；

    getVideoThumbnailPath(); 视频缩略图，需要实现setVideoThumbnailListener接口

    getWatermarkPath(); 水印地址，需要实现setAddBitmapWatermarkListener接口

    getAvailablePath(); SDK_INT为任意版本都返回直接可用地址(但SDK_INT >29且未开启压缩、裁剪或未实现setSandboxFileEngine，请参考getPath())，但如果你需要具体业务场景下的地址，请参考如上几种路径；

 */