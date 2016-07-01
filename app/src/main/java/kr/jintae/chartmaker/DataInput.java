package kr.jintae.chartmaker;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DataInput extends Fragment implements OnClickListener {
	public static TextView tv;
	private LinearLayout topLL;
	private Button btnAddRow, btnMakeChart;
	private int orderNum = 1;
	private int tvColumnTitleNum = 1000;
	private int etColumnValueNum = 2000;
	private int btnIdNum = 3000;
	private ScrollView scrollView;
	public static int selectedChartType;
	private EditText etTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.data_input, container, false);

		selectedChartType = 0;

		etTitle = (EditText) view.findViewById(R.id.etTitle);
		scrollView = (ScrollView) view.findViewById(R.id.scrollView);
		tv = (TextView) view.findViewById(R.id.textView1);
		btnMakeChart = (Button) view.findViewById(R.id.btnMakeChart);
		btnMakeChart.setOnClickListener(this);
		btnAddRow = (Button) view.findViewById(R.id.btnAddRow);
		btnAddRow.setOnClickListener(this);

		topLL = (LinearLayout) view.findViewById(R.id.dataRow);

		// TODO Auto-generated method stub
		return view;
	}

	private void addDataRow() {

		LinearLayout rowWhole = new LinearLayout(getActivity());
		rowWhole.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		rowWhole.setOrientation(LinearLayout.VERTICAL);

		LinearLayout rowL = new LinearLayout(getActivity());
		rowL.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		rowL.setOrientation(LinearLayout.HORIZONTAL);
		rowL.setPadding(10, 10, 10, 10);

		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(0,
				LayoutParams.WRAP_CONTENT);

		TextView tvOrder = new TextView(getActivity());

		tvOrder.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tvOrder.setGravity(Gravity.CENTER);
		tvOrder.setText("" + orderNum);
		tvOrder.setTextAppearance(getActivity(), R.style.textStyle);
		tvOrder.setPadding(0, 0, 15, 0);

		TextView tvColumnTitle = new TextView(getActivity());
		lParams.weight = 1;
		tvColumnTitle.setLayoutParams(lParams);
		tvColumnTitle.setGravity(Gravity.CENTER);
		tvColumnTitle.setText("항목(X축)");
		tvColumnTitle.setTextAppearance(getActivity(), R.style.textStyle);
		tvColumnTitle.setPadding(0, 0, 15, 0);

		EditText etColumnTitle = new EditText(getActivity());
		lParams.weight = 1;
		etColumnTitle.setLayoutParams(lParams);
		etColumnTitle.setGravity(Gravity.CENTER);
		etColumnTitle.setEms(5);
		etColumnTitle.setSingleLine();
		etColumnTitle.setImeOptions(EditorInfo.IME_ACTION_DONE);

		TextView tvColumnValue = new TextView(getActivity());
		lParams.weight = 0.8f;
		tvColumnValue.setLayoutParams(lParams);
		tvColumnValue.setGravity(Gravity.CENTER);
		tvColumnValue.setText("값(Y축)");
		tvColumnValue.setTextAppearance(getActivity(), R.style.textStyle);
		tvColumnValue.setPadding(0, 0, 15, 0);

		EditText etColumnValue = new EditText(getActivity());
		lParams.weight = 0.7f;
		etColumnValue.setLayoutParams(lParams);
		etColumnValue.setGravity(Gravity.CENTER);
		etColumnValue.setInputType(InputType.TYPE_CLASS_NUMBER
				| InputType.TYPE_NUMBER_FLAG_DECIMAL);
		etColumnValue.setEms(3);
		etColumnValue.setSingleLine();
		etColumnValue.setImeOptions(EditorInfo.IME_ACTION_DONE);

		Button btnDelete = new Button(getActivity());
		lParams.weight = 0.8f;
		btnDelete.setLayoutParams(lParams);
		btnDelete.setGravity(Gravity.CENTER);
		btnDelete.setId(btnIdNum);
		btnDelete.setText("삭제");
		btnDelete.setTextSize(12);
		btnDelete.setOnClickListener(this);
		btnIdNum++;

		ImageView splitLine = new ImageView(getActivity());
		splitLine.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 1));
		splitLine.setBackgroundColor(Color.parseColor("#FF0000"));
		//

		rowL.addView(tvOrder);
		rowL.addView(tvColumnTitle);
		rowL.addView(etColumnTitle);
		rowL.addView(tvColumnValue);
		rowL.addView(etColumnValue);
		rowL.addView(btnDelete);

		rowWhole.addView(rowL);
		rowWhole.addView(splitLine);

		topLL.addView(rowWhole);
		scrollView.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				scrollView.fullScroll(ScrollView.FOCUS_DOWN);
			}
		}, 200);

		orderNum++;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// Bundle bundle = this.getArguments();
		// if (bundle != null) {
		// String str = bundle.getString("Title");
		// tv = (TextView)getView().findViewById(R.id.textView1);
		// tv.setText(str);
		// }

	}

	public static void updateView(int position) {
		selectedChartType = position;
		switch (position) {
		case -1:
			tv.setText("차트 종류 선택 안함");
			break;
		case 0:
			tv.setText("영역 차트");
			break;
		case 1:
			tv.setText("바 차트");
			break;
		case 2:
			tv.setText("퓨넬 차트");
			break;
		case 3:
			tv.setText("컬럼 차트");
			break;
		case 4:
			tv.setText("라인 차트");
			break;
		case 5:
			tv.setText("파이 차트");
			break;
		case 6:
			tv.setText("포인트 차트");
			break;
		case 7:
			tv.setText("피라미드 차트");
			break;
		case 8:
			tv.setText("스플라인 차트");
			break;
		case 9:
			tv.setText("스플라인영역 차트");
			break;
		case 10:
			tv.setText("도우넛 차트");
			break;
		case 11:
			tv.setText("장미 차트");
			break;
		}

	}

	private int rowCount;
	private String chartTitle;
	private String[] columnName;
	private double[] columnValue;
	private boolean dataCompleted = true;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnAddRow:
			addDataRow();
			break;
		case R.id.btnMakeChart:
			dataCompleted = true;
			rowCount = topLL.getChildCount();

			if (rowCount > 0) {

				chartTitle = etTitle.getText().toString();

				columnName = new String[rowCount];
				columnValue = new double[rowCount];
				for (int i = 0; i < rowCount; i++) {
					ViewGroup vg1 = (ViewGroup) topLL.getChildAt(i);
					ViewGroup vg2 = (ViewGroup) vg1.getChildAt(0);

					// 항목 이름 가져오기
					EditText etColumnName = (EditText) vg2.getChildAt(2);
					String ss = etColumnName.getText().toString();
					// 항목 값 가져오기
					EditText etColumnValue = (EditText) vg2.getChildAt(4);
					String vv = etColumnValue.getText().toString();

					if (ss.equals("") || vv.equals("")) {
						Toast.makeText(
								getActivity(),
								(i + 1) + "번째 줄에 입력 값이 없습니다. 삭제하거나 값을 입력해 주세요.",
								Toast.LENGTH_SHORT).show();
						dataCompleted = false;

					} else {

						columnName[i] = ss;
						double d = Double.parseDouble(vv);
						columnValue[i] = d;
					}
				}

			} else {
				Toast.makeText(getActivity(),
						"차트 생성에 필요한 데이타가 없습니다. 데이타를 입력해 주세요.",
						Toast.LENGTH_SHORT).show();

			}
			if (rowCount > 0 && dataCompleted) {
				Intent i = new Intent(getActivity(), MakeChart.class);
				i.putExtra("Type", selectedChartType);
				i.putExtra("Image", 0);
				i.putExtra("chartTitle", chartTitle);
				i.putExtra("Title", columnName);
				i.putExtra("Value", columnValue);
				startActivity(i);
			}
			break;
		// 삭제버튼
		default:
			LinearLayout ll = (LinearLayout) v.getParent().getParent();
			topLL.removeView(ll);
			scrollView.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					scrollView.fullScroll(ScrollView.FOCUS_DOWN);
				}
			}, 200);
			rowCount = topLL.getChildCount();
			orderNum--;
			if (rowCount > 0) {
				for (int i = 0; i < rowCount; i++) {
					ViewGroup vg1 = (ViewGroup) topLL.getChildAt(i);
					ViewGroup vg2 = (ViewGroup) vg1.getChildAt(0);
					TextView orderTv = (TextView) vg2.getChildAt(0);
					orderTv.setText(""+(i+1));
				}
			}
			break;

		}
	}

}
