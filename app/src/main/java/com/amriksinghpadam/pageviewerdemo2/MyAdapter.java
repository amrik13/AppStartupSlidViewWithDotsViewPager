package com.amriksinghpadam.pageviewerdemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MyAdapter extends PagerAdapter {

    private String[] str = {"1","2","3","4","5"};
    private Button nextBtn;
    private Button previousBtn;
    private Context context;

    MyAdapter(Context context,Button nextBtn, Button previousBtn){
        this.nextBtn = nextBtn;
        this.previousBtn = previousBtn;
        this.context = context;
    }

    @Override
    public int getCount() { return str.length; }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_pager_item,container,false);
        bindView(view,position,container);
        return view;
    }

    public void bindView(View view, int position, final ViewGroup container){
        TextView textView = view.findViewById(R.id.itemTextId);
        textView.setText(str[position]);
        container.addView(view);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
