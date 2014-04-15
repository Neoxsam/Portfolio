package com.neoxsam.portfolio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelDrawerElem;
import com.neoxsam.portfolio.model.ModelDrawerElem.Function;

public class Utils {

	public static List<ModelDrawerElem> createDrawerList(Context context) {
		List<ModelDrawerElem> drawerList = new ArrayList<ModelDrawerElem>();

		ModelDrawerElem tmp;
		tmp = new ModelDrawerElem(context.getString(R.string.drawer_profile),
				Function.CONTACT);
		drawerList.add(tmp);

		tmp = new ModelDrawerElem(context.getString(R.string.drawer_resume),
				Function.RESUME);
		drawerList.add(tmp);

		tmp = new ModelDrawerElem(context.getString(R.string.drawer_project),
				Function.PROJECT);
		drawerList.add(tmp);

		return drawerList;
	}

	public static String getStringFromAsset(Context ctx, String path) {
		StringBuilder buf = new StringBuilder();

		InputStream json;
		try {
			json = ctx.getAssets().open(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(json,
					"ISO-8859-1"));
			String str;
			while ((str = in.readLine()) != null) {
				buf.append(str);
			}
			in.close();
			return buf.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isValidString(String str) {
		if (str != null && !str.isEmpty() && str.length() > 0) {
			return true;
		}
		return false;
	}

	private static boolean isAssetFileExist(Context ctx, String assetFileName) {
		boolean test;
		try {
			test = Arrays.asList(ctx.getResources().getAssets().list(""))
					.contains(assetFileName);
		} catch (IOException e) {
			return false;
		}
		return test;
	}

	public static String getProfileAssetName(Context ctx) {
		String language = Locale.getDefault().getLanguage();
		if (language.equals(Constants.LANGUAGE_REF_FR)
				&& isAssetFileExist(ctx, Constants.ASSET_FILE_PROFILE_FR)) {
			return Constants.ASSET_FILE_PROFILE_FR;
		}
		return Constants.ASSET_FILE_PROFILE_DEFAULT;
	}

	public static String getProjectAssetName(Context ctx) {
		String language = Locale.getDefault().getLanguage();
		if (language.equals(Constants.LANGUAGE_REF_FR)
				&& isAssetFileExist(ctx, Constants.ASSET_FILE_PROJECT_FR)) {
			return Constants.ASSET_FILE_PROJECT_FR;
		}
		return Constants.ASSET_FILE_PROJECT_DEFAULT;
	}

	public static String getResumeAssetName(Context ctx) {
		String language = Locale.getDefault().getLanguage();
		if (language.equals(Constants.LANGUAGE_REF_FR)
				&& isAssetFileExist(ctx, Constants.ASSET_FILE_RESUME_FR)) {
			return Constants.ASSET_FILE_RESUME_FR;
		}
		return Constants.ASSET_FILE_RESUME_DEFAULT;
	}
}
