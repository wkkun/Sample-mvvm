package com.wkkun.base.view.aspect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wkkun.base.R;


/**
 * Created by ZhuJiaXiang
 * on 2020-08-11
 */
public class AspectRatioFrameLayout extends FrameLayout {

    // NOTE: These must be kept in sync with the AspectRatioImageView attributes in attrs.xml.
    public static final int MEASUREMENT_WIDTH = 0;
    public static final int MEASUREMENT_HEIGHT = 1;

    private static final float DEFAULT_ASPECT_RATIO = 1f;
    private static final boolean DEFAULT_ASPECT_RATIO_ENABLED = false;
    private static final int DEFAULT_DOMINANT_MEASUREMENT = MEASUREMENT_WIDTH;

    private float aspectRatio;
    private boolean aspectRatioEnabled;
    private int dominantMeasurement;

    public AspectRatioFrameLayout(@NonNull Context context) {
        super(context);
    }

    public AspectRatioFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioFrameLayout);
        aspectRatio = a.getFloat(R.styleable.AspectRatioFrameLayout_fl_aspectRatio, DEFAULT_ASPECT_RATIO);
        aspectRatioEnabled = a.getBoolean(R.styleable.AspectRatioFrameLayout_fl_aspectRatioEnabled,
                DEFAULT_ASPECT_RATIO_ENABLED);
        dominantMeasurement = a.getInt(R.styleable.AspectRatioFrameLayout_fl_dominantMeasurement,
                DEFAULT_DOMINANT_MEASUREMENT);
        a.recycle();
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        requestLayout();
    }

    public AspectRatioFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AspectRatioFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (aspectRatioEnabled){
            setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
            int newWidth;
            int newHeight;
            switch (dominantMeasurement) {
                case MEASUREMENT_WIDTH:
                    newWidth = getMeasuredWidth();
                    newHeight = (int) (newWidth * aspectRatio);
                    break;

                case MEASUREMENT_HEIGHT:
                    newHeight = getMeasuredHeight();
                    newWidth = (int) (newHeight * aspectRatio);
                    break;

                default:
                    throw new IllegalStateException("Unknown measurement with ID " + dominantMeasurement);
            }

            int width = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY);
            int height = MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY);
            setMeasuredDimension(width, height);
            super.onMeasure(width, height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
