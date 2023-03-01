package edu.mob.sudoku.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.mob.sudoku.R;

/**
 * Klasa reprezentująca okno z instrukcjami do gry, pozwala użytkownikowi na powrót do głównego okna aplikacji.
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */

public class InstructionActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Przycisk do powrotu do głównego okna aplikacji
     */
    Button back;

    /**
     * Metoda wywoływana podczas tworzenia okna, inicjalizuje elementy interfejsu oraz ustawia listener dla przycisku
     * @param savedInstanceState zachowany stan aplikacji
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        back = findViewById(R.id.back_button);
        back.setOnClickListener(this);
    }

    /**
     * Metoda obsługująca kliknięcie przycisku, przenosi użytkownika do głównego okna aplikacji.
     * @param view widok, na którym został wykonany kliknięcie
     */

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}