package com.shadowzilot.callcountry;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0007J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\b\u0010\u000b\u001a\u00020\bH\u0007J\b\u0010\f\u001a\u00020\bH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/shadowzilot/callcountry/CountryViewModel;", "Landroidx/databinding/BaseObservable;", "mContext", "Landroid/content/Context;", "mCountry", "Lcom/shadowzilot/callcountry/database/Country;", "(Landroid/content/Context;Lcom/shadowzilot/callcountry/database/Country;)V", "getCapitalName", "", "getDrawableFlag", "Landroid/graphics/drawable/Drawable;", "getName", "getWorldPart", "getWorldPartColor", "", "setCountry", "", "country", "app_debug"})
public final class CountryViewModel extends androidx.databinding.BaseObservable {
    private final android.content.Context mContext = null;
    private com.shadowzilot.callcountry.database.Country mCountry;
    
    public final void setCountry(@org.jetbrains.annotations.NotNull()
    com.shadowzilot.callcountry.database.Country country) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.databinding.Bindable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.databinding.Bindable()
    public final java.lang.String getCapitalName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.databinding.Bindable()
    public final android.graphics.drawable.Drawable getDrawableFlag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.databinding.Bindable()
    public final java.lang.String getWorldPart() {
        return null;
    }
    
    @androidx.databinding.Bindable()
    public final int getWorldPartColor() {
        return 0;
    }
    
    public CountryViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.Nullable()
    com.shadowzilot.callcountry.database.Country mCountry) {
        super();
    }
}