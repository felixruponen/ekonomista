package com.example.uppgift_1;

import java.util.ArrayList;
import java.util.Date;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class DetailsFragment extends Fragment implements OnDiagramBarClicked, DateSeekBarChangeListener {

	
	SeekBar seekStart, seekStop;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.details_fragment, container, false);
		
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
			
	}

	@Override
	public void onDateSeekBarChangeListener(Date date) {
		Toast t = Toast.makeText(getActivity(), date.toString(), Toast.LENGTH_SHORT);
		t.show();
	}

}
