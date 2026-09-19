package com.example.islamicapp.taps

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class MarginItemDecoration(
    private val spaceSize: Int // تم تغيير النوع من Unit إلى Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // الآن ستعمل عملية الضرب بشكل صحيح لأن spaceSize أصبح رقماً
        val space = (spaceSize * view.resources.displayMetrics.density).roundToInt()

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = space
            }

            left = space
            right = space
            bottom = space
        }
    }
}
