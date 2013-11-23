package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.Date;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.view.ViewGroup.LayoutParams;
=======
import android.widget.SeekBar;
import android.widget.Toast;
>>>>>>> f85a23afdec7fc185ab551a14363d3f8a5fe8046

public class DetailsFragment extends Fragment implements OnDiagramBarClicked, DateSeekBarChangeListener {

	ViewGroup root;
	
	SeekBar seekStart, seekStop;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.details_fragment, container, false);
		
		root = (ViewGroup) theView.findViewById(R.id.details_root);
		
		DBTools db = new DBTools(getActivity());
		
		double income = db.getTotalIncome();
		double expense = db.getTotalExpense();
		
		DiagramView diagram = (DiagramView) theView.findViewById(R.id.diagram_view);
		
		diagram.setDiagram(income, expense);
		diagram.setOnDiagramBarClickedListener(this);
		
		CustomSeekBar seekBar = (CustomSeekBar) theView.findViewById(R.id.details_seek_start);
		
		seekBar.setOnDateSeekBarChangeListener(this);
		
		ArrayList<Date> dates = new ArrayList<Date>();
		dates.add(new Date(2013, 11, 22));
		dates.add(new Date(2011, 12, 11));
		
		seekBar.setDates(dates);
		
		return theView;
	}

	@Override
	public void onDiagramBarClicked(float x, float y, String text) {
			
<<<<<<< HEAD
		InformationBubbleView info = new InformationBubbleView(getActivity());
		info.setPosition(x, y, text);
		
		LayoutParams lp = new LayoutParams(root.getWidth(), root.getHeight());
		lp.height = LayoutParams.WRAP_CONTENT;
		lp.width = LayoutParams.WRAP_CONTENT;
		
		root.addView(info);

=======
	}

	@Override
	public void onDateSeekBarChangeListener(Date date) {
		Toast t = Toast.makeText(getActivity(), date.toString(), Toast.LENGTH_SHORT);
		t.show();
>>>>>>> f85a23afdec7fc185ab551a14363d3f8a5fe8046
	}

}
