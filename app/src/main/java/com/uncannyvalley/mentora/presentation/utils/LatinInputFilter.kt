package com.uncannyvalley.mentora.presentation.utils

import android.text.InputFilter
import android.text.Spanned

class LatinInputFilter: InputFilter {
    private val pattern = Regex("[\\p{ASCII}]") // ASCII only

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val input = source?.subSequence(start, end).toString()
        return if (pattern.matches(input)) null else ""
    }
}