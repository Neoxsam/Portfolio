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

import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelProjectData;
import com.neoxsam.portfolio.modelwrapper.ModelProjectWrapper;
import com.neoxsam.portfolio.utils.Utils;
import com.neoxsam.portfolio.view.ProjectCustomView;

public class FragmentProject extends Fragment {

	private List<ModelProjectData> mListProject;
	private ProjectCustomView mViewProject;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		String str = Utils.getStringFromAsset(getActivity(),
				Utils.getProjectAssetName(getActivity()));

		ObjectMapper mapper = new ObjectMapper();
		try {
			ModelProjectWrapper wrapper = mapper.readValue(str,
					ModelProjectWrapper.class);

			if (wrapper != null && wrapper.getmList() != null) {
				mListProject = wrapper.getmList();
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
		View v = inflater.inflate(R.layout.fragment_project, container, false);

		mViewProject = (ProjectCustomView) v.findViewById(R.id.custom_view_project);
		
		if (mListProject != null) {
			mViewProject.initView(mListProject);
		}
		
		return v;
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
