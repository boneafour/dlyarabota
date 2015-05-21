package com.example.hyhy.dlyarabota;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.layout.simple_spinner_item;


public class MainActivity extends ActionBarActivity implements OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener {

    private ImageButton PlayPause;
    private SeekBar size;
    private Spinner musiiic;
    public TextView musiclist;
   //String[] musicUrl = getResources().getStringArray(R.array.musics);

    String a;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        musiiic = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, simple_spinner_item, getResources().getStringArray(R.array.musicsname));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musiiic.setSelection(0);
        musiiic.setAdapter(adapter1);
        musiiic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mediaPlayer.stop();
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(id==0){
                a = "http://tegos.kz/new/mp3_full/Dima_Bilan_-_Ne_Molchi.mp3";
                mediaPlayer.stop();
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    PlayPause.setImageResource(R.drawable.pause);
                }else {
                    mediaPlayer.pause();
                    PlayPause.setImageResource(R.drawable.play);
                }


            }
                if(id==1) {
                    a = "http://tegos.kz/new/mp3_full/MC_DONI_feat_Natali_-_Ti_takoy.mp3";
                    mediaPlayer.stop();
                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        PlayPause.setImageResource(R.drawable.pause);
                    }else {
                        mediaPlayer.pause();
                        PlayPause.setImageResource(R.drawable.play);
                    }


                }
                if(id==2) {
                    a = "http://tegos.kz/new/mp3_full/Potap_i_Nastya_Kamenskih_-_Chumacechaya_vesna.mp3";
                    mediaPlayer.stop();

                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        PlayPause.setImageResource(R.drawable.pause);
                    }else {
                        mediaPlayer.pause();
                        PlayPause.setImageResource(R.drawable.play);
                    }


                }
                if(id==3){
                    a = "http://tegos.kz/new/mp3_full/Natan_feat_Timati_-_Derzkaya.mp3";
                    mediaPlayer.stop();

                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        PlayPause.setImageResource(R.drawable.pause);
                    }else {
                        mediaPlayer.pause();
                        PlayPause.setImageResource(R.drawable.play);
                    }
                }
                if(id==4){
                    a = "http://tegos.kz/new/mp3_full/Dan_Balan_-_Do_utra.mp3";
                    mediaPlayer.stop();

                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        PlayPause.setImageResource(R.drawable.pause);
                    }else {
                        mediaPlayer.pause();
                        PlayPause.setImageResource(R.drawable.play);
                    }
                }
            }
        });
        PlayPause = (ImageButton)findViewById(R.id.PlayPause);
        PlayPause.setOnClickListener(this);

        size = (SeekBar)findViewById(R.id.SeekBarTestPlay);
        size.setMax(99);
        size.setOnTouchListener(this);


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
    } private void primarySeekBarProgressUpdater() {
        size.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100));
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.PlayPause){

            try {
                mediaPlayer.setDataSource(a);
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mediaFileLengthInMilliseconds = mediaPlayer.getDuration();

            if(!mediaPlayer.isPlaying()){
                mediaPlayer.start();
                PlayPause.setImageResource(R.drawable.pause);
            }else {
                mediaPlayer.pause();
                PlayPause.setImageResource(R.drawable.play);
            }

            primarySeekBarProgressUpdater();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.SeekBarTestPlay){

            if(mediaPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
                int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        PlayPause.setImageResource(R.drawable.play);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

        size.setSecondaryProgress(percent);
    }
}