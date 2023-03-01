package edu.mob.sudoku.Game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

import edu.mob.sudoku.R;

/**
 * Klasa reprezentująca okno wyboru poziomu trudności gry.
 * Umożliwia wybór poziomu trudności oraz powrót do głównego okna aplikacji.
 * @author Kinga Spytkowska, Patrycja Oświęcimska
 */

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * Przycisk do wyboru poziomu łatwego
     */
    Button easyButton;

    /**
     * Przycisk do wyboru poziomu średniego
     */
    Button mediumButton;

    /**
     * Przycisk do wyboru poziomu trudnego
     */
    Button hardButton;

    /**
     * Przycisk do powrotu do głównego okna aplikacji
     */
    Button back;


    /**
     * Zmienne do przekazania informacji jaki poziom gry został wybrany.
     */
    static String choice;
    static int choose;


    /** Prywatne, statyczne pole
     * deklarujące listę z wartościami dla poziomu easy
     */

    private static List<String> easy = List.of( "003010000052067000004020063000000610291603708060700230106008050038500000520136004",
            "070415000102396040600087509008152070260900100541008300420073060906001704010049080",
            "039040000024009700508000020685400100413090057097035406006050000002900001040260070",
            "704205603628009507009176482060457008502900060483020759845713926230594071190802345",
            "000702010000800300120000000069201403010060700300900260005087604048395007031000859",
            "469570138508000467173460590700005683300006715000701249900054800680200050205083974",
            "200319807075268090089075236600003000407182309908640510000000608020006140806020003",
            "060381700090020061108049005910000503007493000023010987000004006000060300080007059",
            "004036075968000413000104060100069084409010050780050120040805000391007008025001600",
            "070060502005073960009015708700926304901047020040008079000090480480501097200080050");

    /** Prywatne, statyczne pole
     * deklarujące listę z wartościami dla poziomu hard
     */

    private static List<String> hard =List.of( "040100083005000009000290100000306200900807300300500076050083600030651007001000538",
            "070020408018000006000001000205000800003700004000940000850000001000630009069000500",
            "800000000700900500000305861060031000001040300080020710005007000498000072000004109",
            "300500000590043001000100000009030540000924036634000009000080023013760400002359007",
            "049050700001000300070000040200789010430000600018004250700500100100006084000402076",
            "006910230002006010000000007000038040380590060070000080004000000920001000800205000",
            "006009100400000060800401030020000400794386001000140000160003049900000000007098500",
            "040020750000000413100090600000401005007000000300000100500906004900300000070040030",
            "800900050010500937050006801480720500365098100790000300128000095509802010000050028",
            "090000705306008000007000100204000000000802000980005600030400026100000570800600030");

    /** Prywatne, statyczne pole
     * deklarujące listę z wartościami dla poziomu medium
     */
    private static List<String> medium = List.of("905318000800020000060000080070980140100650007003100050704801520608037000200000060",
            "705002008034005200601000000506000000000013400000400090160300829302900007000820000",
            "520007041700062090009000000006080005100000082003009706682001000000008009051006804",
            "341000008680010000027000400000000280090800175802006300100030790000002804000008030",
            "603100070210000658800027001000801000900400810000000940060084100000210307000309004",
            "007052061869100700102907008030000509090000000700500100670410053020300610013020800",
            "806009020150074683700060000983016452014805976675092038027600009008000215490023067",
            "083000095140305070070204100901020008054000900000000000000910200007040000010506380",
            "840020000612090080935078400270500160006007290000061800401906078763800009500000020",
            "060305008004070000000604302009000284040000000301008000480590000070201540000040060");

    /**
     * Statyczna zmienna 'random' z klasy Random służy do generowania liczb pseudolosowych.
     */
    static Random random = new Random();

    /** Prywatne, statyczne pole
     * losujące liczbę z przedziału od 1-10
     */
    private static int index = random.nextInt(10);

    /** Prywatne, statyczne pole
     * wybierające z listy o nazwie 'easy' jeden String
     * w celu zainicjalizowania planszy jeśli użytkownik wybierze poziom easy
     */
    private static String randomValueEasy = easy.get(0);

    /** Prywatne, statyczne pole
     * wybierające z listy o nazwie 'medium' jeden String
     * w celu zainicjalizowania planszy jeśli użytkownik wybierze poziom medium
     */
    private static String randomValueMedium = medium.get(index);

    /** Prywatne, statyczne pole
     * wybierające z listy o nazwie 'hard' jeden String
     * w celu zainicjalizowania planszy jeśli użytkownik wybierze poziom hard
     */
    private static String randomValueHard = hard.get(index);

    /**
     * Metoda wywoływana podczas tworzenia okna, inicjalizuje elementy interfejsu oraz ustawia listener dla przycisków.
     * @param savedInstanceState zachowany stan aplikacji
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        easyButton = findViewById(R.id.easy_button);
        mediumButton = findViewById(R.id.medium_button);
        hardButton = findViewById(R.id.hard_button);
        back = findViewById(R.id.back_button);

        easyButton.setOnClickListener(this);
        mediumButton.setOnClickListener(this);
        hardButton.setOnClickListener(this);
        back.setOnClickListener(this);


    }

    /**
     * Metoda obsługująca kliknięcie przycisków w oknie wyboru poziomu trudności,
     * przekierowuje do odpowiedniego okna i ustawia poziom trudności.
     * @param view widok, na którym został wykonany kliknięcie
     */


    @Override
    public void onClick(View view) {
        if(view.getId() == easyButton.getId()){
            Intent intent = new Intent(this, MainActivity.class );
            choice = randomValueEasy;
            choose = 1;
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Pamiętaj, gra zakończy się jeśli popełnisz 10 błędów.", Toast.LENGTH_SHORT).show();

        }else if(view.getId() == mediumButton.getId()) {
            Intent intent = new Intent(this, MainActivity.class);
            choice = randomValueMedium;
            choose = 2;
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Pamiętaj, gra zakończy się jeśli popełnisz 6 błędów.", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == hardButton.getId()){
            Intent intent = new Intent(this, MainActivity.class);
            choice = randomValueHard;
            choose = 3;
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Pamiętaj, gra zakończy się jeśli popełnisz 3 błędy.", Toast.LENGTH_SHORT).show();
        }else if(view.getId() == back.getId()){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }

    }
}