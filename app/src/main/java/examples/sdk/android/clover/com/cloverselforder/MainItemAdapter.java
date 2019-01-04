package examples.sdk.android.clover.com.cloverselforder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import examples.sdk.android.clover.com.cloverselforder.pojo.MainItems;

public class MainItemAdapter extends RecyclerView.Adapter<MainItemAdapter.MyViewHolder> {
    private Context mContext;
    private MainItems[] mainItems;

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
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        ArrayList<MainItems> mainItem = new ArrayList<>(Arrays.asList(mainItems));
        Log.e("sam", mainItem.get(i).toString());
        viewHolder.item.setText(mainItem.get(i).getItem());
        viewHolder.price.setText("$".concat(mainItem.get(i).getPrice()));
        // loading album cover using Glide library
        ;
    }

    @Override
    public int getItemCount() {
        return mainItems.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView price;
        TextView description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
