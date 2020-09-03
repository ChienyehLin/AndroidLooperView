package com.example.looperview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button mButtonTry;
    private TitleBindListener mTitleBindListener;
    private int mOldSelectedPosition = 0;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public OpeningLooperView(Context context) {
        this(context, null);
    }

    public OpeningLooperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpeningLooperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_opening_looper_view, this, true);
        initView();
        initScrollEvent();
    }

    private void initScrollEvent() {
        onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePointIndicator(position);
                String mainTitle = mTitleBindListener.getTitle(position);
                String subTitle = mTitleBindListener.getSubTitle(position);
                //only the 1st view page should display 'TryItNow' Button
                if (position == 0) {
                    mButtonTry.setVisibility(VISIBLE);
                } else {
                    mButtonTry.setVisibility(INVISIBLE);
                }
                //dynamically adjust heights of 2 kind of titles inorder to fit UI's design
                if (mainTitle != null)
                    mMainTitle.setText(mainTitle);
                LayoutParams mainTitleLayoutParams = ((LayoutParams) mMainTitle.getLayoutParams());
                LayoutParams subTitleLayoutParams = ((LayoutParams) mSubtitle.getLayoutParams());
                if (subTitle != null) {
                    mainTitleLayoutParams.weight = 0.4f;
                    subTitleLayoutParams.weight = 0.6f;
                    mMainTitle.setLayoutParams(mainTitleLayoutParams);
                    mSubtitle.setLayoutParams(subTitleLayoutParams);
                    mSubtitle.setText(subTitle);
                } else {
                    mainTitleLayoutParams.weight = 0.6f;
                    subTitleLayoutParams.weight = 0.4f;
                    mMainTitle.setLayoutParams(mainTitleLayoutParams);
                    mSubtitle.setLayoutParams(subTitleLayoutParams);
                    mSubtitle.setText("");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private void initView() {
        mViewPager = findViewById(R.id.intro_pager);
        mMainTitle = findViewById(R.id.intro_mainTitle);
        mSubtitle = findViewById(R.id.intro_subtitle);
        mButtonTry = findViewById(R.id.btn_try);
        mButtonTry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //point Container, points need to be created dynamically based on the number of views in view pager
        mPointContainer = findViewById(R.id.intro_point_container);

    }

    /**
     * developer can customize the behavior of the try it Button
     */
    public Button getButtonView() {
        return mButtonTry;
    }

    /**
     * user can define title outside the class
     */
    public interface TitleBindListener {
        String getTitle(int position);

        String getSubTitle(int position);
    }

    /**
     * update the bottom points when a page is selected
     */
    public void updatePointIndicator(int position) {
        mPointContainer.getChildAt(mOldSelectedPosition).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_n, null));
        mPointContainer.getChildAt(position).setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_s, null));
        mOldSelectedPosition = position;

    }

    /**
     * create the corresponding number of point indicators that below videos
     */
    public void initPointIndicator(InnerPageAdapter innerPageAdapter) {
        for (int i = 0; i < innerPageAdapter.getCount(); i++) {
            View point = new View(getContext());
            LayoutParams layoutParams = new LayoutParams(SizeUtils.dip2px(getContext(), 6.7f), SizeUtils.dip2px(getContext(), 6.7f));
            layoutParams.setMargins(SizeUtils.dip2px(getContext(), 3.65f), 0, SizeUtils.dip2px(getContext(), 3.65f), 0);
            point.setLayoutParams(layoutParams);
            point.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.dot_n, null));
            mPointContainer.addView(point);
        }
    }

    public void setData(InnerPageAdapter innerPageAdapter, TitleBindListener listener) {
        mViewPager.setAdapter(innerPageAdapter);
        this.mTitleBindListener = listener;
        initPointIndicator(innerPageAdapter);
        //select 1st view of the viewPager so that the title and the point indicator can be selected.
        onPageChangeListener.onPageSelected(0);
    }

    public static abstract class InnerPageAdapter extends PagerAdapter {

        public int viewsCount;// number of view to be loaded

        protected InnerPageAdapter(int count) {
            viewsCount = count;
        }

        @Override
        public int getCount() {
            //viewpager的數量就是videos外面传进来的list大小
            return viewsCount;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //载入view，至于显示什么view,不用管，由外面给进来。只要对position进行一个转换
            View itemView = getItemView(container, position);
            container.addView(itemView);
            return itemView;
        }

        //至于要什么view，我不管，由外面给我。
        protected abstract View getItemView(ViewGroup container, int itemPosition);

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
