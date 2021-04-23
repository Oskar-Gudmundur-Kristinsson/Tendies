package com.example.trytrading_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO: Styling
//TODO: Add a different layout for landscape orientation

//TODO: Add stock information to list view
//TODO: Implement search and filters
//TODO: Link to watchlist
//TODO: Footer menu

public class StockListActivity extends AppCompatActivity {

    private ListView mStocksList;

    private String[] stocks = {"Gold", "Bitcoin", "Ripple", "Ethereum", "USD", "EUR", "Palladium", "Platinum", "Silver", "GBP"};
    private List<String> allStocks = new ArrayList<String>(Arrays.asList(stocks));
    public String currentStockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allStocks);

        mStocksList = (ListView) findViewById(R.id.listView);
        mStocksList.setAdapter(arrayAdapter);

        mStocksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listItem, View v, int position, long id) {
                Intent i = new Intent(StockListActivity.this, StockViewActivity.class);
                currentStockName = listItem.getItemAtPosition(position).toString();
                i.putExtra("KEY", currentStockName);
                System.out.println(currentStockName);
                startActivity(i);
            }
        });
    }
}