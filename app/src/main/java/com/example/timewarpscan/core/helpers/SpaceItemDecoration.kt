package com.example.timewarpscan.core.helpers

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpaceItemDecoration(val positions: List<Int>) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {

        val margin = 66
        val space = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            margin.toFloat(),
            view.resources.displayMetrics).toInt()
        if (parent.getChildAdapterPosition(view) in positions) {
            outRect.top = 0
            outRect.bottom = space
        }
    }
}