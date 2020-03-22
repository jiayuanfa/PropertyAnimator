package com.example.propertyanimator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.propertyanimator.Utils;

public class CameraView extends View {

    /**
     * 提取View的各个关键属性，用来做动画
     */
    // 上下翻起来的角度
    float topFlip = 0;
    float bottomFlip = 0;

    // 折线的角度
    float flipRotation = 0;

    private static float IMAGE_WIDTH = Utils.dp2px(100f);
    private static float OFFSET = Utils.dp2px(0f);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Camera camera = new Camera();

    Bitmap bitmap;
    {
        bitmap = Utils.getAvatar(getResources(), (int)IMAGE_WIDTH);

        /**
         * 设置Camera的位置
         * -8指的是8英寸 72个像素
         * -8 = 8 * 72 = 576像素
         * 后面乘以像素比则可以是不同像素密度的手机实现相同的翻转效果
         */
        camera.setLocation(0, 0, Utils.getZForCamera());
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 切出来1/4的图片
         */
//        canvas.clipRect(0, 0, OFFSET + IMAGE_WIDTH / 2, OFFSET + IMAGE_WIDTH / 2);

        /**
         * 绘制之前先保存画布
         */
        canvas.save();
        /**
         * 使用Camera搞出三维坐标系
         * 应用到Canvas上面
         * 先移动Canvas至原点
         * camera完毕，再移动回去即可
         */
        canvas.translate(OFFSET + IMAGE_WIDTH / 2f, OFFSET + IMAGE_WIDTH / 2f);
//        camera.applyToCanvas(canvas);
        canvas.rotate(-flipRotation);
        /**
         * 使用Camera使图片绕着x轴旋转30度
         * 先save应用完毕之后restore就防止每次重新进入app的时候重绘不在起点
         */
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();

        /**
         * 转起来之前切割
         */
        canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(OFFSET + IMAGE_WIDTH / 2f), -(OFFSET + IMAGE_WIDTH / 2f));

        /**
         * 绘制图片
         */
        canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
        canvas.restore();

//        ------------------------------------------------下半部分----------------------------------------------------
        /**
         * 绘制之前先保存画布
         */
        canvas.save();
        /**
         * 使用Camera搞出三维坐标系
         * 应用到Canvas上面
         * 先移动Canvas至原点
         * camera完毕，再移动回去即可
         */
        canvas.translate(OFFSET + IMAGE_WIDTH / 2f, OFFSET + IMAGE_WIDTH / 2f);
        canvas.rotate(-flipRotation);
        /**
         * 使用Camera使图片绕着x轴旋转30度
         * 先save应用完毕之后restore就防止每次重新进入app的时候重绘不在起点
         */
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();

        /**
         * 转起来之前切割
         */
        canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(OFFSET + IMAGE_WIDTH / 2f), -(OFFSET + IMAGE_WIDTH / 2f));

        /**
         * 绘制图片
         */
        canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
        canvas.restore();
    }
}