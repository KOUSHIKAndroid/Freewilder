package freewilder.rockme.com.freewilder.custom_front;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class OpenSansBold extends AppCompatTextView {

	public OpenSansBold(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
		//init();
	}

	public OpenSansBold(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		//init();
	}

	public OpenSansBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		//init();
	}

	public void init() {

		super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
				"OpenSans-Bold.ttf"));

	}

	public void init(Context context) {
		super.setTypeface(FontCache.get("OpenSans-Bold.ttf", context));
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