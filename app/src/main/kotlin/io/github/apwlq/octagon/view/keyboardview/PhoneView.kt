package io.github.apwlq.octagon.view.keyboardview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.apwlq.octagon.R
import io.github.apwlq.octagon.databinding.PhoneViewBinding
import io.github.apwlq.octagon.view.keytouchlistener.FunctionalKeyTouchListener
import io.github.apwlq.octagon.view.message.SpecialKey
import io.github.apwlq.octagon.view.keytouchlistener.RepeatKeyTouchListener
import io.github.apwlq.octagon.view.keytouchlistener.SimpleKeyTouchListener
import io.github.apwlq.octagon.view.message.SpecialKeyMessage
import io.github.apwlq.octagon.view.message.StringKeyMessage

class PhoneView : ConstraintLayout {

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

    private lateinit var binding: PhoneViewBinding
    private var page = 0

    private fun init() {
        inflate(context, R.layout.phone_view, this)
        binding = PhoneViewBinding.bind(this)
        setPageOrNextPage(0, true)
        setOnTouchListeners()
    }

    fun setPageOrNextPage(newPage: Int? = null, isInitialize: Boolean = false) {
        if (page == newPage && !isInitialize) {
            return
        }
        page = newPage ?: ((page + 1) % KEY_LIST.size)
        listOf(
            binding.oneKey, binding.twoKey, binding.threeKey, binding.fourKey, binding.fiveKey,
            binding.sixKey, binding.sevenKey, binding.eightKey, binding.nineKey, binding.zeroKey,
        ).mapIndexed { index, view ->
            view.text = KEY_LIST[page][index][0]
            view.tag = KEY_LIST[page][index][1]
        }
        binding.punctuationKey.text = resources.getString(
            if (page == 0) R.string.key_punctuation else R.string.key_one_two_three
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListeners() {
        listOf(
            binding.oneKey, binding.twoKey, binding.threeKey, binding.fourKey, binding.fiveKey,
            binding.sixKey, binding.sevenKey, binding.eightKey, binding.nineKey, binding.zeroKey,
        ).map {
            it.apply {
                setOnTouchListener(FunctionalKeyTouchListener(context) {
                    StringKeyMessage(tag as String)
                })
            }
        }
        binding.apply {
            backspaceKey.setOnTouchListener(
                RepeatKeyTouchListener(context, SpecialKeyMessage(SpecialKey.BACKSPACE))
            )
            minusKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage("-")))
            dotKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage(".")))
            punctuationKey.setOnTouchListener(
                FunctionalKeyTouchListener(context) {
                    setPageOrNextPage()
                    null
                }
            )
            spaceKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage(" ")))
            enterKey.setOnTouchListener(
                SimpleKeyTouchListener(context, SpecialKeyMessage(SpecialKey.ENTER))
            )
        }
    }

    companion object {
        private val KEY_LIST = listOf(
            listOf(
                listOf("1", "1"), listOf("2", "2"), listOf("3", "3"),
                listOf("4", "4"), listOf("5", "5"), listOf("6", "6"),
                listOf("7", "7"), listOf("8", "8"), listOf("9", "9"),
                listOf("0", "0"),
            ),
            listOf(
                listOf("(", "("), listOf("/", "/"), listOf(")", ")"),
                listOf("N", "N"), listOf("Pause", ","), listOf(",", ","),
                listOf("*", "*"), listOf("Wait", ";"), listOf("#", "#"),
                listOf("+", "+"),
            ),
        )
    }

}