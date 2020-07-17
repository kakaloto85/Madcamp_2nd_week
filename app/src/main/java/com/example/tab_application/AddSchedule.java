package com.example.tab_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddSchedule extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        Button backButton = (Button) findViewById(R.id.back_to_calendar);
        Button saveButton = (Button) findViewById(R.id.save_schedule);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSchedule.super.onBackPressed();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        EditText title = (EditText) findViewById(R.id.new_title);
        EditText time = (EditText) findViewById(R.id.new_time);
        String ntitle = (String) title.getText().toString();
        String ntime = (String) time.getText().toString();
        if(!(ntitle.isEmpty()  && ntime.isEmpty())){
            bundle.putString("title", ntitle.toString());
            bundle.putString("time", ntime.toString());
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
        }
        else{
            setResult(Activity.RESULT_CANCELED, intent);
        }
        super.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Context context = getApplicationContext();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
