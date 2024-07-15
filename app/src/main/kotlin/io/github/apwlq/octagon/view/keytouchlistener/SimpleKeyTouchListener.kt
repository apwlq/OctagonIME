package io.github.apwlq.octagon.view.keytouchlistener

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import io.github.apwlq.octagon.view.message.BaseKeyMessage

class SimpleKeyTouchListener(
    context: Context,
    private val key: BaseKeyMessage,
) : BaseKeyTouchListener(context) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_UP -> {
                sendKeyMessage(key)
            }
        }
        return super.onTouch(view, motionEvent)
    }

}