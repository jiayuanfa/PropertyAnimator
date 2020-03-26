package com.example.propertyanimator

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.annotation.SuppressLint
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var view: View? = null

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 简单的平移动画
         */
        view = findViewById(R.id.mapView)
//        view!!.animate()
//                .translationX(Utils.dpToPx(200f))
//                .rotation(360f)
//                .scaleX(2f)
//                .scaleY(2f)
//                .alpha(0.5f)
//                .setDuration(2000)
//                .start()

        /**
         * 属性动画本质是调用View的属性值的Set方法，所以我们首先要提供一个Set方法
         * 为什么不行呢？
         * 因为界面没有刷新
         * 60Hz->90Hz的刷新过程中，我们没有进行重绘
         * onDraw方法是界面刷新的时候才会调用
         * 他不知道内容发生改变了，怎么办？
         * 我们需要在setRadius方法中通过invalidate方法告诉它
         */
//        val animator: ObjectAnimator = ObjectAnimator.ofFloat(view, "radius", Utils.dpToPx(100f))
//        animator.duration = 2000
//        animator.start()

        /**
         * 单个属性做动画
         */
//        val animator: ObjectAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 180f)
//        animator.duration = 2000
//        animator.start()

        /**
         * 多个属性做动画
         */
//        val bottomFlipAnimator: ObjectAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 30f)
//        bottomFlipAnimator.startDelay = 1000
//        bottomFlipAnimator.duration = 2000
//        bottomFlipAnimator.start()
//
//        val topFlipAnimator: ObjectAnimator = ObjectAnimator.ofFloat(view, "topFlip", -30f)
//        topFlipAnimator.startDelay = 1000
//        topFlipAnimator.duration = 2000
//        topFlipAnimator.start()
//
//        val flipRotation: ObjectAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
//        flipRotation.startDelay = 1000
//        flipRotation.duration = 2000
//        flipRotation.start()

        /**
         * 三个一起运行
         */
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(bottomFlipAnimator, topFlipAnimator, flipRotation)
////        animatorSet.playTogether(bottomFlipAnimator, flipRotation, topFlipAnimator)
//        animatorSet.startDelay = 1000
//        animatorSet.start()

        /**
         * 同一时间对一个View做多个动画
         */
//        val bottomPropertyVH = PropertyValuesHolder.ofFloat("bottomFlip", 45f)
//        val topFlipVH = PropertyValuesHolder.ofFloat("topFlip", -45f)
//        val rotationFlipVH = PropertyValuesHolder.ofFloat("flipRotation", 270f)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(view, bottomPropertyVH, topFlipVH, rotationFlipVH)
//        animator.duration = 2000
//        animator.startDelay = 1000
//        animator.start()

        /**
         * 对一个属性，做多种动画
         * 实现：向右移动，先快再慢再变正常的效果
         * 使用keyFrame来实现
         * 关键帧动画
         * 比如WebView的进度条动画效果
         * fraction: 意思是动画完成度
         * value: 指的是在消耗了百分之多少的时间之后值的进度
         */
//        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", Utils.dp2px(200f))
//        objectAnimator.duration = 2000
//        objectAnimator.startDelay = 1000
//        objectAnimator.start()

//        val length = Utils.dp2px(250f)
//        val keyFrame1 = Keyframe.ofFloat(0f, 0f)
//        val keyFrame2 = Keyframe.ofFloat(0.1f, 0.4f * length);
//        val keyFrame3 = Keyframe.ofFloat(0.9f, 0.6f * length)
//        val keyFrame4 = Keyframe.ofFloat(1f, length)
//        val vh = PropertyValuesHolder.ofKeyframe("translationX", keyFrame1,
//        keyFrame2, keyFrame3, keyFrame4)
//        val animator = ObjectAnimator.ofPropertyValuesHolder(view, vh)
//        animator.startDelay = 1000
//        animator.duration = 2000
//        // 设置动画的插值器为先加速后减速
//        // AccelerateDecelerateInterpolator
//        animator.interpolator = AccelerateDecelerateInterpolator()
//        animator.start()

        val point = PointF(Utils.dp2px(300f), Utils.dp2px(200f))
        val animator = ObjectAnimator.ofObject(
            view, "point", PointFEvaluator()
            , point
        )
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
    }

    /**
     * 声明一个动画运动规则
     * 点
     * 规则就是终点值减去起点值乘以进度百分比
     * 计算出的点返回即可
     */
    internal class PointFEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(
            fraction: Float,
            startValue: PointF,
            endValue: PointF
        ): PointF {
            val x = startValue.x + (endValue.x - startValue.x) * fraction
            val y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x, y)
        }
    }
}
