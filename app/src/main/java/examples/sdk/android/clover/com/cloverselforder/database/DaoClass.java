package examples.sdk.android.clover.com.cloverselforder.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoClass {

  @Insert
  void insertItem(OrderItems orderItems);

  @Query("SELECT * FROM OrderItems WHERE itemName = :orderItem")
  OrderItems fetchOrder (String orderItem);

  @Delete
  void deleteMovie (OrderItems orderItem);

  @Update
  void updateMovie (OrderItems orderItem);

  @Query("SELECT * FROM OrderItems")
  List<OrderItems> fetchAllOrder ();

}
