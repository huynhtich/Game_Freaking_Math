package com.example.gamefreakingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {

    TextView tvScore, tvQuestion, tvResult;
    ImageView btnWrong, btnCorrect;
    Button btnPlay;

    Timer timer;
    TimerTask timerTask;

    private int score = 0;
    private LevelModel model;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_play);

        tvScore   = (TextView) findViewById(R.id.textview_Score);
        tvQuestion  = (TextView) findViewById(R.id.textview_Question);
        tvResult =  (TextView) findViewById(R.id.textview_Result);

        btnCorrect     = (ImageView) findViewById(R.id.btn_Correct);
        btnWrong    = (ImageView) findViewById(R.id.btn_Wrong);
        btnPlay    = (Button) findViewById(R.id.btn_Play);

        rand = new Random();

        //Tao cau hoi dau tien
        model = GenerateLevel.generateLevel(1);
        displayNewLevel(model);

        createTimerTask();

        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick(view);
            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick(view);
            }
        });


    }


    private void displayNewLevel(LevelModel model) {
        tvQuestion.setText(model.strOperator);
        tvResult.setText(model.strResult);
    }

    private void nextLevel(int score) {
        cancelTimer();

        createTimerTask();

        model = GenerateLevel.generateLevel(score);
        displayNewLevel(model);
    }

    private void createTimerTask() {
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask, 3000);
    }

    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_Correct) {
            if (model.correctWrong) {
                score += 1;

                nextLevel(score);

            } else {
                showGameOver(score);
            }
        }

        if (id == R.id.btn_Wrong) {
            if (!model.correctWrong) {
                score += 1;

                nextLevel(score);

            } else {
                showGameOver(score);
            }
        }

    }

    private void showGameOver(final int score) {
        btnCorrect.setEnabled(false);
        btnWrong.setEnabled(false);

        cancelTimer();

        new AlertDialog.Builder(this)
                .setTitle("Game over")
                .setMessage("Your score : " + score)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        btnCorrect.setEnabled(true);
                        btnWrong.setEnabled(true);

                        tvScore.setText("0");
                        PlayActivity.this.score = 0;
                        PlayActivity.this.nextLevel(PlayActivity.this.score);

                    }
                })

                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        PlayActivity.this.finish();

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

    private void cancelTimer() {
        timerTask.cancel();
        timer.cancel();
    }
}