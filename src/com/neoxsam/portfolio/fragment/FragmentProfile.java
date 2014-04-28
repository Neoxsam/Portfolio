package com.neoxsam.portfolio.fragment;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelProfileData;
import com.neoxsam.portfolio.utils.Utils;
import com.neoxsam.portfolio.view.ProfileInformationCustomView;

public class FragmentProfile extends Fragment {

	private ImageButton mImageButtonMail;
	private ImageButton mImageButtonCall;
	private ImageView mImageViewProfile;
	private TextView mTextViewGoal;
	private TextView mTextViewMotivation;
	private LinearLayout mLayoutGoal;
	private LinearLayout mLayoutMotivation;
	private ModelProfileData mProfileData;
	private ProfileInformationCustomView mInformationView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		String str = Utils.getStringFromAsset(getActivity(),
				Utils.getProfileAssetName(getActivity()));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mProfileData = mapper.readValue(str, ModelProfileData.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_profile, container, false);

		mImageButtonMail = (ImageButton) v.findViewById(R.id.image_button_mail);
		mImageButtonCall = (ImageButton) v.findViewById(R.id.image_button_call);
		mInformationView = (ProfileInformationCustomView) v
				.findViewById(R.id.custom_view_profile_information);
		mImageViewProfile = (ImageView) v.findViewById(R.id.imgae_view_profile);
		mTextViewGoal = (TextView) v.findViewById(R.id.text_view_goal);
		mTextViewMotivation = (TextView) v.findViewById(R.id.text_view_motivation);
		mLayoutGoal = (LinearLayout) v.findViewById(R.id.layout_goal);
		mLayoutMotivation = (LinearLayout) v.findViewById(R.id.layout_motivation);
		
		if (mProfileData != null) {
			if (mProfileData.getmListInformation() != null) {
				mInformationView.initView(mProfileData.getmListInformation());
			}
			
			if (Utils.isValidString(mProfileData.getmMotivation())) {
				mTextViewMotivation.setText(mProfileData.getmMotivation());
			} else {
				mLayoutMotivation.setVisibility(View.GONE);
			}

			if (Utils.isValidString(mProfileData.getmResearch())) {
				mTextViewGoal.setText(mProfileData.getmResearch());
			} else {
				mLayoutGoal.setVisibility(View.GONE);
			}

			if (Utils.isValidString(mProfileData.getmProfilePicture())) {
				int imageResource = getResources().getIdentifier(
						"drawable/" + mProfileData.getmProfilePicture(), null,
						getActivity().getPackageName());
				if (imageResource == 0) {
					mImageViewProfile.setVisibility(View.GONE);					
				} else {
					Drawable image = getResources().getDrawable(imageResource);					
					mImageViewProfile.setImageDrawable(image);
				}

			} else {
				mImageViewProfile.setVisibility(View.GONE);
			}
			createListener();
		}
		return v;
	}

	private void createListener() {
		if (!Utils.isValidString(Constants.MY_PHONE_NUMBER)) {
			mImageButtonCall.setVisibility(View.GONE);
		}
		if (!Utils.isValidString(Constants.MY_EMAIL)) {
			mImageButtonMail.setVisibility(View.GONE);
		}

		mImageButtonCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + Constants.MY_PHONE_NUMBER));
					startActivity(intent);
			}
		});

		mImageButtonMail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { Constants.MY_EMAIL });
				startActivity(Intent.createChooser(intent, "Send Email"));
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
