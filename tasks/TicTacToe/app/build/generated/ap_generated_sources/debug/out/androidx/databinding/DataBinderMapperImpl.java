package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new ilgulee.com.tictactoe.DataBinderMapperImpl());
  }
}
