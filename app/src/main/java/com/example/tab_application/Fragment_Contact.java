package com.example.tab_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_Contact extends Fragment {
    protected ArrayList<PhoneBook> phoneBooks = new ArrayList<PhoneBook>();
    protected ListAdapter adapter;
    ItemTouchHelper helper;
    RecyclerView recyclerView;

    @Override
    //called when fragment is created
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get data from json
        //phoneBooks = Parser(getJsonString());
        //phoneBooks = PhoneBook_Loader.getData(getActivity());
        getData();
    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getResources().getAssets().open("phonebook.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }

    private void getData () {
        ArrayList name = new ArrayList();
        ArrayList number = new ArrayList();

        String [] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME };
        String [] arrPhoneProjection = {
                ContactsContract.CommonDataKinds.Phone.NUMBER };


        // get user list
        Cursor clsCursor = getActivity().getContentResolver().query (
                ContactsContract.Contacts.CONTENT_URI, arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1" ,
                null, null
        );


        //전화번호가 있는 사람들의 이름과 전화번호를 쿼리를 이용해 가져옴
        while( clsCursor.moveToNext() ) {
            String strContactId = clsCursor.getString( 0 );

            name.add(clsCursor.getString( 1 ));

            // phone number
            Cursor clsPhoneCursor = getActivity().getContentResolver().query (
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + strContactId,
                    null, null);

            while( clsPhoneCursor.moveToNext() )
            {
                number.add(clsPhoneCursor.getString( 0 ));

            }
            clsPhoneCursor.close();


        }
        clsCursor.close( );

        //data 객체에 값 채우기
        for (int i = 0; i < name.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            phoneBooks.add(new PhoneBook(((String) name.get(i)), (String) number.get(i)));
        }
    }

    @Override
    //called for composition of the screen after onCreate
    //similar with onCreate method of Activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__contact, container, false);
        Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(context);

        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        adapter = new ListAdapter(phoneBooks);
        recyclerView.setAdapter(adapter);


        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //RecyclerView에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(recyclerView);

        //Add new button
        ImageButton button = (ImageButton) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPhoneBook.class);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(data.hasExtra("name") && data.hasExtra("phone")){
                String name = data.getStringExtra("name");
                //name = ((TextView) findViewById(R.id.name)).setText(name);
                String phone = data.getStringExtra("phone");
                PhoneBook phoneBook = new PhoneBook(name, phone);
                phoneBooks.add(phoneBook);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void setUpRecyclerView(){
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                helper.onDraw(c,parent, state);
            }
        });
    }

    //Parsing
    public ArrayList<PhoneBook> Parser(String jsonString){
        String name = null;
        String phone = null;
        ArrayList<PhoneBook> array = new ArrayList<PhoneBook>();
        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("phonebook");

            for (int i = 0; i < jarray.length(); i++){
                JSONObject jsonObject = jarray.getJSONObject(i);
                PhoneBook contact = new PhoneBook(jsonObject.optString("name"),jsonObject.optString("phone"));
                array.add(contact);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}