package edu.mob.sudoku.Game;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import edu.mob.sudoku.R;

/**
 *Klasa informująca o obecnych współrzędnych
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */

class CurrentClickedEditText {

    /**
     * Ustawienie wiersza i kolumny na -1
     */
    int i = -1;
    int j = -1;

    /**
     * Pole odpowiedzialne za tło
     */
    Drawable originalDrawable = null;

    /**
     * Metoda ustawiająca aktualne położenie oraz tło
     * @param i wiersz
     * @param j kolumna
     * @param drawable tło
     */

    public void setClickedPositionAndBackground(int i, int j, Drawable drawable){
        this.i = i;
        this.j = j;
        this.originalDrawable = drawable;
    }


    /**
     * Metoda sprawdzająca czy bieżące kliknięcie jest prawidłowe
     * @return czy kliknięcie jest prawidłowe
     */

    public boolean isCurrentClickValid(){
        if(this.i == -1 || this.j == -1){
            return false;
        }
        return true;
    }
}


/**
 * Klasa odpowiedzialna za działanie głównego ekranu gry.
 * Zawiera logikę dotyczącą interfejsu użytkownika i funkcjonalności gry.
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Przycisk pozwalający do przejścia do menu
     */
    Button home;

    /**
     * Przycisk pozwalający na rozpoczęcie nowej gry
     */
    Button new_game;

    /**
     * Przycisk do zamknięcia aplikacji
     */
    Button exit;

    /**
     * Przycisk do wyczyszczenia całej planszy
     */
    Button clearA;

    /**
     * Przycisk do wyczyszczenia jednego elementu
     */
    Button clearO;

    /**
     * TextView odpowiadający za wyświetlanie licznika błędów
     */

    TextView count;

    /**
     * Chronometr odpowiedzialny za wyświetlanie czasu
     */
    Chronometer chronometer;

    /**
     * Lista przechowująca zdobyte punkty podczas gry
     */

    static List<Float> scores = new ArrayList<>();

    /**
     * Zmienna przechowująca liczbę punktów
     */

    public float punctation = 0;

    /**
     * Zmienna przechowująca liczbę pomyłek
     */
    private int mistakes = 0;

    /**
     * Lista przetrzymująca przyciski numeryczne
     */

    private List<Button> buttons = new ArrayList<>();

    /** Prywatne pole
     * określające wielkość planszy.
     */
    private final int SIZE = 9;

    /** Prywatne pole określające
     * dwuwymiarową tablicę obiektów typu editText.
     */

    private EditText editTexts[][] = new EditText[SIZE][SIZE];

    /** Prywatne pole określające
     * dwuwymiarową tablicę obiektów typu int.
     */
    private int[][] board = new int[SIZE][SIZE];

    /**
     * Obiekt klasy CurrentClickedEditText
     */
    private CurrentClickedEditText currentClickedEditText = new CurrentClickedEditText();


    /**
     * Metoda wywoływana podczas tworzenia okna, inicjalizuje elementy interfejsu oraz ustawia listener dla przycisków,
     * ponadto wywołuje odpowiednie metody
     * @param savedInstanceState zachowany stan aplikacji
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(this);

        new_game = findViewById(R.id.newGameButton);
        new_game.setOnClickListener(this);

        exit = findViewById(R.id.exitButton);
        exit.setOnClickListener(this);

        clearA = findViewById(R.id.clearAll);
        clearA.setOnClickListener(this);

        clearO = findViewById(R.id.clearOne);
        clearO.setOnClickListener(this);

        count = findViewById(R.id.counter);
        chronometer = findViewById(R.id.chronometer);



        initBoard(LevelActivity.choice);
        initEditTexts();
        setEditTextValues();
        setUnchangeableEditTexts();
        setEditTextsListeners();
        chronometer.start();


        initNumericalButtons();
        applyBehaviourToNumericalButtons();


    }



    /**
     * Metoda inicjalizująca planszę odpowiednimi wartościami.
     * @param numbers wylosowana zmienna w ramach danego levelu
     */

    private void initBoard(String numbers){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                int n = j + (SIZE*i);
                this.board[i][j] = Integer.parseInt(String.valueOf(numbers.charAt(n)));
            }
        }

    }

    /**
     * Metoda inicjalizująca każdy z przycisków na planszy
     */

    private void initEditTexts() {
        editTexts[0][0] = findViewById(R.id.oneb1);
        editTexts[0][1] = findViewById(R.id.oneb2);
        editTexts[0][2] = findViewById(R.id.oneb3);
        editTexts[0][3] = findViewById(R.id.twob1);
        editTexts[0][4] = findViewById(R.id.twob2);
        editTexts[0][5] = findViewById(R.id.twob3);
        editTexts[0][6] = findViewById(R.id.threeb1);
        editTexts[0][7] = findViewById(R.id.threeb2);
        editTexts[0][8] = findViewById(R.id.threeb3);

        editTexts[1][0] = findViewById(R.id.oneb4);
        editTexts[1][1] = findViewById(R.id.oneb5);
        editTexts[1][2] = findViewById(R.id.oneb6);
        editTexts[1][3] = findViewById(R.id.twob4);
        editTexts[1][4] = findViewById(R.id.twob5);
        editTexts[1][5] = findViewById(R.id.twob6);
        editTexts[1][6] = findViewById(R.id.threeb4);
        editTexts[1][7] = findViewById(R.id.threeb5);
        editTexts[1][8] = findViewById(R.id.threeb6);

        editTexts[2][0] = findViewById(R.id.oneb7);
        editTexts[2][1] = findViewById(R.id.oneb8);
        editTexts[2][2] = findViewById(R.id.oneb9);
        editTexts[2][3] = findViewById(R.id.twob7);
        editTexts[2][4] = findViewById(R.id.twob8);
        editTexts[2][5] = findViewById(R.id.twob9);
        editTexts[2][6] = findViewById(R.id.threeb7);
        editTexts[2][7] = findViewById(R.id.threeb8);
        editTexts[2][8] = findViewById(R.id.threeb9);

        editTexts[3][0] = findViewById(R.id.fourb1);
        editTexts[3][1] = findViewById(R.id.fourb2);
        editTexts[3][2] = findViewById(R.id.fourb3);
        editTexts[3][3] = findViewById(R.id.fiveb1);
        editTexts[3][4] = findViewById(R.id.fiveb2);
        editTexts[3][5] = findViewById(R.id.fiveb3);
        editTexts[3][6] = findViewById(R.id.sixb1);
        editTexts[3][7] = findViewById(R.id.sixb2);
        editTexts[3][8] = findViewById(R.id.sixb3);

        editTexts[4][0] = findViewById(R.id.fourb4);
        editTexts[4][1] = findViewById(R.id.fourb5);
        editTexts[4][2] = findViewById(R.id.fourb6);
        editTexts[4][3] = findViewById(R.id.fiveb4);
        editTexts[4][4] = findViewById(R.id.fiveb5);
        editTexts[4][5] = findViewById(R.id.fiveb6);
        editTexts[4][6] = findViewById(R.id.sixb4);
        editTexts[4][7] = findViewById(R.id.sixb5);
        editTexts[4][8] = findViewById(R.id.sixb6);

        editTexts[5][0] = findViewById(R.id.fourb7);
        editTexts[5][1] = findViewById(R.id.fourb8);
        editTexts[5][2] = findViewById(R.id.fourb9);
        editTexts[5][3] = findViewById(R.id.fiveb7);
        editTexts[5][4] = findViewById(R.id.fiveb8);
        editTexts[5][5] = findViewById(R.id.fiveb9);
        editTexts[5][6] = findViewById(R.id.sixb7);
        editTexts[5][7] = findViewById(R.id.sixb8);
        editTexts[5][8] = findViewById(R.id.sixb9);

        editTexts[6][0] = findViewById(R.id.sevenb1);
        editTexts[6][1] = findViewById(R.id.sevenb2);
        editTexts[6][2] = findViewById(R.id.sevenb3);
        editTexts[6][3] = findViewById(R.id.eightb1);
        editTexts[6][4] = findViewById(R.id.eightb2);
        editTexts[6][5] = findViewById(R.id.eightb3);
        editTexts[6][6] = findViewById(R.id.nineb1);
        editTexts[6][7] = findViewById(R.id.nineb2);
        editTexts[6][8] = findViewById(R.id.nineb3);

        editTexts[7][0] = findViewById(R.id.sevenb4);
        editTexts[7][1] = findViewById(R.id.sevenb5);
        editTexts[7][2] = findViewById(R.id.sevenb6);
        editTexts[7][3] = findViewById(R.id.eightb4);
        editTexts[7][4] = findViewById(R.id.eightb5);
        editTexts[7][5] = findViewById(R.id.eightb6);
        editTexts[7][6] = findViewById(R.id.nineb4);
        editTexts[7][7] = findViewById(R.id.nineb5);
        editTexts[7][8] = findViewById(R.id.nineb6);

        editTexts[8][0] = findViewById(R.id.sevenb7);
        editTexts[8][1] = findViewById(R.id.sevenb8);
        editTexts[8][2] = findViewById(R.id.sevenb9);
        editTexts[8][3] = findViewById(R.id.eightb7);
        editTexts[8][4] = findViewById(R.id.eightb8);
        editTexts[8][5] = findViewById(R.id.eightb9);
        editTexts[8][6] = findViewById(R.id.nineb7);
        editTexts[8][7] = findViewById(R.id.nineb8);
        editTexts[8][8] = findViewById(R.id.nineb9);
    }

    /**
     * Metoda zmieniająca zera na puste pola.
     */

    private void setEditTextValues(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(board[i][j] == 0){
                    editTexts[i][j].setText("");
                }else{
                    editTexts[i][j].setText(Integer.toString(board[i][j]));
                }

            }
        }
    }

    /**
     * Metoda blokująca pola, z wartościami zainicjalizowanymi, przed zmianą.
     */

    private void setUnchangeableEditTexts(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(board[i][j] != 0) {
                    editTexts[i][j].setOnTouchListener(getOnTouchIngorer(true));
                    editTexts[i][j].setEnabled(false);
                    editTexts[i][j].setTextColor(Color.BLACK);
                    editTexts[i][j].setTypeface(null, Typeface.BOLD);
                }
            }
        }
    }

    /**
     * Metoda ustawiająca czy dane miejsce ma ignorować kliknięcie czy nie
     * @param choice czy ustawić ignorowanie
     * @return choice
     */

    private View.OnTouchListener getOnTouchIngorer(boolean choice) {
        return new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return choice;
            }
        };
    }

    /**
     * Metoda ustawiająca nasłuchiwacze
     * na pola na planszy.
     */


    private void setEditTextsListeners(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++){
                editTexts[i][j].addTextChangedListener(getEditTextOnChangeBehaviour(i, j));
                editTexts[i][j].setOnClickListener(getEditTextOnClickBehaviour(i, j));
            }
        }
    }

    /**
     * Metoda zapewniająca instancję klasy implementującą zachowaine na zmianę editText[i][j].
     * @param i numer wiersza
     * @param j numer kolumny
     * @return zwraca nową instancję klasy EditTextTextWatcher
     */


    private TextWatcher getEditTextOnChangeBehaviour(int i, int j){
        return new EditTextTextWatcher(i, j);
    }

    /**
     * Metoda zapewniająca instancję klasy implementującą zachowaine na kliknięcie na editText[i][j].
     * @param i numer wiersza
     * @param j numer kolumny
     * @return zwraca nową instancję klasy EditTextOnCLickListener
     */

    private View.OnClickListener getEditTextOnClickBehaviour(int i, int j) {
        return new EditTextOnClickListener(i, j);
    }

    /**
     * Metoda dodająca każdy przycisk numeryczny do tablicy przycisków.
     */

    private void initNumericalButtons() {
        buttons.add(findViewById(R.id.one));
        buttons.add(findViewById(R.id.two));
        buttons.add(findViewById(R.id.three));
        buttons.add(findViewById(R.id.four));
        buttons.add(findViewById(R.id.five));
        buttons.add(findViewById(R.id.six));
        buttons.add(findViewById(R.id.seven));
        buttons.add(findViewById(R.id.eight));
        buttons.add(findViewById(R.id.nine));
    }


    /**
     * Metoda obsługująca przyciski numeryczne
     */

    private void applyBehaviourToNumericalButtons() {
        for(int i = 0; i < buttons.size(); i++) {
            int buttonValue = i + 1;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentClickedEditText.isCurrentClickValid()) {
                        editTexts[currentClickedEditText.i][currentClickedEditText.j].setText(Integer.toString(buttonValue));
                    }
                }
            });
        }
    }



    /**
     * Metoda przypisująca liczby wpisane przez użytkownika
     * do głównej tablicy.
     */
    private void assignEditTextValueToBoard(int i, int j) {
        try {
            String editTextAsString = editTexts[i][j].getText().toString();
            board[i][j] = Integer.parseInt(editTextAsString);
        }catch (Exception e) {
            board[i][j] = 0;
        }

    }

    /**
     * Metoda ustawiająca licznik błędów
     * @param mistacke licza błędów
     */

    public void mistackes(int mistacke){
        count.setText(String.valueOf(mistacke));
        count.setEnabled(false);

    }

    /**
     * Metoda sprawdzająca czy wpisana liczba na planszy jest poprawna.
     * Jeśli jest poprawna to kolor tła będzie zielony.
     * Jeśli jest niepoprawna to kolor tła będzie czerwony,
     * licznik błędów się zwiększy
     * a w przypadku zbyt dużej ilości błędów wyświetli się komunikat.
     */

    private void checkBoard(int i, int j){
        boolean isCorrectNumber = BoardChecker.isCorrectPlace(board, i, j);
        if(isCorrectNumber){
            editTexts[i][j].setTextColor(Color.parseColor("#FF6200EE"));
            editTexts[i][j].setTypeface(null, Typeface.BOLD);
        }else{
            editTexts[i][j].setTextColor(Color.RED);
            editTexts[i][j].setTypeface(null, Typeface.BOLD);
            mistakes++;
            mistackes(mistakes);
            if(LevelActivity.choose == 1 && mistakes >= 10){
                showMistakesAlert(10);
            } else if (LevelActivity.choose == 2 && mistakes >= 6) {
                showMistakesAlert(6);
            }else if (LevelActivity.choose == 3 && mistakes >= 3) {
                showMistakesAlert(3);
            }
        }
    }

    /**
     * Metoda informująca o ilości popełnionych błędów.
     * Pyta czy gracz chce zagrać ponownie.
     * Jeśli tak to resetuje planszę,
     * jeśli nie to zamyka program.
     */


    private void showMistakesAlert(int y){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("Koniec gry");
        builder.setMessage("Popełniłeś " + y +
                " błędów. Czy chcesz zagrać ponownie?");
        builder.setCancelable(false);
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearAll();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        AlertDialog dialog = builder.show();

        dialog.show();
    }

    /**
     * Metoda informująca o fakcie, że gra się skończyła.
     * Wyświetla wynik oraz czas gry.
     * Pyta czy gracz chce zagrać ponownie.
     * Jeśli tak to resetuje planszę,
     * jeśli nie to zamyka program.
     */

    public void showAlertOnTheGameEnd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("Gratulacje! Wygrałeś!");
        builder.setMessage("Twój czas to: " + chronometer.getText() + ".\nPopełniłeś: " +
                mistakes + " błędów." +
                "\nZdobyłeś: " + punctation(mistakes) + " punktów. \nCzy chcesz zagrać ponownie? ");
        builder.setCancelable(false);
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        builder.create().show();

    }

    /**
     * Metoda sprawdzająca czy wpisana liczba na planszy jest poprawna.
     * Jeśli jest poprawna to kolor napisu będzie fioletowy
     * Jeśli jest niepoprawna to kolor napisu będzie czerwony,
     * licznik błędów się zwiększy
     * a w przypadku zbyt dużej ilości błędów wyświetli się komunikat.
     */

    private void checkGameOver(){
        if(BoardChecker.isGameOver(board)){
            float score = punctation(mistakes);
            addToStatistics(score);
            chronometer.stop();
            showAlertOnTheGameEnd();

        }

    }

    /**
     * Metoda obliczająca punkty
     * @param mistakes liczba pomyłek
     * @return liczba punktów
     */

    public float punctation(int mistakes){
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        return punctation =(10000 - (50 * mistakes)) /(elapsedMillis/1000) ;
    }

    /**
     * Metoda czyszcząca wszystkie wpisane liczby na planszy
     */

    private void clearAll(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(editTexts[i][j].isEnabled() == true){
                    editTexts[i][j].setText("");
                    mistakes--;
                    mistackes(mistakes);
                }
            }
        }
    }

    /**
     * Metoda czyszcząca wybrany element na planszy
     */

    private void clearOne(){
        if(currentClickedEditText.isCurrentClickValid()){
            editTexts[currentClickedEditText.i][currentClickedEditText.j].setText("");
            mistakes--;
            mistackes(mistakes);
        }
    }

    /**
     * Metoda dodająca wynik do listy ze statystykami
     * @param punctation uzyskany wynik
     */


    public void addToStatistics(float punctation) {
        MainActivity.scores.add(punctation);
    }



    /**
     * Metoda obsługująca kliknięcie przycisków, przenosi użytkownika do odpowiedniego okna w aplikacji.
     * @param view widok, na którym został wykonany kliknięcie
     */
    @Override
    public void onClick(View view) {
        if(view.getId() == home.getId()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else if(view.getId() == new_game.getId()){
            Intent intent = new Intent(this, LevelActivity.class);
            startActivity(intent);
        }else if(view.getId() == exit.getId()){
            finishAffinity();
        }else if(view.getId() == clearA.getId()){
            clearAll();
        }else if(view.getId() == clearO.getId()){
            clearOne();
        }

    }

    /**
     *Klasa implementująca TextWatcher czyli nasłuchiwacz na zmiany tekstu
     */

    class EditTextTextWatcher implements TextWatcher {

        /**
         * Zmienne określające wiersz i kolumnę na planszy
         */
        private final int i;
        private final int j;

        /**
         * Konstruktor klasy EditTextTextWatcher
         * @param i wiersz
         * @param j kolumna
         */

        EditTextTextWatcher(int i, int j) {
            this.i = i;
            this.j = j;
        }

        /**
         * Zachowanie przed zmianą tekstu
         */

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        /**
         * Zachowanie w trakcie zmiany tekstu
         */

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        /**
         * Zachowanie po zmianie tekstu
         */

        @Override
        public void afterTextChanged(Editable editable) {
            assignEditTextValueToBoard(this.i, this.j);
            checkBoard(this.i, this.j);
            checkGameOver();

        }
    }

    /**
     *Klasa implementująca OnClickListener czyli nasłuchiwacz na kliknięcie
     */
    class EditTextOnClickListener implements View.OnClickListener {


        /**
         * Zmienne określające wiersz i kolumnę na planszy
         */

        private final int i;
        private final int j;

        /**
         * Konstruktor klasy EditTextOnClickListener
         * @param i wiersz
         * @param j kolumna
         */

        EditTextOnClickListener(int i, int j) {
            this.i = i;
            this.j = j;

        }

        /**
         * Metoda obsługująca kliknięcie przycisków
         * Jeśli pole zostanie wybrane to zmieni się tło
         * @param view widok, na którym został wykonany kliknięcie
         */

        @Override
        public void onClick(View view) {
            setPreviousClickedFieldBackground();
            Drawable originalDrawable = editTexts[this.i][this.j].getBackground();
            currentClickedEditText.setClickedPositionAndBackground(this.i, this.j, originalDrawable);
            setClickedFieldBackground();

        }

        /**
         * Metoda ustawiająca tło pola przed kliknięciem
         */

        private void setPreviousClickedFieldBackground(){
            if(currentClickedEditText.isCurrentClickValid()){
                editTexts[currentClickedEditText.i][currentClickedEditText.j].setBackground(currentClickedEditText.originalDrawable);
            }
        }

        /**
         * Metoda ustawiająca tło pola w trakcie kliknięcia
         */

        private void setClickedFieldBackground(){
            editTexts[this.i][this.j].setBackgroundResource(R.drawable.lightborder);

        }
    }

}


