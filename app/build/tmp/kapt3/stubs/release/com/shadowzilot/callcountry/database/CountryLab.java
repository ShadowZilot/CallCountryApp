package com.shadowzilot.callcountry.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u001e\u001a\u00020\u001aJ\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010 \u001a\u00020\u0010J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J\u000e\u0010$\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010%\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\"H\u0002R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/shadowzilot/callcountry/database/CountryLab;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mContext", "mCountries", "", "Lcom/shadowzilot/callcountry/database/Country;", "getMCountries", "()Ljava/util/List;", "setMCountries", "(Ljava/util/List;)V", "mDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "changeCountryLevel", "", "id", "", "levelKind", "", "different", "checkLevelBounds", "value", "getAmountOfLearnedCountries", "getAverageLevel", "", "getCountryById", "getCountryLevel", "getCountryTestKind", "getLearnedPercent", "getLearningState", "getLogList", "getValueById", "Landroid/content/ContentValues;", "loadJsonString", "makeLearningState", "updateCountry", "Companion", "app_release"})
public final class CountryLab {
    private android.content.Context mContext;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.shadowzilot.callcountry.database.Country> mCountries;
    private android.database.sqlite.SQLiteDatabase mDatabase;
    private static com.shadowzilot.callcountry.database.CountryLab INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.shadowzilot.callcountry.database.CountryLab.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.shadowzilot.callcountry.database.Country> getMCountries() {
        return null;
    }
    
    public final void setMCountries(@org.jetbrains.annotations.NotNull()
    java.util.List<com.shadowzilot.callcountry.database.Country> p0) {
    }
    
    public final void getLogList() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.shadowzilot.callcountry.database.Country getCountryById(int id) {
        return null;
    }
    
    public final float getAverageLevel(int id) {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryTestKind(int id) {
        return null;
    }
    
    private final int getCountryLevel(int id, java.lang.String levelKind) {
        return 0;
    }
    
    public final void changeCountryLevel(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String levelKind, int different) {
    }
    
    public final int getLearningState(int id) {
        return 0;
    }
    
    public final void makeLearningState(int id) {
    }
    
    private final int checkLevelBounds(int value) {
        return 0;
    }
    
    private final void updateCountry(android.content.ContentValues value) {
    }
    
    public final float getLearnedPercent() {
        return 0.0F;
    }
    
    public final int getAmountOfLearnedCountries() {
        return 0;
    }
    
    private final android.content.ContentValues getValueById(int id) {
        return null;
    }
    
    private final java.lang.String loadJsonString() {
        return null;
    }
    
    private CountryLab(android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/shadowzilot/callcountry/database/CountryLab$Companion;", "", "()V", "INSTANCE", "Lcom/shadowzilot/callcountry/database/CountryLab;", "get", "context", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.shadowzilot.callcountry.database.CountryLab get(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}