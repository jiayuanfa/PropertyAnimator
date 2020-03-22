package com.example.propertyanimator;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;

public class Utils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * Get a Bitmap
     * @param resources
     * 为什么这里不使用系统的resources呢？因为这次的图片跟你的软件有关
     * @param width
     * @return
     */
    public static Bitmap getAvatar(Resources resources, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, R.mipmap.test3, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, R.mipmap.test3, options);
    }

    /**
     * 设置Camera打光的距离
     * @return
     */
    public static float getZForCamera() {
        return -4 * Resources.getSystem().getDisplayMetrics().density;
    }
}
