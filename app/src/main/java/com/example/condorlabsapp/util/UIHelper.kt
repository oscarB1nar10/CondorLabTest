package com.example.condorlabsapp.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.annotation.ColorInt

object UIHelper {

    fun colorText(
        textView: TextView,
        text: String,
        textToColor: String,
        @ColorInt color: Int,
        bold: Boolean = false
    ) {
        val spannable = SpannableString(text)
        spannable.setSpan(
            ForegroundColorSpan(color),
            text.indexOf(textToColor),
            textToColor.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (bold) {
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                text.indexOf(textToColor),
                textToColor.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.setText(spannable, TextView.BufferType.SPANNABLE)
    }
}