package com.shadowzilot.callcountry;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\'\b\u0016\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nB\u0005\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/shadowzilot/callcountry/CountryAdapterView;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/shadowzilot/callcountry/CountryHolder;", "Landroid/widget/Filterable;", "countrys", "Ljava/util/ArrayList;", "Lcom/shadowzilot/callcountry/database/Country;", "Lkotlin/collections/ArrayList;", "itemClickListener", "Lcom/shadowzilot/callcountry/ui/OnItemClickListener;", "(Ljava/util/ArrayList;Lcom/shadowzilot/callcountry/ui/OnItemClickListener;)V", "()V", "exampleFilter", "Landroid/widget/Filter;", "mCountrys", "mCountrysFull", "mItemClickListener", "getFilter", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_debug"})
public final class CountryAdapterView extends androidx.recyclerview.widget.RecyclerView.Adapter<com.shadowzilot.callcountry.CountryHolder> implements android.widget.Filterable {
    private java.util.ArrayList<com.shadowzilot.callcountry.database.Country> mCountrys;
    private java.util.ArrayList<com.shadowzilot.callcountry.database.Country> mCountrysFull;
    private com.shadowzilot.callcountry.ui.OnItemClickListener mItemClickListener;
    private final android.widget.Filter exampleFilter = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.shadowzilot.callcountry.CountryHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.shadowzilot.callcountry.CountryHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    public CountryAdapterView() {
        super();
    }
    
    public CountryAdapterView(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.shadowzilot.callcountry.database.Country> countrys, @org.jetbrains.annotations.NotNull()
    com.shadowzilot.callcountry.ui.OnItemClickListener itemClickListener) {
        super();
    }
}