package com.asteroids.asteroids.ui.adapters.holders;

import android.view.View;
import android.widget.ProgressBar;

import com.asteroids.asteroids.R;


/**
 * Created by Tadeo-developer on 18/01/16.
 */
public class AsteroidProgressViewHolder extends ViewHolder {

    private ProgressBar progressBar;


    public AsteroidProgressViewHolder(View itemView) {
        super(itemView);

        progressBar = (ProgressBar) itemView.findViewById(R.id.progress_general);

    }

    @Override
    public void bind(Object obj) {

        if(obj==null){
            progressBar.setVisibility(View.VISIBLE);
        }

    }


}