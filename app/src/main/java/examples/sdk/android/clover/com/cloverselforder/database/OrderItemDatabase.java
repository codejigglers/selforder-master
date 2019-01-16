package examples.sdk.android.clover.com.cloverselforder.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {OrderItems.class}, version = 1, exportSchema = false)
public abstract class OrderItemDatabase extends RoomDatabase {

  public abstract DaoClass daoAccess() ;

}
