package com.boardgamegeek.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

import com.boardgamegeek.R
import com.boardgamegeek.util.MathUtils

import java.text.DecimalFormat

class ScoreGraphView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val scorePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val scoreRadius: Float
    private val smallTickHeight: Float
    private val largeTickHeight: Float

    private val lowScoreColor: Int
    private val averageScoreColor: Int
    private val averageWinScoreColor: Int
    private val highScoreColor: Int

    var lowScore: Double = 0.toDouble()
    var averageScore: Double = 0.toDouble()
    var averageWinScore: Double = 0.toDouble()
    var highScore: Double = 0.toDouble()
    private var hasPersonalScores: Boolean = false

    var personalLowScore: Double = 0.toDouble()
        set(value) {
            field = value
            hasPersonalScores = true
        }

    var personalAverageScore: Double = 0.toDouble()
        set(value) {
            field = value
            hasPersonalScores = true
        }

    var personalAverageWinScore: Double = 0.toDouble()
        set(value) {
            field = value
            hasPersonalScores = true
        }

    var personalHighScore: Double = 0.toDouble()
        set(value) {
            field = value
            hasPersonalScores = true
        }


    init {
        barPaint.color = Color.BLACK
        barPaint.strokeWidth = 1f

        scorePaint.strokeWidth = SCORE_STROKE_WIDTH.toFloat()

        textPaint.color = ContextCompat.getColor(getContext(), R.color.secondary_text)
        textPaint.textSize = 8f * getContext().resources.displayMetrics.scaledDensity

        scoreRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, context.resources.displayMetrics)
        smallTickHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, context.resources.displayMetrics)
        largeTickHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, context.resources.displayMetrics)

        lowScoreColor = ContextCompat.getColor(getContext(), R.color.score_low)
        averageScoreColor = ContextCompat.getColor(getContext(), R.color.score_average)
        averageWinScoreColor = ContextCompat.getColor(getContext(), R.color.score_average_win)
        highScoreColor = ContextCompat.getColor(getContext(), R.color.score_high)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val y = (canvas.height / 2).toFloat()
        val left = scoreRadius + SCORE_STROKE_WIDTH
        val right = canvas.width.toFloat() - scoreRadius - SCORE_STROKE_WIDTH.toFloat()

        canvas.drawLine(left, y, right, y, barPaint)

        // ticks
        val scoreSpread = highScore - lowScore
        val tickSpacing = when {
            scoreSpread <= 20 -> 1
            scoreSpread <= 50 -> 5
            scoreSpread <= 200 -> 10
            scoreSpread <= 500 -> 20
            else -> MathUtils.significantDigits((Math.ceil(scoreSpread / 100) * 10).toInt(), 2)
        }
        var tickScore = Math.ceil(lowScore / tickSpacing) * tickSpacing
        while (tickScore <= highScore) {
            val x = ((tickScore - lowScore) / scoreSpread * (right - left) + left).toFloat()
            val tickHeight: Float
            if (tickScore % (5 * tickSpacing) == 0.0) {
                tickHeight = largeTickHeight
                val label = SCORE_FORMAT.format(tickScore)
                val labelWidth = textPaint.measureText(label)
                val labelLeft = MathUtils.constrain(x - labelWidth / 2, 0f, canvas.width - labelWidth)
                canvas.drawText(label, labelLeft, canvas.height.toFloat(), textPaint)
            } else {
                tickHeight = smallTickHeight
            }
            canvas.drawLine(x, y - tickHeight, x, y + tickHeight, barPaint)
            tickScore += tickSpacing.toDouble()
        }

        // score dots
        scorePaint.style = if (hasPersonalScores) Style.STROKE else Style.FILL
        drawScore(canvas, y, left, right, lowScore, lowScoreColor)
        drawScore(canvas, y, left, right, highScore, highScoreColor)
        drawScore(canvas, y, left, right, averageScore, averageScoreColor)
        drawScore(canvas, y, left, right, averageWinScore, averageWinScoreColor)

        if (hasPersonalScores) {
            scorePaint.style = Style.FILL
            drawScore(canvas, y, left, right, personalLowScore, lowScoreColor)
            drawScore(canvas, y, left, right, personalHighScore, highScoreColor)
            drawScore(canvas, y, left, right, personalAverageScore, averageScoreColor)
            drawScore(canvas, y, left, right, personalAverageWinScore, averageWinScoreColor)
        }
    }

    private fun drawScore(canvas: Canvas, y: Float, left: Float, right: Float, score: Double, @ColorInt color: Int) {
        scorePaint.color = color
        val x = ((score - lowScore) / (highScore - lowScore) * (right - left) + left).toFloat()
        canvas.drawCircle(x, y, scoreRadius, scorePaint)
    }

    companion object {
        private val SCORE_FORMAT = DecimalFormat("0")
        private const val SCORE_STROKE_WIDTH = 2
    }
}
