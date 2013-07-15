package com.srain.views;

import com.srain.actionsheet.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionSheet {

	private Dialog mDialog;
	private View mContentView;
	private LinearLayout mActionSheetContainer;
	private Context mContext;

	public ActionSheet(Context context) {

		mContentView = LayoutInflater.from(context).inflate(R.layout.actionsheet_container, null);
		mActionSheetContainer = (LinearLayout) mContentView.findViewById(R.id.ly_action_sheet_container);

		mContext = context;
	}

	public void addAction(String actionStr, OnClickListener handler) {
		final OnClickListener thatHandler = handler;
		ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.actionsheet_button, null);

		TextView actionView = (TextView) view.findViewById(R.id.btn_action_sheet_action);
		actionView.setText(actionStr);
		actionView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				thatHandler.onClick(v);
				mDialog.dismiss();
			}
		});
		mActionSheetContainer.addView(view);
	}

	public void show() {

		ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.actionsheet_cancel, null);
		TextView actionView = (TextView) view.findViewById(R.id.btn_action_sheet_cancel);
		actionView.setText(mContext.getString(R.string.action_sheet_cancel));
		actionView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
			}
		});

		mActionSheetContainer.addView(view);

		DisplayMetrics metrics = new DisplayMetrics();
		Rect rect = new Rect();

		Activity activity = ((Activity) mContext);
		WindowManager windowManager = activity.getWindowManager();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

		mDialog = new Dialog(mContext, R.style.ActionSheetStyle);
		mDialog.setContentView(mContentView);
		mDialog.getWindow().setGravity(Gravity.BOTTOM);
		mDialog.getWindow().getAttributes().width = metrics.widthPixels;
		mDialog.getWindow().getAttributes().height = metrics.heightPixels - rect.top;
		mDialog.show();
	}
}