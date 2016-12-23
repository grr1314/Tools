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

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

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
        private ColumnChartView columnChartView;
        private ColumnChartData columnChartData;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_columnar, container, false);
            initViews(view);
            return view;
        }

        private void initViews(View view) {
            columnChartView = (ColumnChartView) view.findViewById(R.id.columnChartView);
            initData();
        }

        private void initData() {
            int numSubcolumns = 4;
            int numColumns = 4;
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;
            for (int i = 0; i < numColumns; ++i) {

                values = new ArrayList<SubcolumnValue>();
                for (int j = 0; j < numSubcolumns; ++j) {
                    int sign = getSign();
                    //同样第一个参数表示取值，第二个参数表示颜色
                    values.add(new SubcolumnValue((float) Math.random() * 50f * sign + 5 * sign, ChartUtils.pickColor
                            ()));
                }

                Column column = new Column(values);
                column.setHasLabels(true);
                column.setHasLabelsOnlyForSelected(false);
                columns.add(column);
            }
            columnChartData = new ColumnChartData(columns);
            columnChartView.setColumnChartData(columnChartData);

        }

        /**
         * 控制正负关系
         * @return
         */
        private int getSign() {
            int[] sign = new int[]{-1, 1};
            return sign[Math.round((float) Math.random())];
        }
    }
}
