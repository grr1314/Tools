package com.fragment.admin.mpandroidcharts;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2016/11/23.
 */
public class pieChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart_activity);
        if (savedInstanceState == null) {
            //当savedInstanceState为空的时候，表示第一次进入activity
            getSupportFragmentManager().beginTransaction().add(R.id.pie_chart, new PieChartFragment()).commit();
        }
    }

    /**
     * 静态内部的Fragment
     * 疑问1：为什么修饰符必须是private
     * 疑问2：为什么必须是静态类
     */
    public static class PieChartFragment extends Fragment {

        private PieChart pieChartView;
        private PieData pieData;
        private Typeface typeface;//字体类型

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_piechart, container, false);
            initViews(view);
            return view;
        }

        private void initViews(View view) {
            pieChartView= (PieChart) view.findViewById(R.id.pieChartView);
            pieChartView.getDescription().setEnabled(false);

            typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Light.ttf");

            pieChartView.setCenterTextTypeface(typeface);
            pieChartView.setCenterText("Hello");
            pieChartView.setCenterTextSize(18f);
            pieChartView.setCenterTextTypeface(typeface);
            pieChartView.setDrawEntryLabels(true);//绘制里面的文字
            pieChartView.setDrawHoleEnabled(false);//是否画中间的圆形，false表示不画
            pieChartView.setDrawCenterText(false);//是否绘制中间的文字，false表示不绘制
            pieChartView.animateX(1000);//设置以X轴为中心的动画

            /**
             * 控制颜色标志的位置，默认是在图形底部
             */
            Legend l = pieChartView.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
            l.setXEntrySpace(7f);
            l.setYEntrySpace(0f);
            l.setYOffset(0f);

            initData();
            pieChartView.setData(pieData);//设置数据
        }

        private void initData() {
            int numValues = 7;
            ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();

            for(int i = 0; i < numValues; i++) {
                entries1.add(new PieEntry((float) ((Math.random() * 60) + 40), "Quarter " + (i+1)));
            }

            PieDataSet ds1 = new PieDataSet(entries1, "Quarterly");
            ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ds1.setSliceSpace(2f);
            ds1.setValueTextColor(Color.WHITE);
            ds1.setValueTextSize(12f);
            ds1.setSliceSpace(3f);
            ds1.setSelectionShift(5f);

            pieData = new PieData(ds1);
            pieData.setValueTypeface(typeface);
            pieData.setValueFormatter(new PercentFormatter());

        }
    }
}
