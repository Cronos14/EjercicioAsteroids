package com.asteroids.asteroids.ui.adapters.holders;

import android.view.View;
import android.widget.TextView;

import com.asteroids.asteroids.R;
import com.asteroids.asteroids.data.ObjectGeneral;


/**
 * Created by Tadeo-developer on 18/01/16.
 */
public class AsteroidItemViewHolder extends ViewHolder {

    public static final String NEO_REFERENCE_ID = "neo_reference_id";
    public static final String NAME = "name";
    public static final String ABSOLUTE_MAGNITUDE_H = "absolute_magnitude_h";
    public static final String OBJECT_ESTIMATED_DIAMETER = "estimated_diameter";
    public static final String OBJECT_CHILD_KILOMETERS = "kilometers";
    public static final String OBJECT_CHILD_METERS = "meters";
    public static final String OBJECT_CHILD_MILES = "miles";
    public static final String OBJECT_CHILD_FEET = "feet";
    public static final String ESTIMATED_DIAMETER_MIN = "estimated_diameter_min";
    public static final String ESTIMATED_DIAMETER_MAX = "estimated_diameter_max";

    public static final String SELECTED_DIAMETER = OBJECT_CHILD_METERS;

    public static final String CLOSE_APPROACH_DATA = "close_approach_data";
    public static final String CLOSE_APPROACH_DATE = "close_approach_date";
    public static final String OBJECT_RELATIVE_VELOCITY = "relative_velocity";
    public static final String OBJECT_CHILD_KM_H = "kilometers_per_hour";

    public static final String OBJECT_MISS_DISTANCE = "miss_distance";

    public static final String ORBITING_BODY = "orbiting_body";




    private TextView id;
    private TextView name;
    private TextView abs;

    private TextView diameterMin;
    private TextView diameterMax;


    private ObjectGeneral asteroid;


    public AsteroidItemViewHolder(View itemView) {
        super(itemView);

        id = (TextView) itemView.findViewById(R.id.tv_id);
        name = (TextView)itemView.findViewById(R.id.tv_name);
        abs = (TextView)itemView.findViewById(R.id.tv_absolute_magnitude_h);

        diameterMin = (TextView)itemView.findViewById(R.id.tv_estimated_diameter_min);
        diameterMax = (TextView)itemView.findViewById(R.id.tv_estimated_diameter_max);

    }

    @Override
    public void bind(Object obj) {


        if (obj instanceof ObjectGeneral) {
            asteroid = (ObjectGeneral) obj;
            id.setText(asteroid.getAttributes().get(NEO_REFERENCE_ID).toString());
            name.setText(asteroid.getAttributes().get(NAME).toString());
            abs.setText(asteroid.getAttributes().get(ABSOLUTE_MAGNITUDE_H).toString());

            ObjectGeneral diametro = (ObjectGeneral) asteroid.getAttributes().get(OBJECT_ESTIMATED_DIAMETER);
            ObjectGeneral typeDiametro = (ObjectGeneral) diametro.getAttributes().get(SELECTED_DIAMETER);
            diameterMin.setText(typeDiametro.getAttributes().get(ESTIMATED_DIAMETER_MIN).toString());
            diameterMax.setText(typeDiametro.getAttributes().get(ESTIMATED_DIAMETER_MAX).toString());
        }


    }


}