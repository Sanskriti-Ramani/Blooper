package com.example.sanskriti.blooper.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class ReviewActivity extends AppCompatActivity {
    TextView name;
    TextView content;
    public static final String NAME = "name";
    public static final String CONTENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String title = "Reviews -> " + bundle.getCharSequence(NAME).toString();
        getSupportActionBar().setTitle((CharSequence)title);
        setContentView(R.layout.activity_review);
        name = findViewById(R.id.nameOfThePerson);
        content = findViewById(R.id.contentOfTheReview);

        name.setText(bundle.getCharSequence(NAME));
        content.setText(bundle.getCharSequence(CONTENT));

    }
}
