package ilgulee.com.tictactoe.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import ilgulee.com.tictactoe.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class GameFragmentDirections {
  private GameFragmentDirections() {
  }

  @NonNull
  public static ActionGameFragmentToResultFragment actionGameFragmentToResultFragment() {
    return new ActionGameFragmentToResultFragment();
  }

  public static class ActionGameFragmentToResultFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionGameFragmentToResultFragment() {
    }

    @NonNull
    public ActionGameFragmentToResultFragment setWinner(@NonNull String winner) {
      if (winner == null) {
        throw new IllegalArgumentException("Argument \"winner\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("winner", winner);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("winner")) {
        String winner = (String) arguments.get("winner");
        __result.putString("winner", winner);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_gameFragment_to_resultFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getWinner() {
      return (String) arguments.get("winner");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionGameFragmentToResultFragment that = (ActionGameFragmentToResultFragment) object;
      if (arguments.containsKey("winner") != that.arguments.containsKey("winner")) {
        return false;
      }
      if (getWinner() != null ? !getWinner().equals(that.getWinner()) : that.getWinner() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getWinner() != null ? getWinner().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionGameFragmentToResultFragment(actionId=" + getActionId() + "){"
          + "winner=" + getWinner()
          + "}";
    }
  }
}
