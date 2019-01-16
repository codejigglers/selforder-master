package examples.sdk.android.clover.com.cloverselforder;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import examples.sdk.android.clover.com.cloverselforder.database.OrderItemDatabase;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItems;
import examples.sdk.android.clover.com.cloverselforder.pojo.MainItems;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyViewHolder> {
    private Context mContext;
    private MainItems[] mainItems;

    private static final String DATABASE_NAME = "items_db";
    private OrderItemDatabase orderItemDatabase;


    public MainItemAdapter(Context mContext, MainItems[] mainItems) {
        this.mContext = mContext;
        this.mainItems = mainItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        ArrayList<MainItems> mainItem = new ArrayList<>(Arrays.asList(mainItems));
        Log.e("sam", mainItem.get(i).toString());
        viewHolder.item.setText(mainItem.get(i).getItem());
        viewHolder.price.setText("$".concat(mainItem.get(i).getPrice()));

        viewHolder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(viewHolder.quantity.getText().toString());
                q+=1;
                viewHolder.quantity.setText(String.valueOf(q));
            }
        });

        viewHolder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(viewHolder.quantity.getText().toString());
                if(q>0) {
                    q-=1;
                    viewHolder.quantity.setText(String.valueOf(q));
                }
            }
        });

        orderItemDatabase = Room.databaseBuilder(mContext, OrderItemDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();

        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OrderItems orderItems = orderItemDatabase.daoAccess().fetchOrder(viewHolder.item.getText().toString());

                        if(orderItems == null) {
                            OrderItems orderItems1 = new OrderItems();
                            orderItems1.setCost((Integer.parseInt(viewHolder.price.getText().toString().substring(1, viewHolder.price.getText().toString().length()))) * Integer.parseInt(viewHolder.quantity.getText().toString()));
                            orderItems1.setQuantity(Integer.parseInt(viewHolder.quantity.getText().toString()));
                            orderItems1.setItemName(viewHolder.item.getText().toString());
                            orderItemDatabase.daoAccess().insertItem(orderItems1);
                        }
                        else {
                            OrderItems orderItems1 = new OrderItems();
                            String name = orderItems.getItemName();
                            int cost = orderItems.getCost();
                            int quantity = orderItems.getQuantity();
                            orderItems1.setItemName(name);
                            orderItems1.setCost(cost + (Integer.parseInt(viewHolder.price.getText().toString().substring(1, viewHolder.price.getText().toString().length()))) * Integer.parseInt(viewHolder.quantity.getText().toString()));
                            orderItems1.setQuantity(quantity + Integer.parseInt(viewHolder.quantity.getText().toString()));
                            orderItemDatabase.daoAccess().updateMovie(orderItems1);
                        }

                    }
                }) .start();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainItems.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView price;
        TextView description;
        TextView quantity;
        Button increase;
        Button decrease;
        Button addToCart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
            price = (TextView) itemView.findViewById(R.id.price);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            increase = itemView.findViewById(R.id.add);
            decrease = itemView.findViewById(R.id.minus);
            addToCart = itemView.findViewById(R.id.addCart);
        }
    }
}
