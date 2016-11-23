package com.asteroids.asteroids.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asteroids.asteroids.R;
import com.asteroids.asteroids.data.Header;
import com.asteroids.asteroids.ui.adapters.holders.AsteroidHeaderViewHolder;
import com.asteroids.asteroids.ui.adapters.holders.AsteroidProgressViewHolder;
import com.asteroids.asteroids.ui.adapters.holders.AsteroidItemViewHolder;
import com.asteroids.asteroids.ui.adapters.holders.ViewHolder;

import java.util.ArrayList;

/**
 * Created by Tadeo-developer on 10/06/16.
 */
public class AsteroidAdapter<T> extends AdapterGeneral {

    protected final int VIEW_PROG = 0;
    protected final int VIEW_ITEM = 1;
    protected final int VIEW_HEADER = 2;

    public AsteroidAdapter(ArrayList<T> objects, int layout, RecyclerView recyclerView){
        super(objects,layout,recyclerView);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        ViewHolder anticiposViewHolder;

        if(viewType == VIEW_HEADER){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_asteroid, parent, false);
            anticiposViewHolder = new AsteroidHeaderViewHolder(itemView);
        }else if(viewType == VIEW_PROG){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_asteroid, parent, false);
            anticiposViewHolder = new AsteroidProgressViewHolder(itemView);
        }else{
            itemView = getViewInflater(parent);
            anticiposViewHolder = new AsteroidItemViewHolder(itemView);
        }


        itemView.setOnClickListener(this);
        return anticiposViewHolder;
    }

    @Override
    public int getItemViewType(int position) {

        if(objects.get(position)==null){
            return VIEW_PROG;
        }else if(objects.get(position) instanceof Header){
            return VIEW_HEADER;
        }else{
            return VIEW_ITEM;
        }
    }

}
