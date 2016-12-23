package com.fragment.admin.mpandroidcharts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2016/11/23.
 */
public class columnarChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.columnarchart_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.columnar, new columnarChartFragment()).commit();
        }
    }

    public static class columnarChartFragment extends Fragment {

        private BarChart columnChartView;
        private BarData barData;
        private Typeface tf;

        public columnarChartFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_columnar, container, false);
            tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");
            initViews(view);
            return view;
        }

        private void initViews(View view) {
            columnChartView = (BarChart) view.findViewById(R.id.columnChartView);
            columnChartView.setDrawGridBackground(false);
            columnChartView.getDescription().setEnabled(true);
            initData(1, 100, 6);

            IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(columnChartView);
            XAxis xAxis = columnChartView.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f); // only intervals of 1 day
            xAxis.setLabelCount(7);
            xAxis.setValueFormatter(xAxisFormatter);
            /**
             * 控制颜色标记的位置
             */
            Legend l = columnChartView.getLegend();
            l.setTypeface(tf);

            YAxis leftAxis = columnChartView.getAxisLeft();
            leftAxis.setTypeface(tf);
            leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

            columnChartView.getAxisRight().setEnabled(false);
            columnChartView.animateY(1500);
            columnChartView.setData(barData);
        }

        private void initData(int dataSets, float range, int count) {
            ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

            for (int i = 0; i < dataSets; i++) {
                ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
                for (int j = 0; j < count; j++) {
                    entries.add(new BarEntry(j, (float) (Math.random() * range) + range / 4,"LABEL"+i));
                }
                BarDataSet ds = new BarDataSet(entries, getLabel(i));
                ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
                sets.add(ds);
            }
            barData = new BarData(sets);
            barData.setValueTypeface(tf);
        }

        /**
         * 控制正负关系
         *
         * @return
         */
        private int getSign() {
            int[] sign = new int[]{-1, 1};
            return sign[Math.round((float) Math.random())];
        }
    }
    private static String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };
//    private String[] mXVals = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

    private static String getLabel(int i) {
        return mLabels[i];
    }
}
