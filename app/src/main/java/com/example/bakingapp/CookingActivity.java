package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bakingapp.model.Step;

public class CookingActivity extends AppCompatActivity {

    public static final String STEP_LIST_STATE = "step_list_state";
    // source of data
    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);
        // Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//intent that started this activity
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity == null) {
            closeOnError();
        }
        mStep = intentThatStartedThisActivity.getParcelableExtra("STEP");
        if (savedInstanceState == null) {
            Bundle stepBundle = new Bundle();
            stepBundle.putParcelable("video", mStep);
            VideoFragment video = new VideoFragment();
            video.setArguments(stepBundle);
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.video_id, video)
                    .commit();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, " no video available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STEP_LIST_STATE, mStep);
    }

}
