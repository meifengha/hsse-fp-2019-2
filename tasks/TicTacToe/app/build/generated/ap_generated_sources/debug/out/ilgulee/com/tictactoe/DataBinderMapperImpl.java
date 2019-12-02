package ilgulee.com.tictactoe;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import ilgulee.com.tictactoe.databinding.FragmentGameBindingImpl;
import ilgulee.com.tictactoe.databinding.FragmentGameBindingLandImpl;
import ilgulee.com.tictactoe.databinding.FragmentResultBindingImpl;
import ilgulee.com.tictactoe.databinding.FragmentTitleBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTGAME = 1;

  private static final int LAYOUT_FRAGMENTRESULT = 2;

  private static final int LAYOUT_FRAGMENTTITLE = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(ilgulee.com.tictactoe.R.layout.fragment_game, LAYOUT_FRAGMENTGAME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ilgulee.com.tictactoe.R.layout.fragment_result, LAYOUT_FRAGMENTRESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(ilgulee.com.tictactoe.R.layout.fragment_title, LAYOUT_FRAGMENTTITLE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTGAME: {
          if ("layout-land/fragment_game_0".equals(tag)) {
            return new FragmentGameBindingLandImpl(component, view);
          }
          if ("layout/fragment_game_0".equals(tag)) {
            return new FragmentGameBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_game is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRESULT: {
          if ("layout/fragment_result_0".equals(tag)) {
            return new FragmentResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_result is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTITLE: {
          if ("layout/fragment_title_0".equals(tag)) {
            return new FragmentTitleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_title is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout-land/fragment_game_0", ilgulee.com.tictactoe.R.layout.fragment_game);
      sKeys.put("layout/fragment_game_0", ilgulee.com.tictactoe.R.layout.fragment_game);
      sKeys.put("layout/fragment_result_0", ilgulee.com.tictactoe.R.layout.fragment_result);
      sKeys.put("layout/fragment_title_0", ilgulee.com.tictactoe.R.layout.fragment_title);
    }
  }
}
