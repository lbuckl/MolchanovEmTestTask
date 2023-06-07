package com.molchanov.coreui.utils

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.molchanov.coreui.R

fun View.vision(flag: Int) {
    val vision = this.visibility

    if (flag != vision) {
        this.visibility = flag
    }
}

fun ImageView.loadImageFromUrl(url: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo_vector)
        .into(this)
}

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo_vector)
        .into(this)
}

//region Функции для работы со span
/**
 * Функция ищет в [text] все слова списка[searchWords] и раскрашивает их в цвет [color]
 * @param color - цвет в формате int
 * @param spanFlag - флаг библиотеки Span (например Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
 * вовращает SpannableString
 */
fun setSpanColorByWord(
    text: SpannableString,
    searchWords: List<String>,
    color: Int,
    spanFlag: Int
): SpannableString {

    text.toString().apply {
        searchWords.forEach { word ->
            this.indexesOf(word, true).forEach {
                text.setSpan(
                    ForegroundColorSpan(
                        color
                    ),
                    it, it + word.length,
                    spanFlag
                )
            }
        }
    }

    return text
}

//Фунция ищет слово в тексте и возвращает массив первых индексов
fun String.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> =
    (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
        .findAll(this).map { it.range.first }.toList()
//endregion
