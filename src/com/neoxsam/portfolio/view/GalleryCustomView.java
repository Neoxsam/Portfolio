package com.neoxsam.portfolio.view;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.model.ModelImageData;

public class GalleryCustomView extends LinearLayout {

	private Context mContext;
	private int mNbPicture;

	public GalleryCustomView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public GalleryCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public GalleryCustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public void init() {
		setOrientation(LinearLayout.HORIZONTAL);
		mNbPicture = 0;
	}

	// ******************************************************
	// Create view
	// ******************************************************

	public void addPart(final ModelImageData elem) {
		int imageResource = getResources().getIdentifier(
				"drawable/" + elem.getmScreenshot(), null,
				mContext.getPackageName());
		if (imageResource == 0) {
			return;
		}
		Drawable image = getResources().getDrawable(imageResource);
		if (image == null) {
			return;
		}

		ImageView imageView;
		imageView = new ImageView(mContext);

		LinearLayout.LayoutParams layoutParams;

		if (image.getIntrinsicHeight() > image.getIntrinsicWidth()) {
			double coef = (double)Constants.GALLERY_SCREENSHOT_VERTICAL_HEIGHT / (double)image
					.getIntrinsicHeight();
			double height = coef * (double)image.getIntrinsicHeight();
			double width = coef * (double)image.getIntrinsicWidth();
			layoutParams = new LayoutParams((int)width, (int)height);
		} else {
			double coef = (double)Constants.GALLERY_SCREENSHOT_HORIZONTAL_HEIGHT / (double)image
					.getIntrinsicHeight();
			double height = coef * (double)image.getIntrinsicHeight();
			double width = coef * (double)image.getIntrinsicWidth();

			layoutParams = new LayoutParams(
					(int)width,
					(int)height);
		}
		layoutParams.gravity = Gravity.CENTER_VERTICAL;
		imageView.setLayoutParams(layoutParams);
		imageView.setImageDrawable(image);
		imageView.setPadding(10, 10, 10, 10);
		addView(imageView);
		mNbPicture++;
	}

	public boolean initView(List<ModelImageData> elemList) {

		for (ModelImageData modelImage : elemList) {
			if (modelImage != null) {
				addPart(modelImage);
			}
		}

		if (mNbPicture > 0) {
			return true;
		}
		return false;
	}
}
