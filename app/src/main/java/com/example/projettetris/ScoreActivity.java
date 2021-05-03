package com.example.projettetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private TextView textView;
    private Button resetButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textView = findViewById(R.id.scoreboard);

        resetButton = findViewById(R.id.reset);
        resetButton.setOnClickListener(this);

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("Prefs", this.MODE_PRIVATE);
        String text = "";
        Map<String, Object> allValues = new TreeMap<String, Object>(
                (o1, o2) -> {
                    Integer i1 = Integer.parseInt(o1.split("-")[1]);
                    Integer i2 = Integer.parseInt(o2.split("-")[1]);
                    return i2.compareTo(i1);
                });
        allValues.putAll(sharedPreferences.getAll());
        int cpt = 1;
        for (Object s : allValues.values()) {
            text += cpt + ") " + s.toString() + " pts \n\n";
            cpt++;
        }
        textView.setText(text);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.reset) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent score = new Intent(getApplicationContext(), ScoreActivity.class);
            startActivity(score);
        } else if(v.getId() == R.id.menuButton) {
            Intent menu = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(menu);
        }
    }
}