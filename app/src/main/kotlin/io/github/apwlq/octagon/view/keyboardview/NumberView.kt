package io.github.apwlq.octagon.view.keyboardview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.apwlq.octagon.R
import io.github.apwlq.octagon.databinding.NumberViewBinding
import io.github.apwlq.octagon.view.message.SpecialKey
import io.github.apwlq.octagon.view.keytouchlistener.RepeatKeyTouchListener
import io.github.apwlq.octagon.view.keytouchlistener.SimpleKeyTouchListener
import io.github.apwlq.octagon.view.message.SpecialKeyMessage
import io.github.apwlq.octagon.view.message.StringKeyMessage

class NumberView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private lateinit var binding: NumberViewBinding

    private fun init() {
        inflate(context, R.layout.number_view, this)
        binding = NumberViewBinding.bind(this)
        setOnTouchListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListeners() {
        binding.apply {
            plusKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("+")))
            oneKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("1")))
            twoKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("2")))
            threeKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("3")))
            minusKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("-")))
            asteriskKey.setOnTouchListener(
                SimpleKeyTouchListener(context, StringKeyMessage("*"))
            )
            fourKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("4")))
            fiveKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("5")))
            sixKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("6")))
            dotKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage(".")))
            slashKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("/")))
            sevenKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("7")))
            eightKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("8")))
            nineKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("9")))
            backspaceKey.setOnTouchListener(
                RepeatKeyTouchListener(context, SpecialKeyMessage(SpecialKey.BACKSPACE))
            )
            languageKey.setOnTouchListener(
                SimpleKeyTouchListener(context, SpecialKeyMessage(SpecialKey.LANGUAGE))
            )
            hanjaNumberPunctuationKey.setOnTouchListener(
                SimpleKeyTouchListener(
                    context, SpecialKeyMessage(SpecialKey.HANJA_NUMBER_PUNCTUATION)
                )
            )
            zeroKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("0")))
            spaceKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage(" ")))
            enterKey.setOnTouchListener(
                SimpleKeyTouchListener(context, SpecialKeyMessage(SpecialKey.ENTER))
            )
        }
    }

}