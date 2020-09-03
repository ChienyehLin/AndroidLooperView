package com.example.looperview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    OpeningLooperView mOpeningLooperView;
    Button mButtonTry;
    ArrayList<ImageView> mList;
    ArrayList<String> mMainTitle;
    ArrayList<String> mSubTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initView() {
        mOpeningLooperView = findViewById(R.id.opening_looper_view);
        mOpeningLooperView.setData(new OpeningLooperView.InnerPageAdapter(mList.size()) {
            @Override
            protected View getItemView(ViewGroup container, int itemPosition) {
                return mList.get(itemPosition);
            }
        }, new OpeningLooperView.TitleBindListener() {
            @Override
            public String getTitle(int position) {
                return mMainTitle.get(position);
            }

            @Override
            public String getSubTitle(int position) {
                if(position ==0)
                    return  mSubTitle.get(0);
                return null;
            }
        });
        mButtonTry=mOpeningLooperView.getButtonView();
        mButtonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Try it now",Toast.LENGTH_SHORT).show();
                return;
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

        mMainTitle = new ArrayList<>();
        mMainTitle.add("Motion Graphics Titles");
        mMainTitle.add("Experience the freedom of creation");
        mMainTitle.add("Fine-tune your aesthetic");
        mMainTitle.add("Inspire your Imagination");
        mMainTitle.add("Having fun exploring a vast stock library");
        mSubTitle = new ArrayList<>();
        mSubTitle.add("Galvanize your videos with cinematic titles");
    }
}