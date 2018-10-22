package mvvm.com.login_mvvm.view.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;
import com.google.gson.JsonElement;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvvm.com.login_mvvm.utils.ApiResponse;
import mvvm.com.login_mvvm.utils.Constant;
import mvvm.com.login_mvvm.utils.ViewModelFactory;
import mvvm.com.login_mvvm.viewmodel.LoginViewModel;
import mvvm.com.login_mvvm.MyApplication;
import mvvm.com.login_mvvm.R;

public class MainActivity extends AppCompatActivity {
  @Inject
  ViewModelFactory viewModelFactory;
  @BindView(R.id.phone_no)
  EditText phoneNo;
  @BindView(R.id.password)
  EditText password;
  TextView text1,text2,text3;
  LoginViewModel viewModel;
  ProgressDialog progressDialog;


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
    = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          text1.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          text2.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          text3.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(@Nullable  Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    progressDialog = Constant.getProgressDialog(this, "Please wait...");
    ButterKnife.bind(this);
    ((MyApplication) getApplication()).getAppComponent().doInjection(this);

   viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

    viewModel.loginResponse().observe(this, this::consumeResponse);

  }

  @OnClick(R.id.login)
  void onLoginClicked() {
    if (isValid()) {
      if (!Constant.checkInternetConnection(this)) {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
      } else {
        viewModel.hitLoginApi(phoneNo.getText().toString(), password.getText().toString());
      }

    }
  }

  /*
   * method to validate $(mobile number) and $(password)
   * */
  private boolean isValid() {

    if (phoneNo.getText().toString().trim().isEmpty()) {
      Toast.makeText(MainActivity.this, getResources().getString(R.string.enter_valid_mobile), Toast.LENGTH_SHORT).show();
      return false;
    } else if (password.getText().toString().trim().isEmpty()) {
      Toast.makeText(MainActivity.this, getResources().getString(R.string.enter_valid_password), Toast.LENGTH_SHORT).show();
      return false;
    }

    return true;
  }
  private void consumeResponse(ApiResponse apiResponse) {

    switch (apiResponse.status) {

      case LOADING:
        progressDialog.show();
        break;

      case SUCCESS:
        progressDialog.dismiss();
        renderSuccessResponse(apiResponse.data);
        break;

      case ERROR:
        progressDialog.dismiss();
        Toast.makeText(MainActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        break;

      default:
        break;
    }
  }

  /*
   * method to handle success response
   * */
  private void renderSuccessResponse(JsonElement response) {
    if (!response.isJsonNull()) {
      Log.d("response=", response.toString());
    } else {
      Toast.makeText(MainActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
    }
  }
}
