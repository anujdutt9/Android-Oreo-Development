package com.timestable.android.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    // Function to do Multipltcation for TimesTable
    public void getTimesTable(int progress){
        // Define array of integer values from 1 to 10
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i=1; i<11; i++){
            arrayList.add(Integer.toString(i * progress));
        }

        // Set the Values in List View
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define ListView Object
        listView = (ListView)findViewById(R.id.listView);

        // Define Seekbar Object
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        // Set Min and Max Values
        seekBar.setMin(1);
        seekBar.setMax(21);
        seekBar.setProgress(10);
        getTimesTable(10);

        // On change in SeekBar, change the table values
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Info", String.valueOf(progress));
                getTimesTable(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
