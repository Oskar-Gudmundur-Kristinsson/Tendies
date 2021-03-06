package com.example.trytrading_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

//TODO: Styling
//TODO: Add a different layout for landscape orientation

//TODO: Header
//TODO: Get stock information
//TODO: Footer menu - seperate class

//TODO: Refactor and move popup stuff to a separate class

public class StockViewActivity extends AppCompatActivity {

    private Button mButtonBuy;
    private Button mButtonSell;
    private PopupWindow popUp;
    private TextView mStockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);

        mStockName = (TextView) findViewById(R.id.stockName);

        Intent intent = getIntent();
        String stockName = intent.getStringExtra("KEY");
        mStockName.setText(stockName);

        mButtonBuy = (Button) findViewById(R.id.buttonBuy);
        mButtonSell = (Button) findViewById(R.id.buttonSell);
        popUp = new PopupWindow(this);
        LinearLayout layout = new LinearLayout(this);
        TextView s = new TextView(this);
        Button cancel = new Button(this);
        Button confirm = new Button(this);

        mButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.showAtLocation(layout, Gravity.CENTER_VERTICAL, 10, 10);
                popUp.update(10, 10, 900, 800);
            }
        });

        mButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.showAtLocation(layout, Gravity.CENTER_VERTICAL, 10, 10);
                popUp.update(10, 10, 900, 800);
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        s.setText(stockName);
        cancel.setText("Cancel");
        confirm.setText("Confirm");
        layout.addView(s, params);
        layout.addView(cancel, params);
        layout.addView(confirm, params);
        popUp.setContentView(layout);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });
    }
}