package com.cred.assignment.utility

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class OnSwipeTouchListener internal constructor(
    ctx: Context,
    view: View,
    listener: onSwipeListener
) :
    View.OnTouchListener {
    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private val gestureDetector: GestureDetector
    var context: Context
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > Companion.SWIPE_THRESHOLD && Math.abs(
                            velocityX
                        ) > Companion.SWIPE_VELOCITY_THRESHOLD
                    ) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > Companion.SWIPE_THRESHOLD && Math.abs(
                        velocityY
                    ) > Companion.SWIPE_VELOCITY_THRESHOLD
                ) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }


    }

    fun onSwipeRight() {
        onSwipe?.swipeRight()
    }

    fun onSwipeLeft() {
        onSwipe?.swipeLeft()
    }

    fun onSwipeTop() {
        onSwipe?.swipeUp()
    }

    fun onSwipeBottom() {
        onSwipe?.swipeBottom()
    }

    interface onSwipeListener {
        fun swipeRight()
        fun swipeUp()
        fun swipeBottom()
        fun swipeLeft()
    }

    var onSwipe: onSwipeListener? = null

    init {
        gestureDetector = GestureDetector(ctx, GestureListener())
        view.setOnTouchListener(this)
        context = ctx
        onSwipe = listener
    }
}