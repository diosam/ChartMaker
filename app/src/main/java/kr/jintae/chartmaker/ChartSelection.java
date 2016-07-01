package kr.jintae.chartmaker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class ChartSelection extends Fragment implements OnClickListener {

	private ImageButton[] btnChart;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
	}

	private ChartSelectionListener csListener;

	public interface ChartSelectionListener {
		void OnChartSelected(int positon);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		csListener = (ChartSelectionListener)activity;
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.chart_selection, container, false);
		btnChart = new ImageButton[12];
		for (int i = 0; i < btnChart.length; i++) {
			String buttonID = "imageButton" + (i + 1);
			int resID = getResources().getIdentifier(buttonID, "id",
					getActivity().getPackageName());
			btnChart[i] = (ImageButton) view.findViewById(resID);
			btnChart[i].setOnClickListener(this);
		}
		btnChart[0].setSelected(true);
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int index = 0;
		for (int i = 0; i < btnChart.length; i++) {
			btnChart[i].setSelected(false);
			if (btnChart[i].getId() == v.getId()) {
				index = i;
			}
		}
		csListener.OnChartSelected(index);
		
		switch (index) {
		case 0:
			btnChart[0].setSelected(true);
			break;
		case 1:
			btnChart[1].setSelected(true);
			break;
		case 2:
			btnChart[2].setSelected(true);
			break;
		case 3:
			btnChart[3].setSelected(true);
			break;
		case 4:
			btnChart[4].setSelected(true);
			break;
		case 5:
			btnChart[5].setSelected(true);
			break;
		case 6:
			btnChart[6].setSelected(true);
			break;
		case 7:
			btnChart[7].setSelected(true);
			break;
		case 8:
			btnChart[8].setSelected(true);
			break;
		case 9:
			btnChart[9].setSelected(true);
			break;
		case 10:
			btnChart[10].setSelected(true);
			break;
		case 11:
			btnChart[11].setSelected(true);
			break;
		}
	}
}
