package com.example.a37925.drawpathdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Qiushuo Huang on 2017/5/4.
 */

public class CardItemView extends TextView {
    protected int size;
    protected Paint paint;
    protected Path path;
    protected Region region;
    protected int boarderColor;


    public CardItemView(Context context) {
        this(context, null, 0);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardItemView, defStyleAttr, 0);
//        size = (int)typedArray.getDimension(R.styleable.CardItemView_Card_size, 10);
        boarderColor = typedArray.getColor(R.styleable.CardItemView_Card_color, 0);
        paint.setColor(boarderColor);
        typedArray.recycle();

        region = new Region();
        path = new Path();
    }

    public void draw(){
        size=getHeight();
        path.moveTo(size/2,0);
        path.lineTo(0, size/2);
        path.lineTo(size/2, size);
        path.lineTo(size, size/2);
        path.close();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("test", "layout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("test", "measure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        draw();
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawPath(path, paint);

        Paint corner = new Paint(Paint.ANTI_ALIAS_FLAG);
        corner.setColor(boarderColor);
        corner.setStyle(Paint.Style.STROKE);
        corner.setStrokeWidth(6);

        //左上
        RectF oval1 = new RectF(3, 3, 53, 53);
        canvas.drawArc(oval1, 180, 90, false, corner);
//        canvas.drawRect(oval1, corner);
        //右上
        RectF oval2 = new RectF(getWidth()-53, 3, getWidth()-3, 53);
        canvas.drawArc(oval2, 270, 90, false, corner);
//        canvas.drawRect(oval2, corner);
        //左下
        RectF oval3 = new RectF(3, getHeight()-53, 53, getHeight()-3);
        canvas.drawArc(oval3, 90, 90, false, corner);
//        canvas.drawRect(oval3, corner);

        //右下
        RectF oval4 = new RectF(getWidth()-53, getHeight()-53, getWidth()-3, getHeight()-3);
        canvas.drawArc(oval4, 0, 90, false, corner);


        Paint rec = new Paint(Paint.ANTI_ALIAS_FLAG);
        rec.setColor(boarderColor);
        rec.setStyle(Paint.Style.STROKE);
        rec.setStrokeWidth(6);
        rec.setPathEffect(new DashPathEffect(new float[] {20,20,20,20}, 1));
        RectF rect = new RectF(3,3,getWidth()-3, getHeight()-3);
        canvas.drawRoundRect(rect, 25, 25, rec);
    }

    public void setCardColor(int color) {
        paint.setColor(color);
        invalidate();
    }
}
