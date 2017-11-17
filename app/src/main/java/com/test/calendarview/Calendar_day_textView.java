package com.test.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lady_zhou on 2017/11/17.
 */

public class Calendar_day_textView extends android.support.v7.widget.AppCompatTextView {
    public boolean isToday = false;
    private Paint paint = new Paint();

    public Calendar_day_textView(Context context) {
        super(context);
    }

    public Calendar_day_textView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl();
    }

    public Calendar_day_textView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl();

    }

    private void initControl() {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToday) {
            canvas.translate(getWidth() / 2, getHeight() / 2);//移到当前view的中心点
            canvas.drawCircle(0, 0, getWidth() / 2, paint);
        }
    }
}
