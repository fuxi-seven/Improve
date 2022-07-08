package com.hly.improve

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class PPCircle : View {

    private var mDatas = ArrayList<Float>()
    var mColors = ArrayList<Int>(4)
    var mPaint: Paint = Paint()

    constructor(mContext: Context) : super(mContext)

    constructor(mContext: Context, mAttributeSet: AttributeSet) : super(mContext, mAttributeSet) {
        initPaint()
    }

    private fun initPaint() {
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.color = 0xff44b391.toInt()
    }

    //长宽⼀致
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val mLayoutSize = min(widthSpecSize, heightSpecSize)
        setMeasuredDimension(mLayoutSize, mLayoutSize)
    }

    fun setData(data: ArrayList<Float>, colors: ArrayList<Int>) {
        mDatas = data
        mColors = colors
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mDatas.size == 0) {
            return
        }

        //裁剪圆区域
        val mPath = Path()
        mPath.addCircle(width / 2f, height / 2f, width / 2f * 0.4f, Path.Direction.CW)
        mPath.close()
        canvas?.clipPath(mPath, Region.Op.INTERSECT)
        var total = 0f
        //此处亮点
        mDatas.forEach { total += it }
        val rf = RectF(0f, 0f, width.toFloat(), height.toFloat())
        var startAngle = -90f//起点
        var i = 0
        mDatas.forEach {
            mPaint.color = mColors[i]
            var sweepAngle = it * 360 / total
            canvas?.drawArc(rf, startAngle, sweepAngle, true, mPaint)
            startAngle += sweepAngle
            i++
        }
    }
}