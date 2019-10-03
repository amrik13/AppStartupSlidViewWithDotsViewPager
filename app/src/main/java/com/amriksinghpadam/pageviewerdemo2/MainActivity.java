package com.amriksinghpadam.pageviewerdemo2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    Button nextBtn,previousBtn;
    private TextView[] dot;
    MyAdapter adapter;
    LinearLayout dotLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPagerId);
        nextBtn = findViewById(R.id.nextBtnId);
        previousBtn = findViewById(R.id.previousBtnId);
        dotLayout = findViewById(R.id.dotLayout);
        adapter = new MyAdapter(this,nextBtn,previousBtn);
        viewPager.setAdapter(adapter);
        addDot();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                // call dot method
                if (position != 0 && position != (dot.length - 1)) {
                    previousBtn.setVisibility(View.VISIBLE);
                    nextBtn.setText("Next");
                    dot[position + 1].setTextColor(getResources().getColor(R.color.inactiveDotColor));
                    dot[position - 1].setTextColor(getResources().getColor(R.color.inactiveDotColor));
                }
                if (position == dot.length - 1) {
                    dot[position - 1].setTextColor(getResources().getColor(R.color.inactiveDotColor));
                    nextBtn.setText("Finish");
                }
                if (position == 0) {
                    dot[position + 1].setTextColor(getResources().getColor(R.color.inactiveDotColor));
                    previousBtn.setVisibility(View.INVISIBLE);
                }
//                for(int i=0;i<dot.length;i++){
//                    dot[i].setTextColor(getResources().getColor(R.color.inactiveDotColor));
//                }
                dot[position].setTextColor(getResources().getColor(R.color.activeDotColor));
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public void addDot(){
        dot = new TextView[adapter.getCount()];
        for(int i = 0; i<adapter.getCount();i++){
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#8226"));
            dot[i].setTextSize(30);
            dotLayout.addView(dot[i]);
            dot[i].setTextColor(getResources().getColor(R.color.inactiveDotColor));
        }
        dot[0].setTextColor(getResources().getColor(R.color.activeDotColor));
        if(viewPager.getCurrentItem()==0){
            previousBtn.setVisibility(View.INVISIBLE);
        }
        if(viewPager.getCurrentItem()== dot.length - 1){
            nextBtn.setVisibility(View.INVISIBLE);
        }
    }
}