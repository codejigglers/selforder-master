package examples.sdk.android.clover.com.cloverselforder.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class OrderItems {

  public OrderItems() {

  }

  @NonNull
  @PrimaryKey
  private String itemName;

  @NonNull
  private int cost;

  @NonNull
  private int quantity;

  @NonNull
  public String getItemName() {
    return itemName;
  }

  @NonNull
  public int getCost() {
    return cost;
  }

  @NonNull
  public int getQuantity() {
    return quantity;
  }

  public void setItemName(@NonNull String itemName) {
    this.itemName = itemName;
  }

  public void setCost(@NonNull int cost) {
    this.cost = cost;
  }

  public void setQuantity(@NonNull int quantity) {
    this.quantity = quantity;
  }
}
