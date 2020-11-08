package com.ynov.vernet.cookieclicker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

                            // Si le temps atteint 0
                            if (temps == 0) {
                                // Arrêter le Thread

                                // Faire une moyenne des clics
                                int moyenneClicks = score / 10;

                                // Afficher la moyenne
                                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setTitle("Titre")
                                        .setMessage(moyenneClicks + " clics / secondes")
                                        .setPositiveButton("Oui", (dialogInterface, i) -> {
                                        })
                                        .show();
                                alertDialog.setCanceledOnTouchOutside(false);

                            }
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