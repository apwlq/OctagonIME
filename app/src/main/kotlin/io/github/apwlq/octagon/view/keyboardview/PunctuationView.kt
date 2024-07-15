package io.github.apwlq.octagon.view.keyboardview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.apwlq.octagon.R
import io.github.apwlq.octagon.databinding.PunctuationViewBinding
import io.github.apwlq.octagon.view.message.SpecialKey
import io.github.apwlq.octagon.view.keytouchlistener.FunctionalKeyTouchListener
import io.github.apwlq.octagon.view.keytouchlistener.RepeatKeyTouchListener
import io.github.apwlq.octagon.view.keytouchlistener.SimpleKeyTouchListener
import io.github.apwlq.octagon.view.message.SpecialKeyMessage
import io.github.apwlq.octagon.view.message.StringKeyMessage

class PunctuationView : ConstraintLayout {

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

    private lateinit var binding: PunctuationViewBinding
    private var page = 0

    private fun init() {
        inflate(context, R.layout.punctuation_view, this)
        binding = PunctuationViewBinding.bind(this)
        setPageOrNextPage(0, true)
        setOnTouchListeners()
    }

    fun setPageOrNextPage(newPage: Int? = null, isInitialize: Boolean = false) {
        if (page == newPage && !isInitialize) {
            return
        }
        page = newPage ?: ((page + 1) % PUNCTUATION_LIST.size)
        listOf(
            binding.qKey, binding.wKey, binding.eKey, binding.rKey, binding.tKey, binding.yKey,
            binding.uKey, binding.iKey, binding.oKey, binding.pKey, binding.aKey, binding.sKey,
            binding.dKey, binding.fKey, binding.gKey, binding.hKey, binding.jKey, binding.kKey,
            binding.lKey, binding.zKey, binding.xKey, binding.cKey, binding.vKey, binding.bKey,
            binding.nKey, binding.mKey,
        ).mapIndexed { index, view ->
            view.text = PUNCTUATION_LIST[page][index]
        }
        binding.nextKey.text = resources.getString(
            R.string.key_next_format, page + 1, PUNCTUATION_LIST.size
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListeners() {
        listOf(
            binding.qKey, binding.wKey, binding.eKey, binding.rKey, binding.tKey, binding.yKey,
            binding.uKey, binding.iKey, binding.oKey, binding.pKey, binding.aKey, binding.sKey,
            binding.dKey, binding.fKey, binding.gKey, binding.hKey, binding.jKey, binding.kKey,
            binding.lKey, binding.zKey, binding.xKey, binding.cKey, binding.vKey, binding.bKey,
            binding.nKey, binding.mKey,
        ).map {
            it.apply {
                setOnTouchListener(FunctionalKeyTouchListener(context) {
                    StringKeyMessage(text.toString())
                })
            }
        }
        binding.apply {
            nextKey.setOnTouchListener(
                FunctionalKeyTouchListener(context) {
                    setPageOrNextPage()
                    null
                }
            )
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
            spaceKey.setOnTouchListener(SimpleKeyTouchListener(context, StringKeyMessage(" ")))
            arrowKey.setOnTouchListener(
                SimpleKeyTouchListener(context, SpecialKeyMessage(SpecialKey.ARROW))
            )
            enterKey.setOnTouchListener(
                SimpleKeyTouchListener(context, SpecialKeyMessage(SpecialKey.ENTER))
            )
        }
    }

    companion object {
        private val PUNCTUATION_LIST = listOf(
            listOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                "-", "@", "*", "^", ":", ";", "(", ")", "~",
                "/", "'", "\"", ".", ",", "?", "!",
            ),
            listOf(
                "#", "&", "%", "+", "=", "_", "\\", "|", "<", ">",
                "{", "}", "[", "]", "$", "￡", "¥", "€", "₩",
                "¢", "`", "˚", "•", "®", "©", "¿",
            ),
            listOf(
                "♥", "♡", "◎", "♩", "♬", "♨", "♀", "♂", "☞", "☜",
                "≠", "※", "≒", "♠", "♤", "★", "☆", "♣", "♧",
                "◐", "◆", "◇", "■", "□", "×", "÷",
            ),
            listOf(
                "Ψ", "Ω", "α", "β", "γ", "δ", "ε", "ζ", "η", "θ",
                "∀", "∂", "∃", "∇", "∈", "∋", "∏", "∑", "∝",
                "∞", "∧", "∨", "∩", "∪", "∫", "∬",
            ),
            listOf(
                "←", "↑", "→", "↓", "↔", "↕", "↖", "↗", "↘", "↙",
                "∮", "∴", "∵", "≡", "≤", "≥", "≪", "≫", "⌒",
                "⊂", "⊃", "⊆", "⊇", "℃", "℉", "™",
            ),
        )
    }

}