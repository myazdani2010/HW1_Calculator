package com.hw1.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private TextView display;
    private Boolean typing = false;
    private CalculatorFunction calculatorFunction;
    private static final String DIGITS = "01234567890123.";
    DecimalFormat df = new DecimalFormat("@###########");

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate() in Main");
        setContentView(R.layout.activity_main);

        calculatorFunction = new CalculatorFunction();
        display = (TextView) findViewById(R.id.result);

        if(getIntent().getStringExtra("result2") != null)
            display.setText(getIntent().getStringExtra("result2"));

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(12);

        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.subtract).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.dev).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            if (typing) {

                if (buttonPressed.equals(".") && display.getText().toString().contains(".")) {
                } else {
                    display.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    display.setText(0 + buttonPressed);
                } else {
                    display.setText(buttonPressed);
                }

                typing = true;
            }

        } else {
            if (typing) {
                System.out.println("display text= "+display.getText());
                calculatorFunction.setOperand(Double.parseDouble(display.getText().toString()));
                typing = false;
            }

            calculatorFunction.performOperation(buttonPressed);
            display.setText(df.format(calculatorFunction.getResult()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("OPERAND", calculatorFunction.getResult());
        outState.putDouble("MEMORY", calculatorFunction.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorFunction.setOperand(savedInstanceState.getDouble("OPERAND"));
        calculatorFunction.setMemory(savedInstanceState.getDouble("MEMORY"));
        display.setText(df.format(calculatorFunction.getResult()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.menu_advance)
        {
            System.out.println(display.getText());
            Intent intent = new Intent(MainActivity.this, AdvanceMode.class);
            intent.putExtra("result", display.getText().toString());
            startActivityForResult(intent, 1);
        }

        return true;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.out.println("OnBack Call in Main");
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data )
    {
        System.out.println(requestCode);
        System.out.println(resultCode);
        System.out.println(data);
        System.out.println("onResume() call in main " + data.getStringExtra("result2"));
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String stredittext = data.getStringExtra("result2");
            }
        }

        display.setText(data.getStringExtra("result2"));
        super.onActivityResult(requestCode,resultCode,data);

    }

}
