package tj.rs.devteam.lineindicator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by Rustam Safarov on 2/8/22.
 * github.com/rustamsafarovrs
 */

class LineIndicator : View {

    private val TAG: String = "LineIndicator"

    private var count: Int = 0

    var itemSpace: Int = resources.getDimensionPixelSize(R.dimen.default_line_space)
        private set

    var radius: Int = resources.getDimensionPixelSize(R.dimen.default_line_radius)
        private set

    var lineWidth: Int = resources.getDimensionPixelSize(R.dimen.default_line_width)
        private set

    var lineColor: Int = ContextCompat.getColor(context, R.color.default_line_color)
        private set

    var selectedLineColor: Int =
        ContextCompat.getColor(context, R.color.default_selected_line_color)
        private set

    val items = mutableListOf<Item>()

    var callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            items.forEachIndexed { index, item ->
                item.isSelected = index == position
            }
            invalidate()
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.LineIndicator, 0, 0)

        itemSpace = typedArray.getDimensionPixelSize(R.styleable.LineIndicator_lineSpace, itemSpace)

        radius = typedArray.getDimensionPixelSize(R.styleable.LineIndicator_cornerRadius, radius)

        lineWidth = typedArray.getDimensionPixelSize(R.styleable.LineIndicator_lineWidth, lineWidth)

        lineColor = typedArray.getColor(R.styleable.LineIndicator_lineColor, lineColor)

        selectedLineColor =
            typedArray.getColor(R.styleable.LineIndicator_selectedLineColor, selectedLineColor)

        typedArray.recycle()

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val itemWidth = (measuredWidth / count) - (itemSpace / (count + 2))

        val selectedBackgroundPaint = Paint().apply {
            style = Paint.Style.FILL
            color = selectedLineColor
        }

        val backgroundPaint = Paint().apply {
            style = Paint.Style.FILL
            color = lineColor
        }

        items.forEachIndexed { index, item ->

            var start = (itemWidth * index)
            val end = start + itemWidth

            val isFirstItem = index == 0

            if (!isFirstItem) {
                Log.i(TAG, "applied space to index = ${index}")
                start += itemSpace
            }

            val rect = RectF(
                start.toFloat(), 0f, end.toFloat(),
                lineWidth.toFloat()
            )

            val paint = if (item.isSelected) selectedBackgroundPaint else backgroundPaint

            canvas?.drawRoundRect(
                rect,
                radius.toFloat(),
                radius.toFloat(),
                paint
            )
        }

    }

    fun setViewPager2(viewPager2: ViewPager2) {
        count = viewPager2.adapter?.itemCount ?: 0
        viewPager2.registerOnPageChangeCallback(callback)
        initLines()
    }

    private fun initLines() {
        this.items.clear()
        items.addAll(List(count) { Item() })
        if (items.size > 0) {
            items[0].isSelected = true
        }
        invalidate()
    }

}