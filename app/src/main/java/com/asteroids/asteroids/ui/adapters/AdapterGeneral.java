package com.asteroids.asteroids.ui.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asteroids.asteroids.ui.adapters.holders.ViewHolder;

import java.util.ArrayList;

/**
 * Created by Tadeo-developer on 18/01/16.
 */
public abstract class AdapterGeneral<T> extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private int layout;
    protected ArrayList<T> objects;

    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private int visibleThreshold = 1;
    private OnLoadMoreListener onLoadMoreListener;


    public AdapterGeneral(ArrayList<T> objects, int layout, RecyclerView recyclerView) {
        this.objects = objects;
        this.layout = layout;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {


            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();


                    if (!loading && (totalItemCount <= (lastVisibleItem + visibleThreshold))) {

                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoaded() {
        loading = false;
    }


    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(objects.get(position));

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        listener = onClickListener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }

    public int getLayout() {
        return this.layout;
    }

    public View getViewInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
    }


}
