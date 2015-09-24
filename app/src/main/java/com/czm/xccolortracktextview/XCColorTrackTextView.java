
package com.czm.xccolortracktextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 自定义字体变色TextView
 */
public class XCColorTrackTextView extends TextView {

    private int mWidth;
    private int mHeight;
    private int mTextWidth;
    private int mTextHeight;
    private int mTextStartX;
    private int mTextStartY;
    private Paint mPaint;
    private Rect mTextBounds;
    private int mProgress;
    private int mMax;

    public XCColorTrackTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public XCColorTrackTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public XCColorTrackTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    private void init(Context context) {
        // TODO Auto-generated method stub
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mTextBounds = new Rect();
        mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), mTextBounds);
        mProgress = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mTextWidth = mWidth - getPaddingLeft() - getPaddingRight();
        mPaint.setTextSize(getTextSize());
        mTextStartX =  mWidth / 2 - mTextWidth/ 2;
        mMax = mWidth-getPaddingRight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), mTextBounds);
        canvas.clipRect(mTextStartX, 0, mProgress, mHeight);
        Log.v("czm", "float=" + (getMeasuredHeight() / 2 + mTextBounds.height() / 2));
                Log.v("czm", "int=" + ((getMeasuredHeight() + mTextBounds.height()) / 2));
        canvas.drawText(getText().toString(), mTextStartX, getMeasuredHeight()/2 + mTextBounds.height()/2 - 7f  , mPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mProgress <= mWidth) {
            postInvalidate();
        }
    }

    public int getMax() {
        return mMax;

    }
}
