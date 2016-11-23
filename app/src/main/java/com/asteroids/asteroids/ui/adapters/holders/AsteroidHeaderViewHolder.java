package com.asteroids.asteroids.ui.adapters.holders;

import android.view.View;
import android.widget.TextView;

import com.asteroids.asteroids.R;
import com.asteroids.asteroids.data.Header;


/**
 * Created by Tadeo-developer on 18/01/16.
 */
public class AsteroidHeaderViewHolder extends ViewHolder {

    private TextView dummy;
    private Header header;


    public AsteroidHeaderViewHolder(View itemView) {
        super(itemView);

        dummy = (TextView) itemView.findViewById(R.id.tv_dummy);

    }

    @Override
    public void bind(Object obj) {

        if (obj instanceof Header) {
            header = (Header) obj;
            dummy.setText(header.getDate());
            dummy.setTextColor(itemView.getResources().getColor(R.color.white));
        }

    }


}