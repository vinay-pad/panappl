package com.pan.panoramicview;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewStyle.GridStyle;
import com.jjoe64.graphview.LineGraphView;

public class PanGraphView extends Activity{
	String fw_name;
	ArrayList<Integer> x_val;
	ArrayList<String> y_val;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_info_page);
		fw_name = getIntent().getExtras().getString("fw_name");
		x_val = getIntent().getIntegerArrayListExtra("x_val");
		y_val = getIntent().getStringArrayListExtra("y_val");
		GraphViewData[] data = new GraphViewData[x_val.size()];
		
		for (int i = 0; i < x_val.size(); i++) { 
			Integer s_x_val = x_val.get(i);
			String s_y_val = y_val.get(i);
			data[i] = new GraphViewData(s_x_val.doubleValue(), Double.parseDouble(s_y_val)+1);
		}
		GraphViewSeriesStyle style = new GraphViewSeriesStyle(Color.rgb(240, 3, 3), 3);
		GraphViewSeries exampleSeries = new GraphViewSeries(fw_name, style, data);
		GraphView graphView = new LineGraphView(
		    this // context
		    , fw_name.toString() // heading
			);
		graphView.addSeries(exampleSeries);
		graphView.getGraphViewStyle().setGridStyle(GridStyle.HORIZONTAL);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
	}
}
