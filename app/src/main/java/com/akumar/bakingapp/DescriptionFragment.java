package com.akumar.bakingapp;



import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akumar.bakingapp.Utilities.Recipe;
import com.android.bakingapp.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment  {

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private int position;
    private ArrayList<Recipe.Steps> stepsArrayList;
    private boolean isVideoPlaying = false;
    long cursorPosition = 0;
    MediaSource mediaSource;
    TextView shortDes,description;
    private boolean twoPane ;
    private PlaybackStateCompat.Builder mStateBuilder;
    private static MediaSessionCompat mMediaSession;






    public DescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("create","create called");

        Bundle bundle = getArguments();
        if (savedInstanceState != null){
            stepsArrayList = savedInstanceState.getParcelableArrayList("save_list");
            position = savedInstanceState.getInt("save_posn");
            cursorPosition = savedInstanceState.getLong("curposn");
            isVideoPlaying = savedInstanceState.getBoolean("isplay");
            Log.d("isPlay",String.valueOf(isVideoPlaying));
            Log.d("curpon",String.valueOf(cursorPosition));


        }else{

            position = bundle.getInt("step_position");
            stepsArrayList = bundle.getParcelableArrayList("step_arraylist");
            twoPane = bundle.getBoolean("twopane");

            Log.d("twoPane",String.valueOf(twoPane));


        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_description, container, false);
        simpleExoPlayerView = view.findViewById(R.id.exoPlayer);



        if (!checkNetwork()){
            simpleExoPlayerView.setVisibility(View.GONE);
            Toast.makeText(getContext(), "video unavailable! please connect to a network", Toast.LENGTH_SHORT).show();

        }else {
            simpleExoPlayerView.setVisibility(View.VISIBLE);
        }




        shortDes =  view.findViewById(R.id.recipe_short);
        description =  view.findViewById(R.id.recipe_description);



        if (simpleExoPlayer == null) {

            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector());


        }
        mediaSource = new ExtractorMediaSource(Uri.parse(stepsArrayList.get(position).getVideoURL())
                , new DefaultDataSourceFactory(

                getActivity(), "ambar"), new DefaultExtractorsFactory(), null, null);



        simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);





        final int configuration = getResources().getConfiguration().orientation;

       /* if(configuration == Configuration.ORIENTATION_LANDSCAPE){

            Log.d("config","changed config");



            if (!stepsArrayList.get(position).getVideoURL().isEmpty()) {

                shortDes.setVisibility(View.GONE);


                description.setVisibility(View.GONE);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) simpleExoPlayerView.getLayoutParams();
                params.width = params.MATCH_PARENT;
                params.height = params.MATCH_PARENT;

                simpleExoPlayerView.setLayoutParams(params);
            }else {
                shortDes.setVisibility(View.VISIBLE);
                description.setVisibility(View.VISIBLE);
            }

        }else {


        }
*/

        if (stepsArrayList != null) {

            shortDes.setText(stepsArrayList.get(position).getShortDescription());
            description.setText(stepsArrayList.get(position).getDescription());

            if (!stepsArrayList.get(position).getThumbnailURL().isEmpty()) {


                ImageView imageView = view.findViewById(R.id.thumbnail_img);
                imageView.setVisibility(View.VISIBLE);

                Picasso.with(getContext())
                        .load(stepsArrayList.get(position).getThumbnailURL())
                        .error(R.drawable.ic_launcher_background)
                        .into(imageView);

            } else {
                view.findViewById(R.id.no_image).setVisibility(View.VISIBLE);
            }


            if (stepsArrayList.get(position).getVideoURL() != null && !stepsArrayList.get(position).getVideoURL().isEmpty()) {
                isVideoPlaying = true;
                simpleExoPlayerView.setPlayer(simpleExoPlayer);
            } else {
                isVideoPlaying = false;
                (view.findViewById(R.id.exoPlayer)).setVisibility(View.GONE);
            }
            Button button = view.findViewById(R.id.next_step_btn);

            if (twoPane) {
                button.setVisibility(View.GONE);
            } else {


                if (position + 1 == stepsArrayList.size()) {
                    button.setVisibility(View.GONE);
                } else {
                    button.setVisibility(View.VISIBLE);
                }


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (isVideoPlaying) {
                            simpleExoPlayer.release();
                        }


                        position++;


                        if (position + 1 == stepsArrayList.size()) {
                            (view.findViewById(R.id.next_step_btn)).setVisibility(View.GONE);
                        }

                        Recipe.Steps steps = stepsArrayList.get(position);

                        if (!steps.getThumbnailURL().isEmpty()) {

                            ImageView imageView = view.findViewById(R.id.thumbnail_img);
                            imageView.setVisibility(View.VISIBLE);

                            Picasso.with(getContext())
                                    .load(steps.getThumbnailURL())
                                    .error(R.drawable.ic_launcher_background)
                                    .into(imageView);

                        } else {
                            view.findViewById(R.id.no_image).setVisibility(View.VISIBLE);
                        }


                        shortDes.setText(steps.getShortDescription());
                        description.setText(steps.getDescription());

                        if (!TextUtils.isEmpty(steps.getVideoURL())) {

                            if (!checkNetwork()) {
                                simpleExoPlayerView.setVisibility(View.GONE);
                                isVideoPlaying = false;
                                Toast.makeText(getContext(), "video unavailable! please connect to a network", Toast.LENGTH_SHORT).show();
                            } else {

                                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector());

                                MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(steps.getVideoURL()), new DefaultDataSourceFactory(
                                        getActivity(), "ambar"), new DefaultExtractorsFactory(), null, null);
                                simpleExoPlayer.prepare(mediaSource);
                                simpleExoPlayer.setPlayWhenReady(true);
                                isVideoPlaying = true;
                                simpleExoPlayerView.setPlayer(simpleExoPlayer);
                                simpleExoPlayerView.setVisibility(View.VISIBLE);
                            }


                        } else {
                            isVideoPlaying = false;
                            simpleExoPlayerView.setVisibility(View.GONE);
                        }


                    }
                });

            }
        }



        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (simpleExoPlayer != null) {
            //cursorPosition = simpleExoPlayer.getCurrentPosition();
            simpleExoPlayer.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cursorPosition = simpleExoPlayer.getCurrentPosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("resume","resume called");
        if (simpleExoPlayer != null) {

            simpleExoPlayer.seekTo(cursorPosition);

            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }else if (isVideoPlaying){
            simpleExoPlayer.seekTo(cursorPosition);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }

    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("save_posn",position);
        outState.putParcelableArrayList("save_list",stepsArrayList);
        outState.putLong("curposn",cursorPosition);
        outState.putBoolean("isplay",isVideoPlaying);

    }

    public boolean checkNetwork(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }



    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }






}
