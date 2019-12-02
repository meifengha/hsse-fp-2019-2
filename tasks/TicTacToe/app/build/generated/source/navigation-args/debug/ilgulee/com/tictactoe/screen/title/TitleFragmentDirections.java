package ilgulee.com.tictactoe.screen.title;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ilgulee.com.tictactoe.R;

public class TitleFragmentDirections {
  private TitleFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionTitleFragmentToGameFragment() {
    return new ActionOnlyNavDirections(R.id.action_titleFragment_to_gameFragment);
  }
}
