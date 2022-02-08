package tj.rs.devteam.lineindicator

import android.view.View

/**
 * Created by Rustam Safarov on 2/8/22.
 * github.com/rustamsafarovrs
 */

class LineIndicator : View {

    var count: Int = resources.getInteger(R.integer.default_count)
        set(value) {
            field = value
            this.initLines()
        }

    var itemSpace: Int = resources.getDimensionPixelSize(R.dimen.default_line_space)
        private set

    var radius: Int = resources.getDimensionPixelSize(R.dimen.default_line_radius)
        private set

    var lineWidth: Int = resources.getDimensionPixelSize(R.dimen.default_line_width)
        private set

    var lineColor: Int = R.color.default_line_color
        private set

    var selectedLineColor: Int = R.color.default_selected_line_color
        private set



    private fun initLines() {

    }
}