package mvpdemo.notemvp.com.tungvuong.custom

import android.content.Context
import android.support.v7.widget.GridLayoutManager

class GridLayoutCustom(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {

    override fun isLayoutRTL(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
