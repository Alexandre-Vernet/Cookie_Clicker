package com.ynov.vernet.cookieclicker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textViewTemps, textViewScore;
    private int score = 0;
    private int temps = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Référence
        textViewScore = findViewById(R.id.textViewScore);
        textViewTemps = findViewById(R.id.textViewTemps);


        // Au clic du cookie
        findViewById(R.id.imageViewCookie).setOnClickListener(v -> {

            // Au 1er clic de l'image
            if (score == 0)
                decrementerChrono();

            // Incrémenter le score
            incrementerScore();

        });

    }

    public void decrementerChrono() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(() -> {
                            // Décrémenter le chrono
                            temps--;
                            textViewTemps.setText("Temps : " + temps + " s");
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    public void incrementerScore() {
        score++;
        textViewScore.setText("Score : " + score);

    }
}