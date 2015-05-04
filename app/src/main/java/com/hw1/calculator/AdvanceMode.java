package com.hw1.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class AdvanceMode extends ActionBarActivity implements OnClickListener {

    private TextView display;
    private CalculatorFunction calculatorFunction;
    private static final String DIGITS = "01234567890123.";
    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate() in AdvanceMode");
        setContentView(R.layout.activity_advance_mode);

        calculatorFunction = new CalculatorFunction();
        display = (TextView) findViewById(R.id.result2);
        System.out.println(getIntent().getStringExtra("result"));
        display.setText(getIntent().getStringExtra("result"));

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(12);

        findViewById(R.id.clear2).setOnClickListener(this);
        findViewById(R.id.sin).setOnClickListener(this);
        findViewById(R.id.cos).setOnClickListener(this);
        findViewById(R.id.tan).setOnClickListener(this);
        findViewById(R.id.i).setOnClickListener(this);
        findViewById(R.id.ln).setOnClickListener(this);
        findViewById(R.id.log).setOnClickListener(this);
        findViewById(R.id.pi).setOnClickListener(this);
        findViewById(R.id.e).setOnClickListener(this);
        findViewById(R.id.mode).setOnClickListener(this);
        findViewById(R.id.factorial).setOnClickListener(this);
        findViewById(R.id.sqroot).setOnClickListener(this);
        findViewById(R.id.power).setOnClickListener(this);
        findViewById(R.id.open).setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();

        System.out.println(buttonPressed);
        System.out.println("display text= "+display.getText());
        calculatorFunction.setOperand(Double.parseDouble(display.getText().toString()));
        calculatorFunction.performOperation(buttonPressed);
        display.setText(df.format(calculatorFunction.getResult()));

        Intent intent = new Intent();
        intent.putExtra("result2", display.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.out.println("OnBack Call in Advance "+ display.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("result2", display.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
//        startActivityForResult(intent, 2);
    }
}