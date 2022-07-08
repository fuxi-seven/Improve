package com.hly.improve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView : View {

    private var currentX = 40f
    private var currentY = 50f

    //定义并创建画笔
    private var p = Paint()

    constructor(context: Context) : super(context)
    constructor(context: Context, set: AttributeSet) : super(context, set)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //设置画笔颜色
        p.color = Color.RED
        //绘制一个小圆
        canvas.drawCircle(currentX, currentY, 20F, p)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //修改currentX,currentY两个属性
        if (event != null) {
            currentX = event.x
        }
        if (event != null) {
            currentY = event.y
        }
        //通知当前组件重绘自己
        invalidate()
        //返回true 表名该处理方法已经处理该事件
        return true
    }
}