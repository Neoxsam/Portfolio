package com.neoxsam.portfolio.activity;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.neoxsam.portfolio.Constants;
import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.adapter.AdapterDrawer;
import com.neoxsam.portfolio.fragment.FragmentProfile;
import com.neoxsam.portfolio.fragment.FragmentProject;
import com.neoxsam.portfolio.fragment.FragmentResume;
import com.neoxsam.portfolio.model.ModelDrawerElem;
import com.neoxsam.portfolio.utils.Utils;

public class MainActivity extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mListViewDrawer;
	private ActionBarDrawerToggle mDrawerToggle;
	private long mCurTime;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private List<ModelDrawerElem> mDrawerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mListViewDrawer = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerList = Utils.createDrawerList(this);
		mListViewDrawer.setAdapter(new AdapterDrawer(this, mDrawerList));
		mCurTime = System.currentTimeMillis() / 1000;

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mListViewDrawer.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// open the first fragment
		openProfileFragment();
		setTitle(getString(R.string.drawer_profile));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		if (Utils.isValidString(Constants.MY_EMAIL)
				&& Utils.isValidString(Constants.MY_PHONE_NUMBER)) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main_activity_actions_phone_mail, menu);
			return super.onCreateOptionsMenu(menu);
		} else if (Utils.isValidString(Constants.MY_EMAIL)
				&& !Utils.isValidString(Constants.MY_PHONE_NUMBER)) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main_activity_actions_mail, menu);
			return super.onCreateOptionsMenu(menu);
		} else if (!Utils.isValidString(Constants.MY_EMAIL)
				&& Utils.isValidString(Constants.MY_PHONE_NUMBER)) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main_activity_actions_phone, menu);
			return super.onCreateOptionsMenu(menu);
		}
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	private void actionBarMail() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/html");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { Constants.MY_EMAIL });
		startActivity(Intent.createChooser(intent, "Send Email"));
	}

	private void actionBarCall() {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ Constants.MY_PHONE_NUMBER));
		startActivity(intent);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_mail:
			actionBarMail();
			return true;
		case R.id.action_call:
			actionBarCall();
			return true;
		default:
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (mDrawerList.get(position).getmFunction() == ModelDrawerElem.Function.RESUME) {
				openResumeFragment();
			} else if (mDrawerList.get(position).getmFunction() == ModelDrawerElem.Function.PROJECT) {
				openProjectFragment();
			} else if (mDrawerList.get(position).getmFunction() == ModelDrawerElem.Function.CONTACT) {
				openProfileFragment();
			}

			mListViewDrawer.setItemChecked(position, true);
			setTitle(mDrawerList.get(position).getmName());
			mDrawerLayout.closeDrawer(mListViewDrawer);

		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	// fragment function

	public void openResumeFragment() {
		Fragment fragment;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (getFragmentManager().findFragmentByTag(Constants.FRAGMENT_RESUME) != null) {
			fragment = getFragmentManager().findFragmentByTag(
					Constants.FRAGMENT_RESUME);
		} else {
			fragment = new FragmentResume();
		}
		fragment.setRetainInstance(true);
		ft.replace(R.id.content_frame, fragment, Constants.FRAGMENT_RESUME);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}

	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 1) {
			finish();
		} else {
			super.onBackPressed();
		}
	}

	public void openProjectFragment() {
		Fragment fragment;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (getFragmentManager().findFragmentByTag(Constants.FRAGMENT_PROJECT) != null) {
			fragment = getFragmentManager().findFragmentByTag(
					Constants.FRAGMENT_PROJECT);
		} else {
			fragment = new FragmentProject();
		}
		ft.replace(R.id.content_frame, fragment, Constants.FRAGMENT_PROJECT);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}

	public void openProfileFragment() {
		Fragment fragment;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (getFragmentManager().findFragmentByTag(Constants.FRAGMENT_CONTACT) != null) {
			fragment = getFragmentManager().findFragmentByTag(
					Constants.FRAGMENT_CONTACT);
		} else {
			fragment = new FragmentProfile();
		}
		ft.replace(R.id.content_frame, fragment, Constants.FRAGMENT_CONTACT);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.commit();
	}

}
