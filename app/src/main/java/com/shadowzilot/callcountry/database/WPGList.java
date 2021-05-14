package com.shadowzilot.callcountry.database;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class WPGList {
    private static final String sLogTag = WPGList.class.getSimpleName();
    private ArrayList<Integer> mListOfExceptions = new ArrayList<>();
    private ArrayList<Country> mCountriesList;
    private ArrayList<Country> mLearnedList;

    private CountryLab mDataBase;

    ArrayList<Integer> mLeftSide = new ArrayList<>();

    public WPGList(Context context) {
        mLeftSide.add(0);
        mLeftSide.add(5);
        mLeftSide.add(4);

        mDataBase = CountryLab.Companion.get(context);
        mCountriesList = (ArrayList<Country>) mDataBase.getMCountries();
        mLearnedList = makeListValid(mDataBase.getLearnedCountriesList());
    }

    public Country getNextCountry() {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        if (!mLearnedList.isEmpty()) {

            Country randomLearnedCountry = mLearnedList.get(r.nextInt(mLearnedList.size()));
            while (!isCountryValid(randomLearnedCountry)) {
                randomLearnedCountry = mLearnedList.get(r.nextInt(mLearnedList.size()));
            }
            mLearnedList.remove(randomLearnedCountry);
            mListOfExceptions.add(randomLearnedCountry.getCountryID());
            return randomLearnedCountry;
        } else {
            Country randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
            while (!isCountryValid(randomCountry)) {
                randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
            }
            mListOfExceptions.add(randomCountry.getCountryID());
            return randomCountry;
        }
    }

    private ArrayList<Country> makeListValid(ArrayList<Country> _countriesList) {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int amountOfLeft = 0;
        int amountOfRight = 0;
        for (Country _country : _countriesList) {
            if (mLeftSide.contains(_country.getWorldPartCode())) {
                amountOfLeft++;
            } else {
                amountOfRight++;
            }
        }
        if (amountOfLeft > amountOfRight) {
            // Add more right
            for (int i = 0; i < amountOfLeft - amountOfRight; i++) {
                Country randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
                while (mLeftSide.contains(randomCountry.getWorldPartCode())
                        | _countriesList.contains(randomCountry)) {
                    randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
                }
                _countriesList.add(randomCountry);
            }

        } else if (amountOfRight > amountOfLeft) {
            // Add more left
            for (int i = 0; i < amountOfRight - amountOfLeft; i++) {
                Country randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
                while (!mLeftSide.contains(randomCountry.getWorldPartCode())
                        | _countriesList.contains(randomCountry)) {
                    randomCountry = mDataBase.getCountryById(mCountriesList.get(r.nextInt(mCountriesList.size())).getCountryID());
                }
                _countriesList.add(randomCountry);
            }
        } else {
            return _countriesList;
        }

        return _countriesList;
    }

    private boolean isCountryValid(Country _checkingCountry) {

        if (!mListOfExceptions.contains(_checkingCountry.getCountryID())) {
            Country lastExcludedCountry = getLastExcludedCountry();
            boolean isLastInRightSide = false;
            if (lastExcludedCountry != null) {
                isLastInRightSide = mLeftSide.contains(lastExcludedCountry.getWorldPartCode());
            }
            boolean isCurrentInRightSide = mLeftSide.contains(_checkingCountry.getWorldPartCode());

            return isCurrentInRightSide ^ isLastInRightSide;
        }
        return false;
    }

    private Country getLastExcludedCountry() {
        if (!mListOfExceptions.isEmpty()) {
            return mDataBase.getCountryById(mListOfExceptions.get(mListOfExceptions.size() - 1));
        }
        return null;
    }
}
