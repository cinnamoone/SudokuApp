package edu.mob.sudoku.Game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.mob.sudoku.R;

/**
 * Klasa reprezentująca okno z wynikami gry, wyświetla listę wyników posortowanych od najlepszego do najgorszego.
 * Umożliwia powrót do głównego okna aplikacji.
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Przycisk do powrotu do głównego okna aplikacji
     */
    Button back;

    /**
     * ListView wyświetlający wyniki gry
     */
    ListView result;

    /**
     * Metoda zwracająca posortowaną listę wyników gry
     * @return posortowana lista wyników
     */

    public List<Float> getScores(){
        Collections.sort(MainActivity.scores,new Comparator<Float>() {
            public int compare(Float o1, Float o2) {
                return (int) (o2 - o1);
            }
        });
        return MainActivity.scores;
    }

    /**
     * Metoda mapująca wyniki z posortowanej listy na listę Stringów
     * @param list posortowana lista wyników
     * @return lista Stringów z wynikami
     */


    private List<String> mapScores(List<Float> list){
        List<String> results = new ArrayList<>();
        for(int i =0; i<list.size();i++){
            String result = String.valueOf(i + 1) + ". " + list.get(i).intValue() + " punktów";
            results.add(result);
        }
        return results;
    }

    /**
     * Metoda wywoływana podczas tworzenia okna, inicjalizuje elementy interfejsu oraz ustawia listener dla przycisku.
     * @param savedInstanceState zachowany stan aplikacji
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        result = findViewById(R.id.results);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mapScores(getScores()));
        result.setAdapter(adapter);

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