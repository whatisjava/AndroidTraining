package com.whatisjava.training.animation

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.whatisjava.training.databinding.ActivityDynamicAnimationBinding

class DynamicAnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicAnimationBinding

    private companion object Params {
        const val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
    }

    lateinit var xAnimation: SpringAnimation
    lateinit var yAnimation: SpringAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicAnimationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // create X and Y animations for view's initial position once it's known
        binding.containerLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
//                xAnimation = createSpringAnimation(
//                    movingView, SpringAnimation.X, movingView.x, STIFFNESS, DAMPING_RATIO
//                )
                yAnimation = createSpringAnimation(
                    binding.containerLayout, SpringAnimation.Y, binding.containerLayout.y, STIFFNESS, DAMPING_RATIO
                )
                binding.containerLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        var dX = 0f
        var dY = 0f
        binding.containerLayout.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    // capture the difference between view's top left corner and touch point
//                    dX = view.x - event.rawX
                    dY = view.y - event.rawY

                    // cancel animations so we can grab the view during previous animation
//                    xAnimation.cancel()
                    yAnimation.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    //  a different approach would be to change the view's LayoutParams.
                    binding.containerLayout.animate()
//                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
                MotionEvent.ACTION_UP -> {
//                    xAnimation.start()
                    yAnimation.start()
                }
            }
            true
        }
    }

    fun createSpringAnimation(
        view: View,
        property: DynamicAnimation.ViewProperty,
        finalPosition: Float,
        stiffness: Float,
        dampingRatio: Float
    ): SpringAnimation {
        val animation = SpringAnimation(view, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRatio
        animation.spring = spring
        return animation
    }
}