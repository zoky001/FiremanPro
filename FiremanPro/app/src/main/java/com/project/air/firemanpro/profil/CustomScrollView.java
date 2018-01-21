package com.project.air.firemanpro.profil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Vrši se modifikacija klasičnog ScrollViewa na način da dozvoljava prikazvanje i korištenje google map fragemnta unutar "Scroll Vieva
 * <p>
 * Omogućuje korištenje google mape unutar scroll Vieva.
 * <p>
 * <p>
 * Created by Zoran on 20.11.2017..
 * </p>
 */
public class CustomScrollView extends ScrollView {

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_MOVE:
                return false;

            case MotionEvent.ACTION_CANCEL:
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_UP:
                return false;

            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return true;
    }
}