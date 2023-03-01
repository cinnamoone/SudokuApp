package edu.mob.sudoku.Game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.mob.sudoku.R;
/**
 *Klasa reprezentująca główne okno aplikacji, w którym użytkownik może przejść do instrukcji, rozpocząć nową grę,
 *przejrzeć swoje statystyki lub zakończyć działanie aplikacji.
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Przycisk do przejścia do instrukcji
     */
    Button goToInstruction;

    /**
     * Przycisk do rozpoczęcia nowej gry
     */
    Button newGame;

    /**
     * Przycisk do przeglądnięcia statystyk
     */
    Button statistic;

    /**
     * Przycisk do zamknięcia aplikacji
     */
    Button exit;


    /**
     * Metoda wywoływana podczas tworzenia okna, inicjalizuje elementy interfejsu oraz ustawia listener dla przycisków
     * @param savedInstanceState zachowany stan aplikacji
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        goToInstruction = findViewById(R.id.instruction_button);
        newGame = findViewById(R.id.newgame_button);
        statistic = findViewById(R.id.statistics_button);
        exit = findViewById(R.id.exit_button);

        goToInstruction.setOnClickListener(this);
        newGame.setOnClickListener(this);
        statistic.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    /**
     * Metoda obsługująca kliknięcie przycisków, przenosi użytkownika do odpowiedniego okna w aplikacji.
     * @param view widok, na którym został wykonany kliknięcie
     */

    @Override
    public void onClick(View view) {
        if(view.getId() == goToInstruction.getId()){
            Intent intent = new Intent(this, InstructionActivity.class );
            startActivity(intent);
        }else if(view.getId() == newGame.getId()){
            Intent intent = new Intent(this, LevelActivity.class );
            startActivity(intent);
        }else if(view.getId() == statistic.getId()) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        }else if(view.getId() == exit.getId()){
            finishAffinity();
        }
    }






}