package com.shadowzilot.callcountry.database

class CountryDBSchema {
    object CountryTable {
        const val NAME = "countries"

        object Cols {
            const val ID = "ID"
            /*const val NAME = "countryName"
            const val CAPITAL = "capitalName"
            const val FLAG_NAME = "flagName"
            const val WORLD_PART_CODE = "worldPartCode"
            const val SQUARE = "square"
            const val POPULATION = "population"
            const val LANGUAGE = "language"
            const val FORM_OF_GOVERNMENT = "formOfGovernment"
            const val RELIGION = "religion"*/
            const val NAME_LEVEL = "nameLevel"
            const val CAPITAL_LEVEL = "capitalLevel"
            const val FLAG_LEVEL = "flagLevel"
            const val LEARN_STATE = "learnState"
        }
    }
}