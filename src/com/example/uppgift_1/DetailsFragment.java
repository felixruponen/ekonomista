package com.example.uppgift_1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class DetailsFragment extends Fragment implements OnDiagramBarClicked {

	ViewGroup root;
	
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
		
		
		
		
		return theView;
	}

	@Override
	public void onDiagramBarClicked(float x, float y, String text) {
			
		InformationBubbleView info = new InformationBubbleView(getActivity());
		info.setPosition(x, y, text);
		
		LayoutParams lp = new LayoutParams(root.getWidth(), root.getHeight());
		lp.height = LayoutParams.WRAP_CONTENT;
		lp.width = LayoutParams.WRAP_CONTENT;
		
		root.addView(info);

	}

}
