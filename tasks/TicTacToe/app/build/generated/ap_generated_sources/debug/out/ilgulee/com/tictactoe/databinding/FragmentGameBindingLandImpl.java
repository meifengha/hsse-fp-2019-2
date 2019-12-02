package ilgulee.com.tictactoe.databinding;
import ilgulee.com.tictactoe.R;
import ilgulee.com.tictactoe.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentGameBindingLandImpl extends FragmentGameBinding implements ilgulee.com.tictactoe.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.table_layout, 11);
        sViewsWithIds.put(R.id.row0, 12);
        sViewsWithIds.put(R.id.row1, 13);
        sViewsWithIds.put(R.id.row2, 14);
        sViewsWithIds.put(R.id.end_game_button, 15);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback8;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentGameBindingLandImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentGameBindingLandImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.TextView) bindings[10]
            , (android.widget.Button) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TableRow) bindings[12]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TableRow) bindings[13]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TableRow) bindings[14]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TableLayout) bindings[11]
            );
        this.currentTurn.setTag(null);
        this.gameLayout.setTag(null);
        this.row0Column0.setTag(null);
        this.row0Column1.setTag(null);
        this.row0Column2.setTag(null);
        this.row1Column0.setTag(null);
        this.row1Column1.setTag(null);
        this.row1Column2.setTag(null);
        this.row2Column0.setTag(null);
        this.row2Column1.setTag(null);
        this.row2Column2.setTag(null);
        setRootTag(root);
        // listeners
        mCallback6 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 6);
        mCallback2 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 2);
        mCallback9 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 9);
        mCallback5 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 5);
        mCallback1 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 1);
        mCallback8 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 8);
        mCallback4 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 4);
        mCallback7 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 7);
        mCallback3 = new ilgulee.com.tictactoe.generated.callback.OnClickListener(this, 3);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((ilgulee.com.tictactoe.screen.game.GameViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable ilgulee.com.tictactoe.screen.game.GameViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelTurn((androidx.lifecycle.LiveData<java.lang.Character>) object, fieldId);
            case 1 :
                return onChangeViewModelCells((androidx.lifecycle.LiveData<java.util.List<java.lang.Character>>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelTurn(androidx.lifecycle.LiveData<java.lang.Character> ViewModelTurn, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelCells(androidx.lifecycle.LiveData<java.util.List<java.lang.Character>> ViewModelCells, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String stringValueOfViewModelCells2 = null;
        java.lang.Character viewModelCells5 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells0 = '\u0000';
        java.lang.Character viewModelCells2 = null;
        java.lang.String stringValueOfViewModelCells6 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells3 = '\u0000';
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells4 = '\u0000';
        java.lang.Character viewModelCells4 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells1 = '\u0000';
        java.lang.Character viewModelCells8 = null;
        java.lang.String stringValueOfViewModelCells8 = null;
        androidx.lifecycle.LiveData<java.lang.Character> viewModelTurn = null;
        java.lang.Character viewModelCells1 = null;
        java.lang.String stringValueOfViewModelCells5 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells7 = '\u0000';
        java.lang.String stringValueOfViewModelCells4 = null;
        java.util.List<java.lang.Character> viewModelCellsGetValue = null;
        java.lang.Character viewModelCells7 = null;
        java.lang.String stringValueOfViewModelCells7 = null;
        java.lang.Character viewModelTurnGetValue = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells5 = '\u0000';
        java.lang.Character viewModelCells0 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells8 = '\u0000';
        java.lang.String stringValueOfViewModelCells1 = null;
        java.lang.String stringValueOfViewModelCells3 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells6 = '\u0000';
        java.lang.Character viewModelCells6 = null;
        char androidxDatabindingViewDataBindingSafeUnboxViewModelCells2 = '\u0000';
        java.lang.Character viewModelCells3 = null;
        androidx.lifecycle.LiveData<java.util.List<java.lang.Character>> viewModelCells = null;
        java.lang.String stringValueOfViewModelCells0 = null;
        java.lang.String currentTurnAndroidStringCurrentTurnFormatViewModelTurn = null;
        ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.turn
                        viewModelTurn = viewModel.getTurn();
                    }
                    updateLiveDataRegistration(0, viewModelTurn);


                    if (viewModelTurn != null) {
                        // read viewModel.turn.getValue()
                        viewModelTurnGetValue = viewModelTurn.getValue();
                    }


                    // read @android:string/current_turn_format
                    currentTurnAndroidStringCurrentTurnFormatViewModelTurn = currentTurn.getResources().getString(R.string.current_turn_format, viewModelTurnGetValue);
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.cells
                        viewModelCells = viewModel.getCells();
                    }
                    updateLiveDataRegistration(1, viewModelCells);


                    if (viewModelCells != null) {
                        // read viewModel.cells.getValue()
                        viewModelCellsGetValue = viewModelCells.getValue();
                    }


                    if (viewModelCellsGetValue != null) {
                        // read viewModel.cells.getValue()[5]
                        viewModelCells5 = getFromList(viewModelCellsGetValue, 5);
                        // read viewModel.cells.getValue()[2]
                        viewModelCells2 = getFromList(viewModelCellsGetValue, 2);
                        // read viewModel.cells.getValue()[4]
                        viewModelCells4 = getFromList(viewModelCellsGetValue, 4);
                        // read viewModel.cells.getValue()[8]
                        viewModelCells8 = getFromList(viewModelCellsGetValue, 8);
                        // read viewModel.cells.getValue()[1]
                        viewModelCells1 = getFromList(viewModelCellsGetValue, 1);
                        // read viewModel.cells.getValue()[7]
                        viewModelCells7 = getFromList(viewModelCellsGetValue, 7);
                        // read viewModel.cells.getValue()[0]
                        viewModelCells0 = getFromList(viewModelCellsGetValue, 0);
                        // read viewModel.cells.getValue()[6]
                        viewModelCells6 = getFromList(viewModelCellsGetValue, 6);
                        // read viewModel.cells.getValue()[3]
                        viewModelCells3 = getFromList(viewModelCellsGetValue, 3);
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[5])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells5 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells5);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[2])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells2 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells2);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[4])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells4 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells4);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[8])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells8 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells8);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[1])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells1 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells1);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[7])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells7 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells7);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[0])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells0 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells0);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[6])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells6 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells6);
                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[3])
                    androidxDatabindingViewDataBindingSafeUnboxViewModelCells3 = androidx.databinding.ViewDataBinding.safeUnbox(viewModelCells3);


                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[5]))
                    stringValueOfViewModelCells5 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells5);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[2]))
                    stringValueOfViewModelCells2 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells2);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[4]))
                    stringValueOfViewModelCells4 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells4);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[8]))
                    stringValueOfViewModelCells8 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells8);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[1]))
                    stringValueOfViewModelCells1 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells1);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[7]))
                    stringValueOfViewModelCells7 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells7);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[0]))
                    stringValueOfViewModelCells0 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells0);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[6]))
                    stringValueOfViewModelCells6 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells6);
                    // read String.valueOf(androidx.databinding.ViewDataBinding.safeUnbox(viewModel.cells.getValue()[3]))
                    stringValueOfViewModelCells3 = java.lang.String.valueOf(androidxDatabindingViewDataBindingSafeUnboxViewModelCells3);
            }
        }
        // batch finished
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.currentTurn, currentTurnAndroidStringCurrentTurnFormatViewModelTurn);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.row0Column0.setOnClickListener(mCallback1);
            this.row0Column1.setOnClickListener(mCallback2);
            this.row0Column2.setOnClickListener(mCallback3);
            this.row1Column0.setOnClickListener(mCallback4);
            this.row1Column1.setOnClickListener(mCallback5);
            this.row1Column2.setOnClickListener(mCallback6);
            this.row2Column0.setOnClickListener(mCallback7);
            this.row2Column1.setOnClickListener(mCallback8);
            this.row2Column2.setOnClickListener(mCallback9);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row0Column0, stringValueOfViewModelCells0);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row0Column1, stringValueOfViewModelCells1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row0Column2, stringValueOfViewModelCells2);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row1Column0, stringValueOfViewModelCells3);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row1Column1, stringValueOfViewModelCells4);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row1Column2, stringValueOfViewModelCells5);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row2Column0, stringValueOfViewModelCells6);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row2Column1, stringValueOfViewModelCells7);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.row2Column2, stringValueOfViewModelCells8);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 6: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(5);
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(1);
                }
                break;
            }
            case 9: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(8);
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(4);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(0);
                }
                break;
            }
            case 8: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(7);
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(3);
                }
                break;
            }
            case 7: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(6);
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // viewModel
                ilgulee.com.tictactoe.screen.game.GameViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.fillCell(2);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.turn
        flag 1 (0x2L): viewModel.cells
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}