package com.fragment.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

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
        private PieChartView pieChartView;//饼图View
        private PieChartData pieChartData;//饼图Data

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_piechart, container, false);
            initViews(view);
            return view;
        }

        private void initViews(View view) {
            pieChartView = (PieChartView) view.findViewById(R.id.pieChartView);
            initData();
        }

        private void initData() {
            int numValues = 4;

            List<SliceValue> values = new ArrayList<>();
            for (int i = 0; i < numValues; ++i) {
                //第一个参数表示个数，第二个参数控制颜色
                SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
                sliceValue.setLabel("Label"+i);//设置Label
                sliceValue.setValue(10);
                sliceValue.setTarget(12);
                values.add(sliceValue);
            }
            //图形绘制的关键在于数据的设置
            pieChartData = new PieChartData(values);
            pieChartData.setHasLabels(true);//设置标签
            pieChartData.setHasLabelsOutside(false);//设置标签在外部
            pieChartData.setHasCenterCircle(false);//判断是否有中间的圆心
            pieChartData.setCenterText1("Hello");
            pieChartData.setCenterText2("Second Word");
            pieChartData.setSlicesSpacing(5);//设置中间白线的宽度
            //数据与视图关联
            pieChartView.setPieChartData(pieChartData);
        }
    }
}
