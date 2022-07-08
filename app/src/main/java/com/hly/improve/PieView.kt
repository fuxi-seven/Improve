package com.hly.improve

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.text.DecimalFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin

class PieView : View {

    /**
     * 使用wrap_content时默认的尺寸
     */
    private val defaultWidth = 800
    private val defaultHeight = 800

    /**
     * 中心坐标
     */
    private var centerX = 0
    private var centerY = 0

    /**
     * 半径
     */
    private var radius = 0f

    /**
     * 弧形外接矩形
     */
    private var rectF: RectF? = null

    /**
     * 中间文本的大小
     */
    private val centerTextBound = Rect()

    /**
     * 数据文本的大小
     */
    private val dataTextBound = Rect()

    /**
     * 扇形画笔
     */
    private var mArcPaint: Paint? = null

    /**
     * 中心文本画笔
     */
    private var centerTextPaint: Paint? = null

    /**
     * 数据画笔
     */
    private var dataPaint: Paint? = null

    /**
     * 数据源数字数组
     */
    private lateinit var numbers: IntArray

    /**
     * 数据源名称数组
     */
    private lateinit var names: Array<String>

    /**
     * 数据源总和
     */
    private var sum = 0

    /**
     * 颜色数组
     */
    private lateinit var colors: IntArray

    private val random = Random()

    //自定义属性 Start

    /**
     * 中间字体大小
     */
    private var centerTextSize = 80f

    /**
     * 数据字体大小
     */
    private var dataTextSize = 30f

    /**
     * 中间字体颜色
     */
    private var centerTextColor = Color.BLACK

    /**
     * 数据字体颜色
     */
    private var dataTextColor = Color.BLACK

    /**
     * 圆圈的宽度
     */
    private var circleWidth = 100f

