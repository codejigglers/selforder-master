package examples.sdk.android.clover.com.cloverselforder;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItemDatabase;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItems;

import java.util.ArrayList;
import java.util.List;

public class CartDetail extends AppCompatActivity {


  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private OrderItemDatabase orderItemDatabase;
  TextView totalPrice;

  public AlertDialog.Builder builder1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart_detail);
    Toolbar toolbar = findViewById(R.id.my_toolbar);
    toolbar.setTitle("CART");
    totalPrice = findViewById(R.id.totalCartValue);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    LinearLayout linearLayout = findViewById(R.id.order);


    mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    orderItemDatabase = Room.databaseBuilder(getApplicationContext(), OrderItemDatabase.class, "items_db").fallbackToDestructiveMigration().build();
    loadRecyclerView();
    builder1 = new AlertDialog.Builder(this);


    linearLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(mRecyclerView.getAdapter().getItemCount()>0) {
          deleteAllItems();
          builder1.setTitle("Order Placed");
          builder1.setMessage("Congrats!!! Your Order Has Been Placed.");
          builder1.setCancelable(false);
          builder1.setPositiveButton(
              "Ok",
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  dialog.cancel();
                  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  startActivity(intent);
                  finish();
                }
              });

          AlertDialog alert11 = builder1.create();
          alert11.show();
        }
      }
    });
  }

  public void deleteAllItems() {


      new AsyncTask<Void, Void, String>() {
        @Override
        protected String doInBackground(Void... params) {
          orderItemDatabase.daoAccess().nukeTable();
          return null;
        }

        @Override
        protected void onPostExecute(String s) {
          List<OrderItems> orderItems = new ArrayList<>();
          CartDetailAdapter cartDetailAdapter = new CartDetailAdapter(getApplicationContext(), orderItems, totalPrice);
          mRecyclerView.setAdapter(cartDetailAdapter);
        }
      }.execute();
    }


  public void loadRecyclerView() {
    new AsyncTask<Void, Void, List<OrderItems>>() {
      @Override
      protected List<OrderItems> doInBackground(Void... params) {
        return orderItemDatabase.daoAccess().fetchAllOrder();
      }

      @Override
      protected void onPostExecute(List<OrderItems> items) {
        CartDetailAdapter cartDetailAdapter = new CartDetailAdapter(getApplicationContext(), items, totalPrice);
        mRecyclerView.setAdapter(cartDetailAdapter);
        int sum = 0;
        for(OrderItems l : items) {
          sum += l.getCost();
        }
        totalPrice.setText("$"+String.valueOf(sum));
      }
    }.execute();
  }





}
