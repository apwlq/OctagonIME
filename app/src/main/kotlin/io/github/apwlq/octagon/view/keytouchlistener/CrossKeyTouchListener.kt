package io.github.apwlq.octagon.view.keytouchlistener

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import io.github.apwlq.octagon.view.message.BaseKeyMessage
import kotlin.math.*

class CrossKeyTouchListener(
    context: Context,
    private val keyList: List<BaseKeyMessage>,
) : BaseKeyTouchListener(context) {

    private var startX: Float = 0f
    private var startY: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = motionEvent.x
                startY = motionEvent.y
            }
            MotionEvent.ACTION_UP -> {
                val currentX = motionEvent.x
                val currentY = motionEvent.y
                val distance = sqrt(
                    (currentX - startX).pow(2) + (currentY - startY).pow(2)
                )
                if (distance > config.gestureThreshold) {
                    val degree = (atan2(currentY - startY, currentX - startX) * 180f) / PI
                    startX = currentX
                    startY = currentY
                    if (abs(degree) < 45f) {
                        sendKeyMessage(keyList[1])
                    } else if (abs(degree) < 135f) {
                        sendKeyMessage(if (degree > 0) keyList[2] else keyList[0])
                    } else {
                        sendKeyMessage(keyList[3])
                    }
                } else {
                    sendKeyMessage(keyList[2])
                }
            }
        }
        return super.onTouch(view, motionEvent)
    }

}