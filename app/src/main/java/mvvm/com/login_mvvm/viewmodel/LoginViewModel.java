package mvvm.com.login_mvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import mvvm.com.login_mvvm.utils.ApiResponse;
import mvvm.com.login_mvvm.view.login.Repository;

public class LoginViewModel extends ViewModel {
  private Repository repository;
  private final CompositeDisposable disposables = new CompositeDisposable();
  private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();


  public LoginViewModel(Repository repository) {
    this.repository = repository;
  }

  public MutableLiveData<ApiResponse> loginResponse() {
    return responseLiveData;
  }

  /*
   * method to call normal login api with $(mobileNumber + password)
   * */
  public void hitLoginApi(String mobileNumber, String password) {

    disposables.add(repository.executeLogin(mobileNumber, password)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
      .subscribe(
        result -> responseLiveData.setValue(ApiResponse.success(result)),
        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
      ));
  }


  @Override
  protected void onCleared() {
    disposables.clear();
  }

}
