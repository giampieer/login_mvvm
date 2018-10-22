package mvvm.com.login_mvvm.utils;

import com.google.gson.JsonElement;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCallInterface {
  @FormUrlEncoded
  @POST(Urls.LOGIN)
  Observable<JsonElement> login(@Field("application") String application,@Field("username") String mobileNumber, @Field("password") String password);
}
