package com.srain.actionsheet;

import com.srain.views.ActionSheet;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btnActionSheet).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showActionSheet(v);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showActionSheet(View v) {
		ActionSheet actionSheet = new ActionSheet(this);
		actionSheet.addAction("aaaa", new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("action_view", "aaaa");
			}
		});

		actionSheet.addAction("bbbb", new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("action_view", "bbbb");
			}
		});
		actionSheet.show();
	}
}
