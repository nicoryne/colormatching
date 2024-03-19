package com.example.midterms_colormatching;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ColorMatching extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnReturn;
    ArrayList<Button> buttonArrayList = new ArrayList<>();
    ArrayList<Color> colorArrayList = new ArrayList<>();
    int[] gameState = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    int colorCounter = 0;

    Color c1 = Color.valueOf(0, 255, 0, 100);
    Color c2 = Color.valueOf(255, 0, 0, 100);
    Color c3 = Color.valueOf(0, 0, 255, 100);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching);

        initButtons();
        fillButtonArrayList();
        fillColorArrayList();
        setButtonClickers();
        restartGame();
    }

    private void initButtons() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnReturn = findViewById(R.id.btnReturn);
    }

    private void fillButtonArrayList() {
        buttonArrayList.add(btn1);
        buttonArrayList.add(btn2);
        buttonArrayList.add(btn3);
        buttonArrayList.add(btn4);
        buttonArrayList.add(btn5);
        buttonArrayList.add(btn6);
        buttonArrayList.add(btn7);
        buttonArrayList.add(btn8);
        buttonArrayList.add(btn9);
    }

    private void fillColorArrayList() {
        colorArrayList.add(c1);
        colorArrayList.add(c2);
        colorArrayList.add(c3);
    }

    private void setButtonClickers() {
        for (Button button : buttonArrayList) {
            button.setOnClickListener(v -> {
                int index = buttonArrayList.indexOf(button);
                int left = index - 1;
                int right = index + 1;
                int up = index - 3;
                int down = index + 3;

                if(colorCounter > 2) {
                    colorCounter = 0;
                }


                if((index + 1) % 3 != 0) {
                    changeCellColor(right);
                }
                if(index % 3 != 0) {
                    changeCellColor(left);
                }
                changeCellColor(up);
                changeCellColor(down);

                colorCounter++;

                if(checkAllCellsMatchingColor()) {
                    endGame();
                }
            });
        }

        btnReturn.setOnClickListener(v -> restartGame());
    }

    private void changeCellColor(int index) {
        if(index >= 0 && index <= 8) {
            gameState[index] = colorCounter;
            buttonArrayList.get(index).setBackgroundColor(colorArrayList.get(colorCounter).toArgb());
        }
    }

    private boolean checkAllCellsMatchingColor() {
        int firstColor = gameState[0];
        for(int i = 0; i < gameState.length; i++) {
            if (gameState[i] != firstColor) {
                return false;
            }
        }

        return true;
    }

    private void endGame() {
        for(Button button: buttonArrayList) {
            button.setEnabled(false);
        }
        Toast.makeText(ColorMatching.this, "Winner!", Toast.LENGTH_SHORT).show();
    }

    private void restartGame(){
        Random rand = new Random();
        colorCounter = 0;
        Arrays.fill(gameState, 3);

        for(Button button: buttonArrayList) {
            int randInt = rand.nextInt(3);
            button.setEnabled(true);
            button.setBackgroundColor(colorArrayList.get(randInt).toArgb());
        }

    }
}