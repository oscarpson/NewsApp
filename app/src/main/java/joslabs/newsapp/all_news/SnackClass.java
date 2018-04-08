package joslabs.newsapp.all_news;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joslabs.newsapp.R;

/**
 * Created by OSCAR on 4/8/2018.
 */

public class SnackClass {
    public SnackClass(CoordinatorLayout coordinatorLayout, String snackTitle){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, snackTitle, Snackbar.LENGTH_LONG);
        ViewGroup viewGroup= (ViewGroup) snackbar.getView();
        viewGroup.setBackgroundResource(android.R.color.holo_red_light);

        TextView txtv = (TextView) viewGroup.findViewById(android.support.design.R.id.snackbar_text);
        txtv.setGravity(Gravity.FILL_HORIZONTAL);

        snackbar.show();
    }
}
