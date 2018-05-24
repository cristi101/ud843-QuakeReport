package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View earthquakeView = convertView;

        Earthquake earthquake = getItem(position);

        if (earthquakeView == null)
            earthquakeView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake, parent, false);

        TextView magnitude = earthquakeView.findViewById(R.id.magnitude);
        TextView distance = earthquakeView.findViewById(R.id.location_offset);
        TextView location = earthquakeView.findViewById(R.id.primary_location);
        TextView date = earthquakeView.findViewById(R.id.date);
        TextView time = earthquakeView.findViewById(R.id.time);

        DecimalFormat format = new DecimalFormat("0.0");
        magnitude.setText(format.format(earthquake.getMagnitude()));
        GradientDrawable circle = (GradientDrawable) magnitude.getBackground();
        circle.setColor(getMagnitudeColor(earthquake.getMagnitude()));

        String loc = earthquake.getLocation();
        int i;
        if ((i = loc.indexOf(" of ")) == -1) {
            distance.setText("Near the");
            location.setText(loc);
        } else {
            distance.setText(loc.substring(0, i + 3));
            location.setText(loc.substring(i + 4));
        }

        Date d = new Date(earthquake.getTime());
        DateFormat f = new SimpleDateFormat("LLL dd, yyyy");
        date.setText(f.format(d));
        f = new SimpleDateFormat("h:mm a");
        time.setText(f.format(d));

        return earthquakeView;

    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }
}
