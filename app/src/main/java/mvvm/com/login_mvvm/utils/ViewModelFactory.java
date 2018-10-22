package mvvm.com.login_mvvm.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import mvvm.com.login_mvvm.view.login.Repository;
import mvvm.com.login_mvvm.viewmodel.LoginViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
  private Repository repository;

  @Inject
  public ViewModelFactory(Repository repository) {
    this.repository = repository;
  }


  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      return (T) new LoginViewModel(repository);
    }
    throw new IllegalArgumentException("Unknown class name");
  }
}
