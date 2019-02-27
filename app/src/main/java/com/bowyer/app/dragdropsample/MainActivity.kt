package com.bowyer.app.dragdropsample

import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.bowyer.app.dragdropsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        binding.first.setOnTouchListener(DragViewListener(binding.first))
        binding.firstScore.bindData(ScoreValueType.DEFO.type)

        binding.matoArea.setOnDragListener { _, event ->
            event?.let {
                val view = event.localState as TextView
                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        view.visibility = View.INVISIBLE
                    }
                    DragEvent.ACTION_DROP -> {
                        val radius = view.width / 2
                        view.x = event.x - radius
                        view.y = event.y - radius
                        judge(event.x, event.y, view)
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {
                        view.visibility = View.VISIBLE
                    }
                }
            }
            true
        }
    }

    private fun judge(x: Float, y: Float, view: View) {
        val matoX = binding.matoView.getPosX()
        val matoY = binding.matoView.getPosY()
        val matoR = binding.matoView.getRadius()
        val width = view.width / 2

        val a: Float = matoX - x
        val b: Float = matoY - y
        val c: Float = Math.sqrt((a * a).toDouble() + (b * b).toDouble()).toFloat()

        if (c <= matoR + width) {
            binding.firstScore.bindData(ScoreValueType.MARU.type)
        } else {
            binding.firstScore.bindData(ScoreValueType.BATSU.type)
        }
    }

    internal inner class DragViewListener(val view: View) : View.OnTouchListener {

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            event?.let {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        view.visibility = View.INVISIBLE
                        val shadow = View.DragShadowBuilder(view)
                        ViewCompat.startDragAndDrop(view, null, shadow, view, 0)
                    }
                    else -> {
                        // do nothing
                    }
                }
            }
            return true
        }
    }
}
