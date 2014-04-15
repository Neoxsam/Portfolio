package com.neoxsam.portfolio.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelProjectData;
import com.neoxsam.portfolio.utils.Utils;
import com.neoxsam.portfolio.view.GalleryCustomView;

public class ProjectDetailActivity extends Activity {

	private TextView mTextViewProjectTitle;
	private TextView mTextViewProjectDescription;
	private TextView mTextViewProjectDuration;
	private TextView mTextViewProjectCompany;
	private TextView mTextViewProjectRole;
	private ImageView mImageViewIcon;
	private ModelProjectData mProject;
	private GalleryCustomView mGallery;
	private LinearLayout mLinearLayoutGallery;
	private LinearLayout mLinearLayoutPlayStore;
	private LinearLayout mLinearLayoutGithub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_project_detail);

		mTextViewProjectTitle = (TextView) findViewById(R.id.text_view_project_title);
		mTextViewProjectDescription = (TextView) findViewById(R.id.text_view_project_description);
		mTextViewProjectDuration = (TextView) findViewById(R.id.text_view_project_duration);
		mTextViewProjectCompany = (TextView) findViewById(R.id.text_view_project_company);
		mTextViewProjectRole = (TextView) findViewById(R.id.text_view_project_role);
		mImageViewIcon = (ImageView) findViewById(R.id.image_view_project_icon_app);
		mGallery = (GalleryCustomView) findViewById(R.id.custom_view_gallery);
		mLinearLayoutGallery = (LinearLayout) findViewById(R.id.linear_layout_gallery);
		mLinearLayoutPlayStore = (LinearLayout) findViewById(R.id.linear_layout_play_store);
		mLinearLayoutGithub = (LinearLayout) findViewById(R.id.linear_layout_github);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle b = getIntent().getExtras();
		if (b != null) {
			mProject = (ModelProjectData) b
					.getParcelable(Constants.INTENT_KEY_PROJECT);
			manageData();
		}

		super.onCreate(savedInstanceState);
	}

	private void manageData() {
		if (Utils.isValidString(mProject.getmTitle())) {
			getActionBar().setTitle(mProject.getmTitle());
			mTextViewProjectTitle.setText(mProject.getmTitle());
		}

		if (Utils.isValidString(mProject.getmDescription())) {
			mTextViewProjectDescription.setText(Html.fromHtml(mProject.getmDescription()));
		} else {
			mTextViewProjectDescription.setVisibility(View.GONE);
		}

		if (Utils.isValidString(mProject.getmDuration())) {
			mTextViewProjectDuration.setText(mProject.getmDuration());
		} else {
			mTextViewProjectDuration.setVisibility(View.GONE);
		}

		if (Utils.isValidString(mProject.getmComany())) {
			mTextViewProjectCompany.setText(mProject.getmComany());
		} else {
			mTextViewProjectCompany.setVisibility(View.GONE);
		}

		if (Utils.isValidString(mProject.getmRole())) {
			mTextViewProjectRole.setText(mProject.getmRole());
		} else {
			mTextViewProjectRole.setVisibility(View.GONE);
		}

		if (!Utils.isValidString(mProject.getmMarketLink())) {
			mLinearLayoutPlayStore.setVisibility(View.GONE);
		}

		if (!Utils.isValidString(mProject.getmGithubLink())) {
			mLinearLayoutGithub.setVisibility(View.GONE);
		}

		if (Utils.isValidString(mProject.getmIcon())) {
			int imageResource = getResources().getIdentifier(
					"drawable/" + mProject.getmIcon(), null,
					this.getPackageName());
			Drawable image = getResources().getDrawable(imageResource);
			if (image != null) {
				mImageViewIcon.setImageDrawable(image);
			} else {
				mImageViewIcon.setVisibility(View.GONE);
			}
		} else {
			mImageViewIcon.setVisibility(View.GONE);
		}

		if (mProject.getmListScreenshot() != null
				&& mProject.getmListScreenshot().size() > 0) {
			if (!mGallery.initView(mProject.getmListScreenshot())) {
				mLinearLayoutGallery.setVisibility(View.GONE);
			}
		} else {
			mLinearLayoutGallery.setVisibility(View.GONE);
		}

	}

	// *******************************************************
	// onClickFunction
	// *******************************************************

	public void onClickPlayStore(View v) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://details?id="
							+ mProject.getmMarketLink())));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://play.google.com/store/apps/details?id="
							+ mProject.getmMarketLink())));
		}
	}

	public void onClickGithub(View v) {

	}

}
