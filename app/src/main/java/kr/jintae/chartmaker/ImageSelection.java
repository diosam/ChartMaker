package kr.jintae.chartmaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ImageSelection extends Activity implements OnClickListener {

	private ImageButton[] btnImage;
	private int chartImage = 0;
//	Intent i = new Intent(ImageSelection.this, MakeChart.class);

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_selection);
//		i = getIntent();
		btnImage = new ImageButton[6];
		for (int i = 0; i < btnImage.length; i++) {
			String buttonID = "button" + (i + 1);
			int resID = getResources().getIdentifier(buttonID, "id",
					getApplicationContext().getPackageName());
			btnImage[i] = (ImageButton) findViewById(resID);
			btnImage[i].setOnClickListener(this);
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
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
		}
//		i.putExtra("Image", chartImage);
//		startActivity(i);
	}

}
