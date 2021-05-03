package com.example.projettetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playButton, scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        scoreButton = findViewById(R.id.scores);
        scoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                Intent game = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(game);
                break;
            case R.id.scores:
                Intent score = new Intent(getApplicationContext(), ScoreActivity.class);
                startActivity(score);
                break;
            default:
                break;
        }


    }
}