package com.neoxsam.portfolio.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelProfileInformation;
import com.neoxsam.portfolio.utils.Utils;

public class ProfileInformationCustomView extends LinearLayout {

	private Context mContext;
	private int mNbPicture;

	public ProfileInformationCustomView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public ProfileInformationCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public ProfileInformationCustomView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public void init() {
		setOrientation(LinearLayout.VERTICAL);
		mNbPicture = 0;
	}

	// ******************************************************
	// Create view
	// ******************************************************

	public void addPart(final ModelProfileInformation elem) {
		if (!Utils.isValidString(elem.getmInformation()))
			return;

		TextView textView;
		textView = new TextView(mContext);

		LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		textView.setText(elem.getmInformation());
		textView.setTextAppearance(mContext, R.style.text_part_name);
		textView.setLayoutParams(LLParams);
		textView.setPadding(10, 10, 10, 10);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		addView(textView);
		mNbPicture++;
	}

	public boolean initView(List<ModelProfileInformation> elemList) {

		for (ModelProfileInformation modelInfo : elemList) {
			if (modelInfo != null) {
				addPart(modelInfo);
			}
		}

		if (mNbPicture > 0) {
			return true;
		}
		return false;
	}
}
