package com.shadowzilot.callcountry.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0000H\u0096\u0002J\b\u0010-\u001a\u0004\u0018\u00010.J\u0006\u0010/\u001a\u00020\u0003J\u000e\u00100\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u00101\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\b\u00102\u001a\u00020\u0005H\u0016R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\u001e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010 \u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\"\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010$\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0011\u0010&\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0016R\u0011\u0010(\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0016R\u000e\u0010*\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/shadowzilot/callcountry/database/Country;", "", "countryID_", "", "countryName_", "", "countryCapital_", "countryFlag_", "worldPartCode_", "population_", "square_", "language_", "currency_", "formOfGovernment_", "religion_", "context", "Landroid/content/Context;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "countryCapital", "getCountryCapital", "()Ljava/lang/String;", "countryFlag", "getCountryFlag", "countryID", "getCountryID", "()I", "countryName", "getCountryName", "currency", "getCurrency", "formOfGovernment", "getFormOfGovernment", "language", "getLanguage", "population", "getPopulation", "religion", "getReligion", "square", "getSquare", "worldPartCode", "compareTo", "other", "getDrawableFlag", "Landroid/graphics/drawable/Drawable;", "getWorldPartCode", "getWorldPartColor", "getWorldPartName", "toString", "app_release"})
public final class Country implements java.lang.Comparable<com.shadowzilot.callcountry.database.Country> {
    private final int countryID = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String countryName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String countryCapital = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String countryFlag = null;
    private final int worldPartCode = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String population = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String square = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String language = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currency = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String formOfGovernment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String religion = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public final int getCountryID() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryCapital() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryFlag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPopulation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSquare() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormOfGovernment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReligion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public final int getWorldPartCode() {
        return 0;
    }
    
    public final int getWorldPartColor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWorldPartName(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getDrawableFlag() {
        return null;
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    com.shadowzilot.callcountry.database.Country other) {
        return 0;
    }
    
    public Country(int countryID_, @org.jetbrains.annotations.NotNull()
    java.lang.String countryName_, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCapital_, @org.jetbrains.annotations.NotNull()
    java.lang.String countryFlag_, int worldPartCode_, @org.jetbrains.annotations.NotNull()
    java.lang.String population_, @org.jetbrains.annotations.NotNull()
    java.lang.String square_, @org.jetbrains.annotations.NotNull()
    java.lang.String language_, @org.jetbrains.annotations.NotNull()
    java.lang.String currency_, @org.jetbrains.annotations.NotNull()
    java.lang.String formOfGovernment_, @org.jetbrains.annotations.NotNull()
    java.lang.String religion_, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}