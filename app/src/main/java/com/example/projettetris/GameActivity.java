package com.example.projettetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.projettetris.pieces.PieceManager;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView grid;
    private int[][] universe = new int[17][10];
    private Clock clockManager;
    private GridAdapter gridAdapter;
    private Button left, down, right, rotate, menu;
    private TextView gameOver, chrono, score;
    private EditText playerName;
    private boolean gameRunning;
    private PieceManager pieceManager;
    private SharedPreferences sharedpreferences;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.grid = findViewById(R.id.grid);
        this.left = findViewById(R.id.left);
        this.down = findViewById(R.id.down);
        this.right = findViewById(R.id.right);
        this.rotate = findViewById(R.id.rotate);
        this.menu = findViewById(R.id.returnButton);
        this.menu.setEnabled(false);
        this.menu.setAlpha(0);

        this.gameOver = findViewById(R.id.gameOver);
        this.gameOver.setEnabled(false);
        this.gameOver.setAlpha(0);
        this.chrono = findViewById(R.id.chrono);
        this.chrono.setTextColor(Color.WHITE);
        this.playerName = findViewById(R.id.playerName);
        this.playerName.setEnabled(false);
        this.playerName.setAlpha(0);
        this.score = findViewById(R.id.score);
        this.score.setTextColor(Color.WHITE);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tetris);
        mediaPlayer.setLooping(true);

        sharedpreferences = getSharedPreferences("Prefs", this.MODE_PRIVATE);

        initializeUniverse();
        gridAdapter = new GridAdapter(this, universe);
        grid.setAdapter(gridAdapter);

        gameRunning = true;
        this.pieceManager = new PieceManager(universe, gridAdapter);
        this.clockManager = new Clock(this::isGameRunning, 1000, this.pieceManager, chrono);
        this.pieceManager.setGameActivity(this);
        this.clockManager.start();

        this.left.setOnClickListener(this);
        this.down.setOnClickListener(this);
        this.right.setOnClickListener(this);
        this.rotate.setOnClickListener(this);

        this.pieceManager.createPiece();
    }

    private void initializeUniverse() {
        for(int li = 0; li<17; li++) {
            for(int col = 0; col<10; col++) {
                universe[li][col] = 0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.left:
                this.pieceManager.left();
                break;
            case R.id.down:
                this.pieceManager.down();
                break;
            case R.id.right:
                this.pieceManager.right();
                break;
            case R.id.rotate:
                this.pieceManager.rotate();
                break;
            case R.id.returnButton:
                // enregistrer le score dans les shared preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(playerName.getText().toString().contains("Entrez") || playerName.getText().toString().contains("votre") ||
                        playerName.getText().toString().contains("nom") || playerName.getText().toString().equals("")) {
                    editor.putString("Inconnu-" + pieceManager.getScore(),"Inconnu-" + pieceManager.getScore());
                } else {
                    editor.putString(playerName.getText().toString() + "-" + pieceManager.getScore(), playerName.getText().toString() + "-" + pieceManager.getScore());
                }
                editor.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            default:
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.pause();
    }


    private boolean isGameRunning() {
        return gameRunning;
    }

    public void gameRunningChange() {
        gameRunning = !gameRunning;
        clockManager.pause();
        if(!gameRunning) {
            putTextView(this.menu);
            this.menu.setOnClickListener(this);
            putTextView(this.playerName);
            putTextView(this.gameOver);
            this.gameOver.setText("GAME OVER\n" + "Score: " + pieceManager.getScore());
            this.chrono.setTextColor(0);
            removeTextView(this.down);
            removeTextView(this.right);
            removeTextView(this.left);
            removeTextView(this.rotate);
            mediaPlayer.stop();
        } else {
            removeTextView(this.menu);
        }
    }

    public void setScore(String score) {
        this.score.setText(score);
    }

    private void removeTextView(TextView textView) {
        textView.setEnabled(false);
        textView.setAlpha(0);
    }

    private void putTextView(TextView textView) {
        textView.setEnabled(true);
        textView.setAlpha(1f);
    }
}