package com.neoxsam.portfolio.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelDrawerElem;

public class AdapterDrawer extends BaseAdapter implements ListAdapter {

    //================================================================================
    // Properties
    //================================================================================

    private List<ModelDrawerElem> mDrawerList;
    private LayoutInflater mInflater;
    
    //================================================================================
    // Cycle life
    //================================================================================

    public AdapterDrawer(Context context, List<ModelDrawerElem> drawerList) {
        mDrawerList = drawerList;
        mInflater = LayoutInflater.from(context);
    }

    //================================================================================
    // ListView cell
    //================================================================================

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.drawer_list_item, parent, false);

            holder = new ViewHolder();
            holder.textViewElem = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewElem.setText(mDrawerList.get(position).getmName());
        
        return convertView;
    }

    //================================================================================
    // Nested class
    //================================================================================

    class ViewHolder {
        TextView textViewElem;
    }

	@Override
	public int getCount() {
		return mDrawerList.size();
	}


	@Override
	public Object getItem(int position) {
		return mDrawerList.get(position);
	}


	@Override
	public long getItemId(int position) {
		return 0;
	}
}