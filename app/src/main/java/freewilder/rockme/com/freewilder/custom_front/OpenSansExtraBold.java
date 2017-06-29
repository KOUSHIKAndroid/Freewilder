package freewilder.rockme.com.freewilder.custom_front;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by su on 5/4/17.
 */

public class OpenSansExtraBold extends AppCompatTextView {

    public OpenSansExtraBold(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
        //init();
    }

    public OpenSansExtraBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        //init();
    }

    public OpenSansExtraBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        //init();
    }

    public void init() {

        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "OpenSans-ExtraBold.ttf"));

    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("OpenSans-ExtraBold.ttf", context));
    }




    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if(focused)
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if(focused)
            super.onWindowFocusChanged(focused);
    }


    @Override
    public boolean isFocused() {
        return true;
    }
}