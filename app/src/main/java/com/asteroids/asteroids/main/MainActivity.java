package com.asteroids.asteroids.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.asteroids.asteroids.data.AsteroidLoader;
import com.asteroids.asteroids.R;
import com.asteroids.asteroids.Utils.Utils;
import com.asteroids.asteroids.data.Header;
import com.asteroids.asteroids.data.ObjectGeneral;
import com.asteroids.asteroids.data.Response;
import com.asteroids.asteroids.ui.adapters.AdapterGeneral;
import com.asteroids.asteroids.ui.adapters.AsteroidAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response> {

    public static final String NEAR_HEART_OBJECTS = "near_earth_objects";
    public static final String LOAD_PAGE = "prev";
    public static final String LINKS = "links";
    public static final String BUNDLE_URL_PREV = "url";

    public static final int TIME_HANDLER = 1000;

    private RecyclerView recyclerView;
    private ArrayList<ObjectGeneral> data;
    private AsteroidAdapter<ObjectGeneral> adapter;
    private ArrayList<Response> responses;
    private Toolbar toolbar;
    private boolean pullAndRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_general);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        data = new ArrayList<>();
        adapter = new AsteroidAdapter<>(data, R.layout.row_asteroid, recyclerView);
        responses = new ArrayList<>();


        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        getSupportLoaderManager().initLoader(1, null, this).forceLoad();


        adapter.setOnLoadMoreListener(new AdapterGeneral.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                pullAndRefresh = true;
                data.add(null);
                adapter.notifyItemInserted(data.size() - 1);

                Bundle args = new Bundle();
                ObjectGeneral link = (ObjectGeneral) responses.get(responses.size() - 1).getAttributes().get(LINKS);
                args.putString(BUNDLE_URL_PREV, link.getAttributes().get(LOAD_PAGE).toString());
                getSupportLoaderManager().restartLoader(1, args, MainActivity.this).forceLoad();

            }
        });

    }


    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        if (args != null) {
            return new AsteroidLoader(this, args.getString(BUNDLE_URL_PREV));
        } else {
            return new AsteroidLoader(this, Utils.getDate(), Utils.getDate(), getResources().getString(R.string.api_key));
        }

    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response response) {

        responses.add(response);
        final ObjectGeneral nearEarthObjects = (ObjectGeneral) response.getAttributes().get(NEAR_HEART_OBJECTS);


        if (pullAndRefresh) {

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    data.remove(data.size() - 1);
                    adapter.notifyItemRemoved(data.size());

                    for (String key : nearEarthObjects.getAttributes().keySet()) {

                        Header header = new Header();
                        header.setDate(key);
                        data.add(header);
                        ArrayList<ObjectGeneral> asteroids = (ArrayList<ObjectGeneral>) nearEarthObjects.getAttributes().get(key);
                        data.addAll(asteroids);
                        adapter.notifyDataSetChanged();
                    }

                    adapter.setLoaded();
                    pullAndRefresh = false;
                }
            }, TIME_HANDLER);

        } else {
            for (String key : nearEarthObjects.getAttributes().keySet()) {
                Header header = new Header();
                header.setDate(key);
                data.add(header);
                ArrayList<ObjectGeneral> asteroids = (ArrayList<ObjectGeneral>) nearEarthObjects.getAttributes().get(key);
                data.addAll(asteroids);
                adapter.notifyDataSetChanged();
            }

        }

    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        data.clear();
        adapter.notifyDataSetChanged();
    }

}
