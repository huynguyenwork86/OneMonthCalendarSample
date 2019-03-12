package com.hn.onemonthcalendarview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class DrawableUtils {
    public static Drawable circleDrawableWithColor(int color){
        ShapeDrawable result = new ShapeDrawable(new OvalShape());
        result.getPaint().setColor(color);
        return result;
    }
}
