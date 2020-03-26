package com.example.propertyanimator;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PointView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private PointF point = new PointF(0, 0);

    public PointF getPoint() {
        return point;
    }

    public void setPoint(PointF point) {
        this.point = point;
        invalidate();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(Utils.dp2px(20f));
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(point.x, point.y, paint);
    }
}