    //自定义属性 End

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PieView)
        centerTextSize = typedArray.getDimension(R.styleable.PieView_centerTextSize, centerTextSize)
        dataTextSize = typedArray.getDimension(R.styleable.PieView_dataTextSize, dataTextSize)
        circleWidth = typedArray.getDimension(R.styleable.PieView_circleWidth, circleWidth)
        centerTextColor = typedArray.getColor(R.styleable.PieView_centerTextColor, centerTextColor)
        dataTextColor = typedArray.getColor(R.styleable.PieView_dataTextColor, dataTextColor)
        typedArray.recycle()
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        mArcPaint = Paint()
        mArcPaint!!.strokeWidth = circleWidth
        mArcPaint!!.isAntiAlias = true
        mArcPaint!!.style = Paint.Style.STROKE

        centerTextPaint = Paint()
        centerTextPaint!!.textSize = centerTextSize
        centerTextPaint!!.isAntiAlias = true
        centerTextPaint!!.color = centerTextColor

        dataPaint = Paint()
        dataPaint!!.strokeWidth = 2f
        dataPaint!!.textSize = dataTextSize
        dataPaint!!.isAntiAlias = true
        dataPaint!!.color = dataTextColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (measureWidthMode == MeasureSpec.AT_MOST
            && measureHeightMode == MeasureSpec.AT_MOST
        ) {
            setMeasuredDimension(defaultWidth, defaultHeight)
        } else if (measureWidthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(defaultWidth, measureHeightSize)
        } else if (measureHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(measureWidthSize, defaultHeight)
        } else {
            setMeasuredDimension(measureWidthSize, measureHeightSize)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = measuredWidth / 2
        centerY = measuredHeight / 2
        //设置半径为宽高最小值的1/4
        radius = (measuredWidth.coerceAtMost(measuredHeight) / 4).toFloat()//Math.min()
        //设置扇形外接矩形
        rectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        calculateAndDraw(canvas)
    }

    /**
     * 计算比例并且绘制扇形和数据
     */
    private fun calculateAndDraw(canvas: Canvas) {
        if (numbers.isEmpty()) {
            return
        }
        //扇形开始度数
        var startAngle = 0
        //所占百分比
        var percent: Float
        //所占度数
        var angle: Float
        for (i in numbers.indices) {
            percent = numbers[i] / sum.toFloat()
            //获取百分比在360中所占度数
            angle = if (i == numbers.size - 1) { //保证所有度数加起来等于360
                (360 - startAngle).toFloat()
            } else {
                ceil((percent * 360).toDouble()).toFloat()
            }
            //绘制第i段扇形
            drawArc(canvas, startAngle.toFloat(), angle, colors[i])
            startAngle += angle.toInt()

            //绘制数据
            if (numbers[i] <= 0) {
                continue
            }
            //当前弧线中心点相对于纵轴的夹角度数,由于扇形的绘制是从三点钟方向开始，所以加90
            val arcCenterDegree = 90 + startAngle - angle / 2
            drawData(canvas, arcCenterDegree, i, percent)
        }
        //绘制中心数字总和
        canvas.drawText(sum.toString() + "", (centerX - centerTextBound.width() / 2).toFloat(),
            (centerY + centerTextBound.height() / 2).toFloat(), centerTextPaint!!)
    }

    /**
     * 计算每段弧度的中心坐标
     *
     * @param degree 当前扇形中心度数
     */
    private fun calculatePosition(degree: Float): FloatArray {
        //由于Math.sin(double a)中参数a不是度数而是弧度，所以需要将度数转化为弧度
        //而Math.toRadians(degree)的作用就是将度数转化为弧度
        //sin 一二正，三四负 sin（180-a）=sin(a)
        //扇形弧线中心点距离圆心的x坐标
        val x = (sin(Math.toRadians(degree.toDouble())) * radius).toFloat()
        //cos 一四正，二三负
        //扇形弧线中心点距离圆心的y坐标
        val y = (cos(Math.toRadians(degree.toDouble())) * radius).toFloat()

        //每段弧度的中心坐标(扇形弧线中心点相对于view的坐标)
        val startX = centerX + x
        val startY = centerY - y
        val position = FloatArray(2)
        position[0] = startX
        position[1] = startY
        return position
    }

    /**
     * 绘制数据
     *
     * @param canvas  画布
     * @param degree  第i段弧线中心点相对于纵轴的夹角度数
     * @param i       第i段弧线
     * @param percent 数据百分比
     */
    private fun drawData(canvas: Canvas, degree: Float, i: Int, percent: Float) {
        //弧度中心坐标
        val startX = calculatePosition(degree)[0]
        val startY = calculatePosition(degree)[1]

        //获取名称文本大小
        dataPaint!!.getTextBounds(names[i], 0, names[i].length, dataTextBound)
        //绘制名称数据，20为纵坐标偏移量
        canvas.drawText(names[i], startX - dataTextBound.width() / 2,
            startY + dataTextBound.height() / 2 - 20, dataPaint!!)

        //拼接百分比并获取文本大小
        val df = DecimalFormat("0.0")
        val percentString = df.format((percent * 100).toDouble()) + "%"
        dataPaint!!.getTextBounds(percentString, 0, percentString.length, dataTextBound)

        //绘制百分比数据，20为纵坐标偏移量
        canvas.drawText(percentString, startX - dataTextBound.width() / 2,
            startY + dataTextBound.height() * 2 - 20, dataPaint!!)
    }

    /**
     * 绘制扇形
     *
     * @param canvas     画布
     * @param startAngle 开始度数
     * @param angle      扇形的度数
     * @param color      颜色
     */
    private fun drawArc(canvas: Canvas, startAngle: Float, angle: Float, color: Int) {
        mArcPaint!!.color = color
        //-0.5和+0.5是为了让每个扇形之间没有间隙
        canvas.drawArc(rectF!!, startAngle - 0.5f, angle + 0.5f, false, mArcPaint!!)
    }

    /**
     * 生成随机颜色
     */
    private fun randomColor(): Int {
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        return Color.rgb(red, green, blue)
    }

    /**
     * 设置数据
     *
     * @param numbers 数字数组
     * @param names   名称数组
     */
    fun setData(numbers: IntArray?, names: Array<String>?) {
        if (numbers == null || numbers.isEmpty() || names == null || names.isEmpty()) {
            return
        }
        if (numbers.size != names.size) {
            //名称个数与数字个数不相等
            return
        }
        this.numbers = numbers
        this.names = names
        colors = IntArray(numbers.size)
        var i = 0
        this.numbers.forEach {
            //计算总和
            sum += it
            //随机颜色
            colors[i] = randomColor()
            i++
        }
        // 另外一种方式
        /*for (i in this.numbers!!.indices) {
            //计算总和
            sum += numbers[i]
            //随机颜色
            colors[i] = randomColor()
        }*/
        //计算总和数字的宽高
        centerTextPaint!!.getTextBounds(sum.toString() + "", 0, (sum.toString() + "").length, centerTextBound)
        invalidate()
    }
}