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

public class AddPhoneBook extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phonebook);

        Button backButton = (Button) findViewById(R.id.back);
        Button saveButton = (Button) findViewById(R.id.save);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPhoneBook.super.onBackPressed();
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
        EditText name = (EditText) findViewById(R.id.new_name);
        EditText phone = (EditText) findViewById(R.id.new_phone);
        String n = (String) name.getText().toString();
        String p = (String) phone.getText().toString();
        if(!(n.isEmpty()  && p.isEmpty())){
            bundle.putString("name", n.toString());
            bundle.putString("phone", p.toString());
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
