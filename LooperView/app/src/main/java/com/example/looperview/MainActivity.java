package com.example.looperview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {
    OpeningLooperView mOpeningLooperView;
    ArrayList<ImageView> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpeningLooperView = findViewById(R.id.opening_looper_view);
        initData();


        mOpeningLooperView.setData(new OpeningLooperView.InnerPageAdapter(mList) {
            @Override
            public int getDataSize() {
                return mList.size();
            }

            @Override
            protected View getItemView(ViewGroup container, int itemPosition) {
                return null;
            }
        }, new OpeningLooperView.TitleBindListener() {
            @Override
            public String getTitle(int position) {
                return null;
            }
        });
    }

    public void initData() {
        mList = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.tutorial_1_16to9);
        mList.add(imageView);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.tutorial_2_16to9);
        mList.add(imageView);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.tutorial_3_16to9);
        mList.add(imageView);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.tutorial_4_16to9);
        mList.add(imageView);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.tutorial_5_16to9);
        mList.add(imageView);
    }
}