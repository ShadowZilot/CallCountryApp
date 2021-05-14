package com.shadowzilot.callcountry.databinding;
import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CountryListItemBindingImpl extends CountryListItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.capital_sign, 5);
        sViewsWithIds.put(R.id.earth_sign, 6);
    }
    // views
    @NonNull
    private final com.shadowzilot.callcountry.views.MaskedCardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CountryListItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private CountryListItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.TextView) bindings[4]
            );
        this.countryCapitalName.setTag(null);
        this.countryFlag.setTag(null);
        this.countryName.setTag(null);
        this.mboundView0 = (com.shadowzilot.callcountry.views.MaskedCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.worldPartLabel.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
            setViewModel((com.shadowzilot.callcountry.CountryViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.shadowzilot.callcountry.CountryViewModel ViewModel) {
        updateRegistration(0, ViewModel);
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModel((com.shadowzilot.callcountry.CountryViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModel(com.shadowzilot.callcountry.CountryViewModel ViewModel, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        java.lang.String viewModelGetName = null;
        java.lang.String viewModelGetCapitalName = null;
        java.lang.String viewModelGetWorldPart = null;
        android.graphics.drawable.Drawable viewModelGetDrawableFlag = null;
        int viewModelGetWorldPartColor = 0;
        com.shadowzilot.callcountry.CountryViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x3L) != 0) {



                if (viewModel != null) {
                    // read viewModel.getName()
                    viewModelGetName = viewModel.getName();
                    // read viewModel.getCapitalName()
                    viewModelGetCapitalName = viewModel.getCapitalName();
                    // read viewModel.getWorldPart()
                    viewModelGetWorldPart = viewModel.getWorldPart();
                    // read viewModel.getDrawableFlag()
                    viewModelGetDrawableFlag = viewModel.getDrawableFlag();
                    // read viewModel.getWorldPartColor()
                    viewModelGetWorldPartColor = viewModel.getWorldPartColor();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.countryCapitalName, viewModelGetCapitalName);
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.countryFlag, viewModelGetDrawableFlag);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.countryName, viewModelGetName);
            this.worldPartLabel.setTextColor(viewModelGetWorldPartColor);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.worldPartLabel, viewModelGetWorldPart);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}