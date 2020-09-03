package com.example.looperview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class OpeningLooperView extends LinearLayout {

    private LinearLayout mPointContainer;
    private TextView mSubtitle;
    private TextView mMainTitle;
    private ViewPager mViewPager;
    private TitleBindListener mTitleBindListener;
    private int mOldSelectedPosition=0;

    public OpeningLooperView(Context context) {
        this(context,null);
    }

    public OpeningLooperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OpeningLooperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_opening_looper_view,this,true);
        initView();
        initScrollEvent();
    }

    private void initScrollEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePointIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.intro_pager);
        mMainTitle = findViewById(R.id.intro_mainTitle);
        mSubtitle = findViewById(R.id.intro_subtitle);

        //point Container, points need to be created dynamically based on the number of views in view pager
        mPointContainer = findViewById(R.id.intro_point_container);

    }
    /**
     * user can define title outside the class
     * */
    public interface TitleBindListener {
        String getTitle(int position);
    }

    /**update the bottom points when the selected page is changed
     * */
    public void updatePointIndicator(int position){
        mPointContainer.getChildAt(mOldSelectedPosition).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_n,null));
        mPointContainer.getChildAt(position).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_s,null));
        mOldSelectedPosition=position;

    }
    public void initPointIndicator(InnerPageAdapter innerPageAdapter){
        for(int i=0;i<innerPageAdapter.getDataSize();i++){
            View point = new View(getContext());
            LayoutParams layoutParams= new LayoutParams(SizeUtils.dip2px(getContext(),10),SizeUtils.dip2px(getContext(),10));
            layoutParams.setMargins(SizeUtils.dip2px(getContext(),5),0,SizeUtils.dip2px(getContext(),5),0);
            point.setLayoutParams(layoutParams);

            point.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_n,null));

            mPointContainer.addView(point);
        }
        updatePointIndicator(0);
    }
    public void setData(InnerPageAdapter innerPageAdapter,TitleBindListener listener) {
        mViewPager.setAdapter(innerPageAdapter);
        this.mTitleBindListener = listener;
        initPointIndicator(innerPageAdapter);
    }
    public static abstract class InnerPageAdapter extends PagerAdapter {
        protected  final List<ImageView> videos;

        protected InnerPageAdapter(List<ImageView> videos) {
            this.videos = videos;
        }

        public abstract int getDataSize();

        @Override
        public int getCount() {
            //viewpager的數量就是videos外面传进来的list大小
            return videos.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //载入view，至于显示什么view,不用管，由外面给进来。只要对position进行一个转换
            View itemView = videos.get(position);
            container.addView(itemView);
            return itemView;
        }

        //至于要什么view，我不管，由外面给我。
        protected abstract View getItemView(ViewGroup container,int itemPosition);

        @Override
        public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object) {
            container.removeView(videos.get(position));
        }
    }
}
