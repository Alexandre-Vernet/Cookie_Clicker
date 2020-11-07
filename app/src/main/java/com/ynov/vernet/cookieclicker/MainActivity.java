package com.ynov.vernet.cookieclicker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textViewTemps, textViewScore;
    private int score, temps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Référence
        textViewScore = findViewById(R.id.textViewScore);
        textViewTemps = findViewById(R.id.textViewTemps);

        // Au clic du cookie
        findViewById(R.id.imageViewCookie).setOnClickListener(v -> {
            // Incrémenter le score
            score++;
            textViewScore.setText("Score : " + score);
        });
    }
}