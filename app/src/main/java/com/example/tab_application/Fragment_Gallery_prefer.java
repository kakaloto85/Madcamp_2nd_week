package com.example.tab_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment_Gallery_prefer extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment__gallery_prefer);
        mContext = this;
        TabLayout tab = findViewById(R.id.tabs2);
        tab.getTabAt(0).setIcon(R.drawable.favorite_default);
        /* create adapter */
        final MyAdapter adapter = new MyAdapter (mContext);

        GridView gv = (GridView)findViewById(R.id.ImgGridView2);
        gv.setAdapter(adapter);

        /* back to default album button */
        ImageButton go_default = (ImageButton)findViewById(R.id.prefer_change_btn2);
        go_default.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        private ArrayList<String> img_path;

        public MyAdapter(Context context) {
            mContext = context;

            img_path = new ArrayList<String>();
            /* get prefer images path's*/
            prefer_arr path_arr = (prefer_arr) getApplication();
            img_path = path_arr.get_prefer_arr();
        }

        @Override
        public int getCount() {
            return img_path.size();
        }

        @Override
        public Object getItem(int position) {
            return img_path.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null){
                imageView = new ImageView(mContext);
                imageView.setAdjustViewBounds(false);
            }else{
                imageView = (ImageView) convertView;
            }

            Glide.with(mContext).load(img_path.get(position)).override(450, 350).centerCrop().into(imageView);
            return imageView;
        }
    }


}