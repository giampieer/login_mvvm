package mvvm.com.login_mvvm.data;

import javax.inject.Singleton;

import dagger.Component;

import mvvm.com.login_mvvm.view.login.MainActivity;
@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
  void doInjection(MainActivity loginActivity);
}
