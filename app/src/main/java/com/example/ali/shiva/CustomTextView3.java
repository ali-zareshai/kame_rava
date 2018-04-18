package com.example.ali.shiva;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView3 extends TextView {
    public CustomTextView3(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/yekan.ttf");
        this.setTypeface(type2);
    }

    public CustomTextView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public CustomTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(FormatHelper.toPersianNumber((String) text), type);
    }
}
