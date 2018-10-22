package mvvm.com.login_mvvm.view.login;

import com.google.gson.JsonElement;
import io.reactivex.Observable;

import mvvm.com.login_mvvm.utils.ApiCallInterface;

public class Repository {
  private ApiCallInterface apiCallInterface;

  public Repository(ApiCallInterface apiCallInterface) {
    this.apiCallInterface = apiCallInterface;
  }

  /*
   * method to call login api
   * */
  public Observable<JsonElement> executeLogin(String mobileNumber, String password) {
    return apiCallInterface.login("30",mobileNumber, password);
  }
}
