package com.shadowzilot.callcountry.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/shadowzilot/callcountry/database/CountryDBSchema;", "", "()V", "CountryTable", "app_debug"})
public final class CountryDBSchema {
    
    public CountryDBSchema() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/shadowzilot/callcountry/database/CountryDBSchema$CountryTable;", "", "()V", "NAME", "", "Cols", "app_debug"})
    public static final class CountryTable {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String NAME = "countries";
        @org.jetbrains.annotations.NotNull()
        public static final com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable INSTANCE = null;
        
        private CountryTable() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/shadowzilot/callcountry/database/CountryDBSchema$CountryTable$Cols;", "", "()V", "CAPITAL_LEVEL", "", "FLAG_LEVEL", "ID", "LEARN_STATE", "NAME_LEVEL", "app_debug"})
        public static final class Cols {
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String ID = "ID";
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String NAME_LEVEL = "nameLevel";
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String CAPITAL_LEVEL = "capitalLevel";
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String FLAG_LEVEL = "flagLevel";
            @org.jetbrains.annotations.NotNull()
            public static final java.lang.String LEARN_STATE = "learnState";
            @org.jetbrains.annotations.NotNull()
            public static final com.shadowzilot.callcountry.database.CountryDBSchema.CountryTable.Cols INSTANCE = null;
            
            private Cols() {
                super();
            }
        }
    }
}