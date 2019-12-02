package ilgulee.com.tictactoe.screen.result;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ilgulee.com.tictactoe.R;

public class ResultFragmentDirections {
  private ResultFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRestart() {
    return new ActionOnlyNavDirections(R.id.action_restart);
  }
}
