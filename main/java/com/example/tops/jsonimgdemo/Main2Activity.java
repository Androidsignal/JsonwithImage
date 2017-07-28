package com.example.tops.jsonimgdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    TextView txtname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = (ImageView) findViewById(R.id.image1);
        txtname = (TextView) findViewById(R.id.txtprint);

        String name = getIntent().getStringExtra("name");
        String dob  = getIntent().getStringExtra("dob");
        String description = getIntent().getStringExtra("description");
        String country = getIntent().getStringExtra("country");
        String height = getIntent().getStringExtra("height");
        String spouse = getIntent().getStringExtra("spouse");
        String children = getIntent().getStringExtra("children");

        txtname.setText("" + name + "\n\n" +
                "Description : " + description + "\n\n" +
                "DOB : " + dob + "\n\n" +
                "Country : " + country + "\n" +
                "Height : " + height + "\n" +
                "" + spouse + "\n" +
                "Children : " + children);

        //Glide.with(Main2Activity.this).load(getIntent().getStringExtra("image")).into(imageView);
        Picasso.with(Main2Activity.this).load(getIntent().getStringExtra("image")).into(imageView);

    }
}
