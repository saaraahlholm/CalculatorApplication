package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewResult;
    private final StringBuilder currentInput = new StringBuilder();
    private final String operator = "";
    private boolean isEqualsClicked = false;

    Button buttonClear, buttonSlash, buttonMinus, buttonPlus, buttonEquals, buttonDot, buttonX;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        giveId(buttonClear,R.id.buttonClear);
        giveId(button7, R.id.button7);
        giveId(button8, R.id.button8);
        giveId(button9, R.id.button9);
        giveId(buttonSlash, R.id.buttonSlash);
        giveId(button4, R.id.button4);
        giveId(button5, R.id.button5);
        giveId(button6, R.id.button6);
        giveId(buttonX, R.id.buttonX);
        giveId(button1, R.id.button1);
        giveId(button2, R.id.button2);
        giveId(button3, R.id.button3);
        giveId(buttonMinus, R.id.buttonMinus);
        giveId(buttonDot, R.id.buttonDot);
        giveId(button0, R.id.button0);
        giveId(buttonEquals, R.id.buttonEquals);
        giveId(buttonPlus, R.id.buttonPlus);
    }
    public void giveId(Button button, int id){
        button = findViewById(id);
        button.setOnClickListener(this);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String btnText = btn.getText().toString();

        double result = 0;
        if (isEqualsClicked && !btnText.equals("=")) {
            currentInput.setLength(0);
            result = 0;
            isEqualsClicked = false;
        }

        switch (btnText) {
            case "Tyhjennä":
                currentInput.setLength(0);
                result = 0;
                textViewResult.setText("0");
                break;
            case "=":
                if (currentInput.length() > 0) {
                    try {
                        String[] parts = currentInput.toString().split("(?<=[-+X/])|(?=[-+X/])");

                        result = Double.parseDouble(parts[0]);

                        for (int i = 1; i < parts.length; i += 2) {
                            String operator = parts[i];
                            double number = Double.parseDouble(parts[i + 1]);

                            switch (operator) {
                                case "+":
                                    result += number;
                                    break;
                                case "-":
                                    result -= number;
                                    break;
                                case "X":
                                    result *= number;
                                    break;
                                case "/":
                                    if (number != 0) {
                                        result /= number;
                                    } else {
                                        textViewResult.setText("err");
                                        return;
                                    }
                                    break;
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                        textViewResult.setText("0");
                        currentInput.setLength(0);
                        return;
                    }
                    textViewResult.setText(currentInput + "=" + "\n" + String.valueOf(result));
                    isEqualsClicked = true;
                }
                break;
            default:
                currentInput.append(btnText);
                textViewResult.setText(currentInput.toString());
                break;
        }
    }
}