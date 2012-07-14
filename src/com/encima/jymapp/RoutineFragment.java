package com.encima.jymapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class RoutineFragment extends Fragment {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Test", "hello");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Button add  = (Button) getView().findViewById(R.id.add);
		add.setText("Add Routine");
		ListView lv = (ListView) getView().findViewById(R.id.list);
		String[] listItems = {"Test"};
		lv.setAdapter(new ArrayAdapter<String>(getView().getContext(),  android.R.layout.simple_list_item_1, listItems ));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.routine_fragment, container, false);
		return view;
	}
}
