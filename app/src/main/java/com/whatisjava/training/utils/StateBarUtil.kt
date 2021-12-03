package com.whatisjava.training.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.math.ceil


object StateBarUtil {

    /**
     * 获取状态栏高度
     * @return
     */
    fun getStatusBarHeight(activity: Activity): Int {
        var result: Int
        val resourceId: Int = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        result = if (resourceId > 0) {
            activity.resources.getDimensionPixelSize(resourceId)
        } else {
            ceil(((if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 24 else 25) * activity.resources.displayMetrics.density).toDouble()).toInt()
        }
        Log.d("status_bar_height", "$result")
        return result
    }

    /**
     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
     * @param dark 状态栏字体和图标是否为深色
     */
    fun setStatusBarFontDark(dark: Boolean, window: Window) {

        // 小米MIUI
        try {
            val clazz: Class<*> = window.javaClass
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field: Field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag: Int = field.getInt(layoutParams)
            val extraFlagField: Method = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag)
            }
        } catch (e: Exception) {
            Log.d("MiUI", e.localizedMessage)
        }

        // 魅族FlymeUI
        try {
            val window: Window = window
            val lp: WindowManager.LayoutParams = window.attributes
            val darkFlag: Field = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags: Field = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit: Int = darkFlag.getInt(null)
            var value: Int = meizuFlags.getInt(lp)
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            window.attributes = lp
        } catch (e: Exception) {
            Log.d("FlymeUI", e.localizedMessage)
        }

        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
        } else {
            
        }
    }

}