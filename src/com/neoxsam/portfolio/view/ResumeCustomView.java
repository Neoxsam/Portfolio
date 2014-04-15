package com.neoxsam.portfolio.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neoxsam.portfolio.R;
import com.neoxsam.portfolio.model.ModelResumeData;
import com.neoxsam.portfolio.model.ModelResumeDataMain;
import com.neoxsam.portfolio.utils.Utils;

public class ResumeCustomView extends LinearLayout {

	private Context mContext;
	private LayoutInflater inflater;
	
	public ResumeCustomView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ResumeCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ResumeCustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }	
	
    public void init() {
    	inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    	setOrientation(LinearLayout.VERTICAL);
    }
    
    //******************************************************
    // Create view
    //******************************************************

    public void addCell(ModelResumeData elem, LinearLayout LL) {
    	View view = inflater.inflate(R.layout.cell_data_resume, null);
    	view.setPadding(20, 0, 20, 10);
    	
    	TextView tmp;
    	tmp = (TextView) view.findViewById(R.id.text_view_title);
    	if (tmp != null && Utils.isValidString(elem.getmName())) {
    		tmp.setText(elem.getmName());
    	} else {
    		tmp.setVisibility(View.GONE);    		
    	}

    	tmp = (TextView) view.findViewById(R.id.text_view_location);
    	if (tmp != null && Utils.isValidString(elem.getmLocation())) {
    		tmp.setText(elem.getmLocation());
    	} else {
    		tmp.setVisibility(View.GONE);    		
    	}

    	tmp = (TextView) view.findViewById(R.id.text_view_date);
    	if (tmp != null && Utils.isValidString(elem.getmDate())) {
    		tmp.setText(elem.getmDate());
    	} else {
    		tmp.setVisibility(View.GONE);    		
    	}
    	
    	tmp = (TextView) view.findViewById(R.id.text_view_content);
    	if (tmp != null && Utils.isValidString(elem.getmContent())) {
    		tmp.setText(elem.getmContent());
    	} else {
    		tmp.setVisibility(View.GONE);    		
    	}
    	LL.addView(view);
    }

    public void addTitleTextView(String title, LinearLayout LL) {
    	TextView tv=new TextView(mContext);
        tv.setText(title);
        tv.setTextAppearance(mContext, R.style.text_part_title);
    	tv.setPadding(20, 10, 20, 10);
        LL.addView(tv);
    }

    public void addPart(ModelResumeDataMain elem) {
    	LinearLayout LL = new LinearLayout(mContext);
    	LayoutParams LLParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
    	
    	LLParams.setMargins(20, 10, 20, 10);

    	LL.setOrientation(LinearLayout.VERTICAL);
    	LL.setPadding(20, 20, 20, 20);
    	LL.setBackgroundResource(R.drawable.tile_background);
    	LL.setLayoutParams(LLParams);    

    	List<ModelResumeData> list = elem.getmContent();
    	if (elem.getmTitle() != null) {
        	addTitleTextView(elem.getmTitle(), LL);    		
    	}
    	for (ModelResumeData modelResumeData : list) {
			if (modelResumeData != null) {				
		    	addCell(modelResumeData, LL);
			}
		}

    	addView(LL);
    }
        
    public void initView(List<ModelResumeDataMain> elemList) {
    	
    	
    	for (ModelResumeDataMain modelResumeDataMain : elemList) {
			if (modelResumeDataMain != null) {
				addPart(modelResumeDataMain);
			}
		}
    }
}
