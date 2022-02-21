package com.example.handlingsassistent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> arrList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();


        ListView shoppingListView = findViewById(R.id.shoppingListView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_layout,
                arrList);

        shoppingListView.setAdapter(adapter);
        //Möjliggör att kunna välja flera alternativ i listan.
        shoppingListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        shoppingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrList.remove(position);
                adapter.notifyDataSetChanged();
                saveData();
                return true;
            }
        });
    }


    public void addToList (View v){
        if( ((TextView) findViewById(R.id.inputText)).getText().toString().isEmpty()) {
            Log.d("Empty", "Input is empty");
        }
        else {
            arrList.add(((TextView) findViewById(R.id.inputText)).getText().toString());
            ((ListView) findViewById(R.id.shoppingListView)).invalidateViews();
            ((TextView) findViewById(R.id.inputText)).setText("");
            saveData();
        }
    }

    // Referens: https://developer.android.com/guide/topics/ui/dialogs.html
    public void clearList (View v){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.alert_title);
            alert.setMessage(R.string.alert_message);
            alert.setPositiveButton(R.string.alert_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    arrList.clear();
                    ((ListView) findViewById(R.id.shoppingListView)).invalidateViews();
                    saveData();
                }
            });
            alert.setNegativeButton(R.string.alert_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.create().show();

    }

    // Referens: https://www.youtube.com/watch?v=jcliHGR3CHo
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrList);
        editor.putString("task list", json);
        editor.apply();
    }
    // Referens: https://www.youtube.com/watch?v=jcliHGR3CHo
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        arrList = gson.fromJson(json, type);
        if(arrList == null){
            arrList = new ArrayList<String>();
        }

    }

    public void loadTemplateActivity(View v){
        Intent intent = new Intent(this, TemplateActivity.class);
        startActivity(intent);
    }

    public void loadSpendingActivity(View v){
        Intent intent = new Intent(this, SpendingActivity.class);
        startActivity(intent);
    }
}