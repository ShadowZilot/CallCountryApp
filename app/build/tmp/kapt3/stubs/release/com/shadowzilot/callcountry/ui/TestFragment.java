package com.shadowzilot.callcountry.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u0002J\u0018\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\bH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\"\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u0012\u0010(\u001a\u00020\u001d2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J&\u0010+\u001a\u0004\u0018\u00010\u00172\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u00100\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/shadowzilot/callcountry/ui/TestFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mCapital", "Landroid/widget/TextView;", "mCurrentCountry", "Lcom/shadowzilot/callcountry/database/Country;", "mCurrentQuestionKind", "", "mErrors", "", "mFManager", "Landroidx/fragment/app/FragmentManager;", "mFlag", "Landroid/widget/ImageView;", "mListErrors", "", "mName", "mNextButton", "Landroid/widget/ImageButton;", "mReference", "Lcom/shadowzilot/callcountry/database/LearningList;", "mRoot", "Landroid/view/View;", "mTestToolBar", "mVibrator", "Landroid/os/Vibrator;", "mWorldPartLabel", "changeLevel", "", "different", "initAnswerFragment", "id", "testKind", "initNextQuestion", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "resetScreen", "app_release"})
public final class TestFragment extends androidx.fragment.app.Fragment {
    private com.shadowzilot.callcountry.database.LearningList mReference;
    private int mErrors = 0;
    private final java.util.List<java.lang.Integer> mListErrors = null;
    private android.os.Vibrator mVibrator;
    private android.widget.ImageView mFlag;
    private android.widget.TextView mName;
    private android.widget.TextView mCapital;
    private android.widget.TextView mWorldPartLabel;
    private android.widget.TextView mTestToolBar;
    private com.shadowzilot.callcountry.database.Country mCurrentCountry;
    private java.lang.String mCurrentQuestionKind;
    private android.widget.ImageButton mNextButton;
    private android.view.View mRoot;
    private androidx.fragment.app.FragmentManager mFManager;
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void initNextQuestion() {
    }
    
    private final void initAnswerFragment(int id, java.lang.String testKind) {
    }
    
    private final void resetScreen() {
    }
    
    private final void changeLevel(int different) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public TestFragment() {
        super();
    }
}