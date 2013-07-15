package com.srain.views;

import com.srain.actionsheet.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
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
		((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;

		mDialog = new Dialog(mContext, R.style.ActionSheetStyle);
		mDialog.setContentView(mContentView);
		mDialog.getWindow().setGravity(Gravity.BOTTOM);
		mDialog.getWindow().getAttributes().width = width;
		mDialog.getWindow().getAttributes().height = metrics.heightPixels - 50;
		mDialog.show();
	}
}