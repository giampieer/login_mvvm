package mvvm.com.login_mvvm.utils;

import android.util.Log;
import android.util.Printer;

import com.google.gson.JsonElement;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiCallInterface {
  @FormUrlEncoded
  @POST("http://pichanga-env.3t9rtcf9rd.us-east-2.elasticbeanstalk.com/api/authenticate  ")

  Observable<JsonElement> login(@Field("application") String application, @Field("username") String mobileNumber, @Field("password") String password);
}
interface RestClient {
  @GET("LOGIN")
  Observable<JsonElement> getData();

}
