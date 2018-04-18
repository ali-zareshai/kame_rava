package com.example.ali.shiva;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomTextView extends TextView {

    public static void setText(String s) {
        setText(FormatHelper.toPersianNumber(s));
    }

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/danstevis.otf");
        this.setTypeface(type2,Typeface.BOLD_ITALIC);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
