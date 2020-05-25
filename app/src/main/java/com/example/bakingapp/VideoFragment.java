package com.example.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class VideoFragment extends Fragment {
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;
    TextView mStepDescription;
    Step mStep;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public VideoFragment() {

    }

    /**
     * Inflates the fragment layout file and sets the correct resource for the Exoplayer  to display
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the Video fragment layout
        View rootView = inflater.inflate(R.layout.video_fragment, container, false);
          // Initialize the player view.
        mPlayerView = (PlayerView) rootView.findViewById(R.id.player_view);
        mStepDescription = (TextView) rootView.findViewById(R.id.tv_step_description);

        // Initialize the player.
        initializePlayer(Uri.parse(mStep.getVideoURL()));
return rootView;
    }


    /**
     * Initialize ExoPlayer.
     * @param videoUri The URI of the sample to play.
     */
    public void initializePlayer(Uri videoUri) {
        mStepDescription.setText(mStep.getDescription());
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            Uri uri = videoUri;
            MediaSource mediaSource = buildMediaSource(uri);
            mExoPlayer.prepare(mediaSource,false,false);
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(currentWindow, playbackPosition);
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getActivity(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            playWhenReady = mExoPlayer.getPlayWhenReady();
            playbackPosition = mExoPlayer.getCurrentPosition();
            currentWindow = mExoPlayer.getCurrentWindowIndex();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer((Uri.parse(mStep.getVideoURL())));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || mExoPlayer == null)) {
            initializePlayer((Uri.parse(mStep.getVideoURL())));
        }
    }
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}


