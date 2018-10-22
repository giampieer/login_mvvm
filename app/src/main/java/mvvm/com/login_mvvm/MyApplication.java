package mvvm.com.login_mvvm;

import android.app.Application;
import android.content.Context;

import mvvm.com.login_mvvm.data.AppComponent;
import mvvm.com.login_mvvm.data.AppModule;
import mvvm.com.login_mvvm.data.UtilsModule;
import mvvm.com.login_mvvm.data.DaggerAppComponent;

public class MyApplication extends Application {
  AppComponent appComponent;
  Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  protected void attachBaseContext(Context context) {
    super.attachBaseContext(context);
  }
}
