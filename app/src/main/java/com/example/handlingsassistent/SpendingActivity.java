package com.example.handlingsassistent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpendingActivity extends AppCompatActivity {

    ArrayList<Integer> costList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        ListView costListView = findViewById(R.id.costListView);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this,
                R.layout.cost_list_layout,
                costList);
        costListView.setAdapter(adapter);
    }

    public void addCost(View v){
        TextView tv = findViewById(R.id.inputCost);
        costList.add(Integer.parseInt(tv.getText().toString()));
        ((ListView) findViewById(R.id.costListView)).invalidateViews();
        ((TextView) findViewById(R.id.totalCost)).setText("Total kostnad: " + Integer.toString(sumList(costList)));
        tv.setText("");

    }

    public int sumList(ArrayList<Integer> list){
        int sum = 0;
        for(int i : list){
            sum = sum + i;
        }
        return sum;
    }
}