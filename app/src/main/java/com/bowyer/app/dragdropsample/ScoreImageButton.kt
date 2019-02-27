package com.bowyer.app.dragdropsample

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import com.bumptech.glide.Glide

class ScoreImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : ImageButton(context, attrs, defStyleAttr) {

    private lateinit var score: String
    fun bindData(score: String) {
        this.score = score
        bindData(ScoreValueType.of(score))
    }

    fun click(): String {
        val scoreValueType =
            when (ScoreValueType.of(score)) {
                ScoreValueType.DEFO -> ScoreValueType.MARU
                ScoreValueType.MARU -> ScoreValueType.BATSU
                ScoreValueType.BATSU -> ScoreValueType.DEFO
            }
        bindData(scoreValueType)
        return scoreValueType.type
    }

    private fun bindData(scoreValueType: ScoreValueType) {
        when (scoreValueType) {
            ScoreValueType.DEFO -> {
                Glide.with(context).load(R.drawable.ic_defo).into(this)
            }
            ScoreValueType.MARU -> {
                Glide.with(context).load(R.drawable.ic_maru).into(this)
            }
            ScoreValueType.BATSU -> {
                Glide.with(context).load(R.drawable.ic_batsu).into(this)
            }
        }
        this.score = scoreValueType.type
    }

    fun getScore(): String {
        return this.score
    }
}
