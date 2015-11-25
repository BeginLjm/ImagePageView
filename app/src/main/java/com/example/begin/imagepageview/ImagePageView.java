package com.example.begin.imagepageview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.loopj.android.image.SmartImageView;

import java.util.LinkedList;

/**
 * Created by Begin on 15/11/24.
 */
public class ImagePageView extends RelativeLayout {
    private Context context;
    private LinkedList<Object> images;
    private int num;
    private LinkedList<View> views;
    private LinkedList<ImageView> imageViews;
    public static final int BITMAP = 1;
    public static final int RESID = 2;
    public static final int URL = 3;


    public ImagePageView(Context context) {
        this(context, null);
    }

    public ImagePageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImagePageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setImages(LinkedList<Object> list, int type) {
        this.num = list.size();
        this.images = list;
        views = new LinkedList<>();
        imageViews = new LinkedList<>();
        addViewPage(type);
    }

    private void addViewPage(int type) {
        ViewPager viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager.setBackgroundColor(0x99123321);
        LinearLayout layout = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(CENTER_HORIZONTAL);
        layoutParams.bottomMargin = 30;
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
        params.leftMargin = 20;
        for (int i = 0; i < num; i++) {
            if (num != 1) {
                View view = new View(context);
                view.setLayoutParams(params);
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.circle);
                } else {
                    view.setBackgroundResource(R.drawable.circletm);
                }
                views.add(view);
                layout.addView(view);
            }
            if (type==URL){
                SmartImageView smartImageView = new SmartImageView(context);
                smartImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                smartImageView.setImageUrl((String)images.get(i));
                imageViews.add(smartImageView);
            }else {
                ImageView imageView = new ImageView(context);
                switch (type) {
                    case BITMAP:
                        imageView.setBackground(new BitmapDrawable(null, (Bitmap) images.get(i)));
                        break;
                    case RESID:
                        imageView.setBackgroundResource((int) images.get(i));
                        break;
                }
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageViews.add(imageView);
            }
        }
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < views.size(); i++) {
                    views.get(i).setBackgroundResource(R.drawable.circletm);
                }
                views.get(position).setBackgroundResource(R.drawable.circle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        this.addView(viewPager);
        this.addView(layout);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (images == null)
                return 0;
            else
                return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position), 0);
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            imageViews.get(position).setDrawingCacheEnabled(true);
            if (imageViews.get(position).getDrawingCache() != null)
                imageViews.get(position).getDrawingCache().recycle();
            imageViews.get(position).setDrawingCacheEnabled(false);
            container.removeView(imageViews.get(position));
        }
    }


}
