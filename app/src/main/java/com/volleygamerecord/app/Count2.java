package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.List;

/**
 * Created by A on 2014/4/1.
 */
public class Count2 extends Activity {
    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE,Color.YELLOW, Color.RED };
    private static double[] VALUES = new double[] { 10, 11, 12, 13 };
    private static String[] NAME_LIST = new String[] { "A", "B", "C", "D" };
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count2);
        Button btn_back_menu = (Button) findViewById(R.id.btn_back_menu);
        btn_back_menu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName =  DataCenter.getInstance().getStringValue("fbName");

                //從local data store拿自己隊伍的資料，並上傳至parse
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
                query.whereEqualTo("userName",userName);
                query.fromLocalDatastore();
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                        if (e == null) {
                            ParseObject.saveAllInBackground(parseObjects);
                        } else {
                            Log.e("parseReturn", e.toString());
                        }
                    }
                });
                //從local data store拿自己隊伍的資料，並上傳至parse
                ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("GameScore");
                query2.whereEqualTo("userName",userName);
                query2.fromLocalDatastore();
                query2.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                        if (e == null) {
                            ParseObject.saveAllInBackground(parseObjects);
                            Intent intent = new Intent();
                            intent.setClass(Count2.this, Start.class);
                            startActivity(intent);
                        } else {
                            Log.e("parseReturn", e.toString());
                        }
                    }
                });
            }
        });

        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.argb(100, 255, 255, 255));
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(15);
        mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[] { 20, 150, 15, 0 });
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(90);

        for (int i = 0; i < VALUES.length; i++) {
            mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
        }
        if (mChartView != null) {
            mChartView.repaint();
        }


        super.onResume();
        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            layout.setVisibility(View.GONE);
            mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);

            mChartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();

                    if (seriesSelection == null) {
                        Toast.makeText(Count2.this, "No chart element was clicked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Count2.this,"Chart element data point index "+ (seriesSelection.getPointIndex()+1) + " was clicked" + " point value="+ seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            mChartView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(Count2.this,"No chart element was long pressed", Toast.LENGTH_SHORT);
                        return false;
                    } else {
                        Toast.makeText(Count2.this,"Chart element data point index "+ seriesSelection.getPointIndex()+ " was long pressed",Toast.LENGTH_SHORT);
                        return true;
                    }
                }
            });
            layout.addView(mChartView, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT));
        }
        else {
            mChartView.repaint();
        }
    }
}