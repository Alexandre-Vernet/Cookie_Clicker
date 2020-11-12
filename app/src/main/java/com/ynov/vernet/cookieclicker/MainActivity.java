package com.ynov.vernet.cookieclicker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textViewTemps, textViewScore;
    private int score = 0;
    private int temps = 3;
    private double moyenneClics;
    private CountDownTimer timer;

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
            if (score == 0) {
                // Démarrer le compte à rebours
                timer = new CountDownTimer(1000, 20) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        try {
                            decrementerChrono();
                        } catch (Exception e) {
                            Log.e(getString(R.string.erreur), R.string.erreur + e.toString());
                        }
                    }
                }.start();
            }

            // Incrémenter le score à chaques clics
            incrementerScore();
        });
    }

    public void decrementerChrono() {
        temps--;
        textViewTemps.setText(getString(R.string.temps, temps));

        // Si le temps atteint 0
        if (temps <= 0) {
            // Arrêter le compte à rebours
            timer.cancel();

            // Afficher les résultats
            resultat();
        } else
            // Continuer le compte à rebours
            timer.start();
    }

    public void incrementerScore() {
        score++;
        textViewScore.setText(getString(R.string.score, score));
    }

    public void resultat() {

        // Faire une moyenne des clics
        moyenneClics = score / 10;

        // Afficher la moyenne
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.termine)
                .setMessage(getString(R.string.moyenne_clics, moyenneClics))
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    // Réinitialiser les valeurs
                    score = 0;
                    temps = 10;
                    textViewScore.setText(getString(R.string.score, score));
                    textViewTemps.setText(getString(R.string.temps, temps));
                })
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }
}