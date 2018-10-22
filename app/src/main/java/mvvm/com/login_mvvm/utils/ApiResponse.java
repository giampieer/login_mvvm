package mvvm.com.login_mvvm.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import static mvvm.com.login_mvvm.utils.Status.ERROR;
import static mvvm.com.login_mvvm.utils.Status.LOADING;
import static mvvm.com.login_mvvm.utils.Status.SUCCESS;
import com.google.gson.JsonElement;

public class ApiResponse {
  public final Status status;

  @Nullable
  public final JsonElement data;

  @Nullable
  public final Throwable error;

  private ApiResponse(Status status, @Nullable JsonElement data, @Nullable Throwable error) {
    this.status = status;
    this.data = data;
    this.error = error;
  }

  public static ApiResponse loading() {
    return new ApiResponse(LOADING, null, null);
  }

  public static ApiResponse success(@NonNull JsonElement data) {
    return new ApiResponse(SUCCESS, data, null);
  }

  public static ApiResponse error(@NonNull Throwable error) {
    return new ApiResponse(ERROR, null, error);
  }

}
