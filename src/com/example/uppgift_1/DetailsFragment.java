package com.example.uppgift_1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class DetailsFragment extends Fragment implements OnDiagramBarClicked {

	
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
		
		
		
		
		return theView;
	}

	@Override
	public void onDiagramBarClicked(float x, float y, String text) {
			
		InformationBubbleView info = new InformationBubbleView(getActivity());
		info.setPosition(x, y, text);
	}

}
