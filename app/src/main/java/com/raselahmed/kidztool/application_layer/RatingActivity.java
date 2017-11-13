package com.raselahmed.kidztool.application_layer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.raselahmed.kidztool.R;

public class RatingActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingId);
        txt = (TextView) findViewById(R.id.txtViewId);
        txt.setText("Your Rating : 3" );

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {

                txt.setText("Your Rating : " + rating);

            }
        });




    }
}
