package com.encima.jymapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab.
        // We can also use ActionBar.Tab#select() to do this if we have a reference to the
        // Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                return true;
            case R.id.add_settings:
            	if(mViewPager.getCurrentItem() == 0) {
            	  Builder ad = new AlertDialog.Builder(this);
            	  ad.setTitle("How Many Exercises? ...");
            	  LinearLayout ex_dialog_layout = new LinearLayout(getApplicationContext());
            	  final EditText exNum = new EditText(getApplicationContext());
            	  exNum.setInputType(3);
            	  exNum.setTextColor(Color.BLACK);
            	  ex_dialog_layout.addView(exNum);
            	  ad.setView(ex_dialog_layout);
//            	  ad.setView(LayoutInflater.from(this).inflate(R.layout.scrollable_routine_dialog,null));
            	  ad.setPositiveButton("OK", 
            	    new android.content.DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent addRoutine = new Intent(getApplicationContext(), AddRoutine.class);
							addRoutine.putExtra("exNum", Integer.parseInt(exNum.getText().toString()));
							startActivity(addRoutine);
						}
            	    });

            	   ad.setOnCancelListener(new DialogInterface.OnCancelListener(){
	            	    public void onCancel(DialogInterface dialog) {
	            	     // OK, go back to Main menu   
	            	    }
            	    });
            	   ad.show();
            	}else
            		Toast.makeText(getApplicationContext(), "Add Diet Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
        	if(i == 0) {
        		Fragment fragment = new DummySectionFragment();
        		return fragment;
        	}else if(i ==1) {
        		Fragment diet = new DietSectionFragment();
        		return diet;
        	}
        	return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                case 1: return getString(R.string.title_section2).toUpperCase();
                case 2: return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
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
    
    public static class DietSectionFragment extends Fragment {
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
}
