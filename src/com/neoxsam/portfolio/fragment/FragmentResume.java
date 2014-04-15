package com.neoxsam.portfolio.fragment;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelResumeDataMain;
import com.neoxsam.portfolio.modelwrapper.ModelResumeWrapper;
import com.neoxsam.portfolio.utils.Utils;
import com.neoxsam.portfolio.view.ResumeCustomView;

public class FragmentResume extends Fragment {

	private ResumeCustomView mViewFormation;
	private List<ModelResumeDataMain> mListFormation;
	private ScrollView mScrollView;

	private int mScrollYPosition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		mScrollYPosition = 0;
		String str = Utils.getStringFromAsset(getActivity(),
				Utils.getResumeAssetName(getActivity()));

		ObjectMapper mapper = new ObjectMapper();
		try {
			ModelResumeWrapper wrapper = mapper.readValue(str,
					ModelResumeWrapper.class);

			if (wrapper != null && wrapper.getmList() != null) {
				mListFormation = wrapper.getmList();
			}

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
		View v = inflater.inflate(R.layout.fragment_resume, container, false);

		mScrollView = (ScrollView) v.findViewById(R.id.scroll_view_main);
		mViewFormation = (ResumeCustomView) v
				.findViewById(R.id.custom_view_formation);

		mViewFormation.initView(mListFormation);

		if (mScrollView != null) {
			mScrollView.setScrollY(mScrollYPosition);
		}
		
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		if (mScrollView != null) {
			mScrollYPosition = mScrollView.getScrollY();
		}
		super.onPause();
	}

	

}
