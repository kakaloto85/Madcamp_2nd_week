package com.example.tab_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class act_imgpop extends Activity implements OnClickListener{
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_imgpop);
        mContext = this;

        /* get uri from intent */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        final String imgPath = extras.getString("filename");

        /* show fullsized image */
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        Glide.with(this).load(imgPath).into(iv);

        /* back button */
        ImageButton btn = (ImageButton)findViewById(R.id.btn_back);
        btn.setOnClickListener(this);

        /* prefer button */
        final ImageButton prefer_btn = (ImageButton)findViewById(R.id.prefer_btn);
        prefer_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer_btn.setSelected(true);
                prefer_arr path_arr = (prefer_arr) getApplication();
                if(!path_arr.check_contains(imgPath))
                    path_arr.add_prefer_arr(imgPath);
            }
        });
    }

    /* function for back button
    * return to the tab2 fragment */
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_back:
                this.finish();
                break;
        }
    }
}