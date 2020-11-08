package com.ynov.vernet.cookieclicker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textViewTemps, textViewScore;
    private int score = 0;
    private int temps = 3;      /*Mettre 10*/
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
                            Log.e("Erreur", "Erreur : " + e.toString());
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
        textViewTemps.setText("Temps : " + temps + " s");

        // Si le temps atteint 0
        if (temps <= 0) {
            // Arrêter le compte à rebours
            timer.cancel();

            // Afficher les résultats
            resultat();
        }
        else
            // Continuer le compte à rebours
            timer.start();
    }

    public void incrementerScore() {
        score++;
        textViewScore.setText("Score : " + score);
    }

    public void resultat() {

        // Faire une moyenne des clics
        double moyenneClicks = score / 10;

        // Afficher la moyenne
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Terminé !")
                .setMessage(moyenneClicks + " clics / secondes")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                })
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }
}