package kr.jintae.chartmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint.Align;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.artfulbits.aiCharts.ChartView;
import com.artfulbits.aiCharts.Base.ChartPoint;
import com.artfulbits.aiCharts.Base.ChartSeries;
import com.artfulbits.aiCharts.Base.ChartTitle;
import com.artfulbits.aiCharts.Enums.Alignment;
import com.artfulbits.aiCharts.Types.ChartTypes;

public class MakeChart extends Activity {
	/** Called when the activity is first created. */

	// 화면 캡쳐 부분
	LinearLayout view1;
	Bitmap bm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_main);
		view1 = (LinearLayout) findViewById(R.id.screen);
		Intent intent = getIntent();
		chartTitle = intent.getExtras().getString("chartTitle");
		chartImage = intent.getExtras().getInt("Image");
		title = intent.getExtras().getStringArray("Title");
		value = intent.getExtras().getDoubleArray("Value");
		type = intent.getExtras().getInt("Type");

		createChart();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sub1, menu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return super.onCreateOptionsMenu(menu);
	}

	private void showSubScreen(Bitmap bm) {
		try {
			File path = new File("sdcard/capture/");
			if (!path.isDirectory()) {
				path.mkdirs();
			}
			Calendar cal = Calendar.getInstance();
			String str = cal.getTime().toString();

			FileOutputStream out = new FileOutputStream("sdcard/capture/chart"
					+ str + ".jpg");

			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			File hFile = new File("sdcard/capture/chart" + str + ".jpg");
			SingleMediaScanner mScanner = new SingleMediaScanner(
					getApplicationContext(), hFile);
			Toast.makeText(getApplicationContext(),
					"sdcard/capture 폴더에 캡쳐된 차트를 저장하였습니다.", Toast.LENGTH_SHORT)
					.show();
			// getApplicationContext().sendBroadcast(new
			// Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
			// Uri.parse("file://sdcard/capture/chart"+str+".jpg")));

		} catch (FileNotFoundException e) {
			Log.d("FileNotFoundException:", e.getMessage());
		}
	}

	private boolean valueShow = false;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_default:
			chartImage = 0;
			series.setBackDrawable(null);
			createChart();
			break;
		case R.id.action_showNumber:
			if (series.getShowLabel()) {
				item.setTitle(R.string.action_show);
				series.setShowLabel(false);
			} else {
				item.setTitle(R.string.action_hide);
				series.setShowLabel(true);

			}
			break;
		case R.id.action_changeImage:
			if (type == 1 || type == 3) {
				mMainDialog = new MainDialog();
				mMainDialog.show(getFragmentManager(), "MYTAG");
			} else {
				Toast.makeText(getApplicationContext(),
						"바 차트와 컬럼 차트 이외의 차트는 이미지 변경을 지원하지 않습니다)",
						Toast.LENGTH_SHORT).show();
			}
			// Intent it = new Intent(MakeChart.this, ImageSelection.class);
			// it = getIntent();
			// startActivity(it);
			break;
		case R.id.action_captureImage:
			View v2 = view1.getChildAt(0);
			v2.setDrawingCacheEnabled(true);
			bm = v2.getDrawingCache();
			showSubScreen(bm);

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private String chartTitle;
	private int chartImage;
	private String[] title;
	private double[] value;
	private int type;

	MainDialog mMainDialog;
	Toast mToast;

	public static class MainDialog extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder mBuilder = new AlertDialog.Builder(
					getActivity());
			LayoutInflater mLayoutInflater = getActivity().getLayoutInflater();
			mBuilder.setView(mLayoutInflater.inflate(R.layout.image_selection,
					null));
			mBuilder.setTitle("차트에 표시할 이미지 선택");
			// mBuilder.setMessage("Dialog Messageeeeeee");
			return mBuilder.create();
		}

		@Override
		public void onStop() {
			super.onStop();
		}

	}

	public void ONCLICK_DIALOG(View v) {
		switch (v.getId()) {
		case R.id.button1:
			chartImage = R.drawable.icon_pen1;
			break;
		case R.id.button2:
			chartImage = R.drawable.icon_pen2;
			break;
		case R.id.button3:
			chartImage = R.drawable.icon_arrow;
			break;
		case R.id.button4:
			chartImage = R.drawable.icon_people;
			break;
		case R.id.button5:
			chartImage = R.drawable.icon_plane;
			break;
		case R.id.button6:
			chartImage = R.drawable.icon_house;
			break;
		case R.id.button7:
			chartImage = R.drawable.icon_brush;
			break;
		case R.id.button8:
			chartImage = R.drawable.icon_cup;
			break;
		case R.id.button9:
			chartImage = R.drawable.icon_book;
			break;
		case R.id.button10:
			chartImage = R.drawable.icon_money;
			break;
		case R.id.button11:
			chartImage = R.drawable.icon_phone;
			break;
		case R.id.button12:
			chartImage = R.drawable.icon_energy;
			break;
		case R.id.button13:
			chartImage = R.drawable.icon_apple;
			break;
		case R.id.button14:
			chartImage = R.drawable.icon_halloween;
			break;
		case R.id.button15:
			chartImage = R.drawable.icon_envelope;
			break;
		case R.id.button16:
			chartImage = R.drawable.icon_truck;
			break;
		case R.id.button17:
			chartImage = R.drawable.icon_watch;
			break;
		case R.id.button18:
			chartImage = R.drawable.icon_camera;
			break;
		}
		mMainDialog.dismiss();
		createChart();
	}

	public void makeToast(String msg) {
		mToast = Toast.makeText(getApplicationContext(), msg,
				Toast.LENGTH_SHORT);
		mToast.show();
	}

	private ChartView chartView;
	private ChartSeries series;

	private void createChart() {

		chartView = (ChartView) findViewById(R.id.chartView);
		series = chartView.getSeries().get("defaultSeries");
		series.setHLabelAlignment(Alignment.Center);
		series.setVLabelAlignment(Alignment.Near);
		/*
		 * 나중에 옵셥으로 줄 수 있는 것들...
		 * 
		 * - 그래프에 데이타값 표시. series.setShowLabel(true);
		 */
		// 글자크기 조절
		// series.setTextPaint(fontPaint);
		// 그래프 이미지 변경
		if (chartImage != 0) {
			Drawable dw = getResources().getDrawable(chartImage);
			series.setBackDrawable(dw);
		}

		// X축 Y축 라벨 텍스트
		series.getYAxis().getLabelPaint().setTextSize(50);
		series.getXAxis().getLabelPaint().setTextSize(50);
		// 차트 안에 들어가는 텍스트
		series.getTextPaint().setTextSize(50);
		series.getYAxis().setLabelAlignment(Alignment.Far);

		ChartTitle cTitle = chartView.getTitles().get(0);
		cTitle.getTextPaint().setTextSize(60);
		cTitle.setText(chartTitle);

		//
		// Paint fontPaint = new Paint();
		// fontPaint.setTextSize(1000);
		// fontPaint.setColor(Color.WHITE);

		switch (type) {
		case 0:
			// 영역 차트
			series.setType(ChartTypes.Area);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
				// point.setTextPaint(fontPaint);
				// System.out.println(point.getTextPaint().getTextSize());
				// point.setTextPaint(fontPaint);
			}

			break;
		case 1:
			// 바차트
			// 세로축에 항목 이름 나타내야 함......
			series.setType(ChartTypes.Bar);
			// Drawable dw = getResources().getDrawable(R.drawable.icon_pen1);
			// series.setBackDrawable(dw);
			series.setHLabelAlignment(Alignment.Near);
			series.setVLabelAlignment(Alignment.Center);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
			}
			break;
		case 2:
			// 퓨넬
			series.setType(ChartTypes.Funnel);
			series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setLabel(title[i]);
				// point.setLabelBackground(dw);
			}
			break;
		case 3:
			// 컬럼차트
			series.setType(ChartTypes.Column);
			// Drawable dw2 = getResources().getDrawable(R.drawable.icon_pen1);
			// series.setBackDrawable(dw2);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
			}
			break;
		case 4:
			// 라인차트
			series.setType(ChartTypes.Line);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
			}
			break;
		case 5:
			// 파이차트
			series.setType(ChartTypes.Pie);
			series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				// point.setAxisLabel(title[i]);
				point.setLabel(title[i]);

				// series.setAttribute(ChartPieType.LABEL_STYLE,
				// LabelStyle.OutsideColumn);
				// series.setAttribute(ChartPieType.OPTIMIZE_POINTS,
				// Boolean.FALSE);

			}
			break;
		case 6:
			// 포인트 차트
			series.setType(ChartTypes.Point);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
			}
			break;

		case 7:
			// 피라미드차트
			series.setType(ChartTypes.Pyramid);
			series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setLabel(title[i]);
			}
			break;
		case 8:
			// 스플라인차트
			series.setType(ChartTypes.Spline);
			// series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
				// point.setLabel(title[i]);
			}
		case 9:
			// 스플라인영역차트
			series.setType(ChartTypes.SplineArea);
			// series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setAxisLabel(title[i]);
				// point.setLabel(title[i]);
			}
			break;

		case 10:
			// 도우넛차트
			series.setType(ChartTypes.Doughnut);
			series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setLabel(title[i]);
			}
			break;
		case 11:
			// 도우넛차트
			series.setType(ChartTypes.Rose);
			// series.setShowLabel(true);
			for (int i = 0; i < title.length; i++) {
				ChartPoint point = series.getPoints().addXY(i, value[i]);
				point.setLabel(title[i]);
			}
			break;

		}

	}
}
