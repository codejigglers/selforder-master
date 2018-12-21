package examples.sdk.android.clover.com.cloverselforder;

import examples.sdk.android.clover.com.cloverselforder.pojo.Order;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface selfOrderApi {
    //https://api.myjson.com/bins/vi74w
    @GET("bins/12o2bc")
    Call<Order> getUser();
}
