package examples.sdk.android.clover.com.cloverselforder;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItemDatabase;
import examples.sdk.android.clover.com.cloverselforder.database.OrderItems;

import java.util.List;

public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.MyViewHolder> {

  public List<OrderItems> orderItems;
  private OrderItemDatabase orderItemDatabase;
  private Context context;
  private TextView textView;

  public CartDetailAdapter(Context context, List<OrderItems> orderItems, TextView textView) {
    this.context = context;
    this.textView = textView;
    this.orderItems = orderItems;
  }

  @NonNull
  @Override
  public CartDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    Context context = viewGroup.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View contactView = inflater.inflate(R.layout.cart_detail_item, viewGroup, false);
    MyViewHolder vh = new MyViewHolder(contactView);
    return vh;
  }

  @Override
  public void onBindViewHolder(@NonNull CartDetailAdapter.MyViewHolder myViewHolder, int i) {
    final int f = i;
    myViewHolder.item_cost.setText("$" + String.valueOf(orderItems.get(i).getCost()));
    myViewHolder.item_quantity.setText("X" + String.valueOf(orderItems.get(i).getQuantity()));
    myViewHolder.item_name.setText(orderItems.get(i).getItemName());
    myViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        orderItemDatabase = Room.databaseBuilder(context, OrderItemDatabase.class, "items_db").fallbackToDestructiveMigration().build();
        final OrderItems v = orderItems.get(f);

        new Thread(new Runnable() {
          @Override
          public void run() {
            orderItemDatabase.daoAccess().deleteMovie(v);
          }
        }).start();

        orderItems.remove(f);
        notifyItemRemoved(f);
        notifyItemRangeChanged(f, getItemCount());

        int sum = 0;
        for(OrderItems l : orderItems) {
          sum += l.getCost();
        }

        textView.setText("$"+String.valueOf(sum));

      }

    });
  }

  @Override
  public int getItemCount() {
    return orderItems.size();
  }


  public static class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView item_name;
    public TextView item_quantity;
    public TextView item_cost;
    public Button deleteButton;

    public MyViewHolder(View v) {
      super(v);
      item_name = v.findViewById(R.id.item_name);
      item_quantity = v.findViewById(R.id.item_quantity);
      item_cost = v.findViewById(R.id.item_cost);
      deleteButton = v.findViewById(R.id.deleteButton);
    }
  }
}
