package com.example.gamefreakingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    private int intervalDelay = 1200;
    private int score = 0;
    private LevelModel model;
    private Random rand;
    private String strMessage = "";
    private int maxScore = 0;

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
        model = GenerateLevel.generateLevel(0);

        displayNewLevel(model);

        createTimerTask(intervalDelay);

        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickView(view);
            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickView(view);
            }
        });


    }

    private void displayNewLevel(LevelModel model) {
        tvQuestion.setText(model.strOperator);
        tvResult.setText(model.strResult);
    }

    private void nextLevel(int score) {

        tvScore.setText("Score: " + score + " Best: " + maxScore);

        cancelTimer();

        model = GenerateLevel.generateLevel(score);
        if (model.difficultLevel == 2)
            intervalDelay = 1500;
        else if (model.difficultLevel == 3)
            intervalDelay = 1500;

        createTimerTask(intervalDelay);
        displayNewLevel(model);
    }

    private void createTimerTask(int interval) {
        timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        strMessage = "Suy nghĩ lâu quá!!";
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask, 5000);
    }

    public void onClickView(View v) {
        int id = v.getId();

        if (id == R.id.btn_Correct) {
            if (model.correctWrong) {
                score += 1;

                nextLevel(score);

            } else {
                strMessage = "Bạn chọn sai!";
                showGameOver(score);
            }
        }

        if (id == R.id.btn_Wrong) {
            if (!model.correctWrong) {
                score += 1;

                nextLevel(score);

            } else {
                strMessage = "Nhầm rồi!";
                showGameOver(score);
            }
        }

    }

    private void showGameOver(final int score) {
        btnCorrect.setEnabled(false);
        btnWrong.setEnabled(false);

        cancelTimer();

        if (score > maxScore) {
            maxScore = score;
        }
        //tvScore.setText(maxScore + "");

        new AlertDialog.Builder(this)
                .setTitle("Game over")
                .setMessage(strMessage + " Điểm : " + score)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        btnCorrect.setEnabled(true);
                        btnWrong.setEnabled(true);

                        tvScore.setText(maxScore + "");
                        PlayActivity.this.score = 0;
                        PlayActivity.this.nextLevel(PlayActivity.this.score);

                    }
                })

                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(PlayActivity.this, MainActivity.class));
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