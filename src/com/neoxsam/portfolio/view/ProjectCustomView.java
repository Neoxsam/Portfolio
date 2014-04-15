package com.neoxsam.portfolio.view;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.activity.ProjectDetailActivity;
import com.neoxsam.portfolio.model.ModelProjectData;
import com.neoxsam.portfolio.utils.Utils;

public class ProjectCustomView extends LinearLayout {

	private Context mContext;
	private LayoutInflater inflater;

	public ProjectCustomView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public ProjectCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public ProjectCustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public void init() {
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setOrientation(LinearLayout.VERTICAL);
	}

	// ******************************************************
	// Create view
	// ******************************************************

	public void addPart(final ModelProjectData elem) {
		View view = inflater.inflate(R.layout.cell_data_project, null);
		LinearLayout layout = (LinearLayout) view;

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		layoutParams.setMargins(20, 10, 20, 10);
		layout.setLayoutParams(layoutParams);

		TextView tmpTV;
		tmpTV = (TextView) view.findViewById(R.id.text_view_title);
		if (tmpTV != null && Utils.isValidString(elem.getmTitle())) {
			tmpTV.setText(elem.getmTitle());
		} else {
			tmpTV.setVisibility(View.GONE);
		}

		tmpTV = (TextView) view.findViewById(R.id.text_view_description);
		if (tmpTV != null && Utils.isValidString(elem.getmDescription())) {
			tmpTV.setText(Html.fromHtml(elem.getmDescription()));
		} else {
			tmpTV.setVisibility(View.GONE);
		}

		tmpTV = (TextView) view.findViewById(R.id.text_view_more_information);
		if (tmpTV != null) {
			tmpTV.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, ProjectDetailActivity.class);
					intent.putExtra(Constants.INTENT_KEY_PROJECT, elem);
					mContext.startActivity(intent);
				}
			});
		}
		
		ImageView tmpIV;
		tmpIV = (ImageView) view.findViewById(R.id.image_view_app_icon);
		if (tmpIV != null && Utils.isValidString(elem.getmIcon())) {
			int imageResource = getResources().getIdentifier(
					"drawable/" + elem.getmIcon(), null,
					mContext.getPackageName());
			if (imageResource != 0) {
				Drawable image = getResources().getDrawable(imageResource);
				if (image != null) {
					tmpIV.setImageDrawable(image);
				} else {
					tmpIV.setVisibility(View.GONE);
				}				
			} else {
				tmpIV.setVisibility(View.GONE);				
			}
		} else {
			tmpIV.setVisibility(View.GONE);
		}

		addView(view);
	}

	public void initView(List<ModelProjectData> elemList) {

		for (ModelProjectData modelProject : elemList) {
			if (modelProject != null) {
				addPart(modelProject);
			}
		}
	}
}
