package com.whatisjava.training.translucent

import android.graphics.Rect
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityTranslucentBinding
import com.whatisjava.training.utils.StateBarUtil
import kotlin.math.ceil

class TranslucentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTranslucentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTranslucentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var statusBarHeight: Int
        val decorView = this.window.decorView
        decorView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                decorView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val rect = Rect()
                decorView.getWindowVisibleDisplayFrame(rect)
                statusBarHeight = rect.top
                Log.d("viewTreeObserver height", "$statusBarHeight")
            }
        })

        statusBarHeight = ceil(((if (VERSION.SDK_INT >= VERSION_CODES.M) 24 else 25) * resources.displayMetrics.density).toDouble()).toInt()
        Log.d("ceil height", "$statusBarHeight")

        statusBarHeight = StateBarUtil.getStatusBarHeight(this)
        Log.d("statusBarHeight", "$statusBarHeight")

//        val layoutParams = CollapsingToolbarLayout.LayoutParams(toolbar.layoutParams)
//        layoutParams.setMargins(0, statusBarHeight, 0, 0)
//        toolbar.layoutParams = layoutParams

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                Log.d("state", "$state")
                when (state) {
                    State.EXPANDED -> {
                        StateBarUtil.setStatusBarFontDark(false, window)
                        binding.toolbar.navigationIcon = ContextCompat.getDrawable(baseContext, R.drawable.ic_baseline_arrow_back_24_dark)
                    }
                    State.COLLAPSED -> {
                        StateBarUtil.setStatusBarFontDark(true, window)
                        binding.toolbar.navigationIcon = ContextCompat.getDrawable(baseContext, R.drawable.ic_baseline_arrow_back_24)
                    }
                    else -> {
                        binding.toolbar.navigationIcon = ContextCompat.getDrawable(baseContext, R.drawable.ic_baseline_arrow_back_24_dark)
                    }
                }
            }
        })

    }

}