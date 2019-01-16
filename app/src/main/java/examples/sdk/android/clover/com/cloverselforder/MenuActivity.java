package examples.sdk.android.clover.com.cloverselforder;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import examples.sdk.android.clover.com.cloverselforder.database.OrderItemDatabase;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItems;
import examples.sdk.android.clover.com.cloverselforder.pojo.Items;
import examples.sdk.android.clover.com.cloverselforder.pojo.MainItems;
import examples.sdk.android.clover.com.cloverselforder.pojo.Order;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    StringBuilder anything = new StringBuilder();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public Items[] item;
    int len = 1;
    private OrderItemDatabase orderItemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        LinearLayout linearLayout = findViewById(R.id.cartButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        selfOrderApi service = RetrofitInstance.getRetrofitInstance().create(selfOrderApi.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<Order> call = service.getUser();

        /*Log the URL called*/
        Log.i("URL Called", call.request().url() + "");


        orderItemDatabase = Room.databaseBuilder(getApplicationContext(), OrderItemDatabase.class, "items_db").fallbackToDestructiveMigration().build();


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anything.setLength(0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<OrderItems> orderItems = orderItemDatabase.daoAccess().fetchAllOrder();

                        for (int i=0; i<orderItems.size(); i++) {
                            anything.append(orderItems.get(i).getItemName().toString() + " ");
                            anything.append(String.valueOf(orderItems.get(i).getCost()) + " ");
                            anything.append(String.valueOf(orderItems.get(i).getQuantity()) + " ");
                        }

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), anything, Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }) .start();
            }
        });

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                item = response.body().getMerchant().getItems();
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.appbar).setVisibility(View.VISIBLE);
                findViewById(R.id.container).setVisibility(View.VISIBLE);
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);
                TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
                tabLayout.setupWithViewPager(mViewPager);
                addTabs();
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTabs() {
        len = item.length;
        mSectionsPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PlaceholderFragment p;
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    p = PlaceholderFragment.newInstance(position + 1, item);
                    p.setTab(position + 1);
                    return p;
                case 1:
                    p = PlaceholderFragment.newInstance(position + 1, item);
                    p.setTab(position + 1);
                    return p;
                case 2:
                    p = PlaceholderFragment.newInstance(position + 1, item);
                    p.setTab(position + 1);
                    return p;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return len;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tab 1";
                case 1:
                    return "Tab 2";
                default:
                    return null;
            }
        }
    }
}

