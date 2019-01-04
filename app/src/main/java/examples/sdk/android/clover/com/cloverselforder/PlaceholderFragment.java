package examples.sdk.android.clover.com.cloverselforder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;

import examples.sdk.android.clover.com.cloverselforder.pojo.Items;
import examples.sdk.android.clover.com.cloverselforder.pojo.MainItems;

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    static Items[] items;
    int tabNum;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, Items[] item) {
        try {
            items = item.clone();
        } catch (Exception e) {

        }
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void setTab(int n) {
        tabNum = n;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

//        TextView textView = rootView.findViewById(R.id.section_label);
        try {
            Items item = (Items) Array.get(items, tabNum - 1);
            MainItems[] mainItems = item.getMainItems();
            MainItemAdapter mainItemAdapter = new MainItemAdapter(getContext(), mainItems);
            RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mainItemAdapter);
//      MainItems mainItems1 = (MainItems) Array.get(mainItems, 0);
//      textView.setText(mainItems1.getDescription());
        } catch (Exception e) {

        }

        //textView.setText();
        return rootView;
    }
}