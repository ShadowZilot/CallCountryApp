package com.shadowzilot.callcountry.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.shadowzilot.callcountry.CountryAdapterView;
import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.database.Country;
import com.shadowzilot.callcountry.database.CountryLab;
import com.shadowzilot.callcountry.views.WPGScoreTable;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static android.content.SharedPreferences.*;
import static com.shadowzilot.callcountry.CountryDetailActionKt.ARG_COUNTRY_ID;

public class GameWorldPartsSummary extends Fragment implements OnItemClickListener {
    private static final String SCORE_ARG = "SCORE_ARG";
    private static final String ERROR_LIST_ARG = "ERRORS_LIST";
    public static final String APP_WPG_HIGH_SCORE = "HIGH_SCORE";

    private int mScore;
    private int mHighScore;

    private Toolbar mAppBar;
    private AppBarLayout mBarLayout;
    private RecyclerView mErrorsView;
    private WPGScoreTable mScoreView;
    private LinearLayout mErrorsContainer;
    private ImageView mNoErrorsImage;
    private TextView mNoErrorsText;

    private ArrayList<Country> mListOfCountries = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScore = requireArguments().getInt(SCORE_ARG);
        ArrayList<Integer> ids = requireArguments().getIntegerArrayList(ERROR_LIST_ARG);
        CountryLab database = CountryLab.Companion.get(requireActivity());

        mHighScore = getWPGHighScore();
        if (mScore > mHighScore) {
            setWPGHighScore(mScore);
        }

        for (Integer id : ids) {
            mListOfCountries.add(database.getCountryById(id));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wpg_summary, container,
                false);

        mErrorsContainer = rootView.findViewById(R.id.errors_container);

        mScoreView = rootView.findViewById(R.id.score_summary);
        mScoreView.setHighScore(mHighScore);
        mScoreView.setScore(0f);

        mBarLayout = rootView.findViewById(R.id.collapse_bar);

        mAppBar = rootView.findViewById(R.id.wpg_summary_toolbar);
        mAppBar.setNavigationOnClickListener(v -> {
            requireActivity().finish();
        });

        mNoErrorsImage = rootView.findViewById(R.id.no_errors_image);
        mNoErrorsText = rootView.findViewById(R.id.no_errors_label);
        mErrorsView = rootView.findViewById(R.id.list_of_wrongs);

        if (!mListOfCountries.isEmpty()) {
            mErrorsView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            mErrorsView.setAdapter(new CountryAdapterView(mListOfCountries, this));
        } else {
            mErrorsView.setVisibility(View.GONE);
            mNoErrorsText.setVisibility(View.VISIBLE);
            mNoErrorsImage.setVisibility(View.VISIBLE);
        }
        changeScrollState(false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ObjectAnimator containerAnimator = ObjectAnimator.ofFloat(mErrorsContainer,
                "alpha", 0, 1);
        ObjectAnimator containerOffset = ObjectAnimator.ofFloat(mErrorsContainer, "Y",
                mErrorsContainer.getY() + 700, mErrorsContainer.getY() + 500);
        AnimatorSet containerSet = new AnimatorSet();
        containerSet.setDuration(2000);

        ObjectAnimator animatorScore = ObjectAnimator.ofFloat(mScoreView,
                "score", 0f, mScore);
        ObjectAnimator animatorCamera = ObjectAnimator.ofFloat(mScoreView,
                "offset", 0f, mScoreView.getEndScorePos(mScore));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(8500);
        animatorSet.playTogether(animatorScore, animatorCamera);
        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mScoreView.drawHighScore();
                mErrorsContainer.setVisibility(View.VISIBLE);
                containerSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        changeScrollState(true);
                    }
                });
                containerSet.playTogether(containerAnimator, containerOffset);
                containerSet.start();
            }
        });
    }

    private int getWPGHighScore() {
        SharedPreferences lPreferences = getActivity().getSharedPreferences("records",
                Context.MODE_PRIVATE);
        if (lPreferences.contains(APP_WPG_HIGH_SCORE)) {
            return lPreferences.getInt(APP_WPG_HIGH_SCORE, 0);
        }
        return 0;
    }

    private void setWPGHighScore(int newHighScore) {
        SharedPreferences lPreferences = getActivity().getSharedPreferences("records",
                Context.MODE_PRIVATE);
        Editor lEditor = lPreferences.edit();
        lEditor = lEditor.clear();
        lEditor.putInt(APP_WPG_HIGH_SCORE, newHighScore);
        lEditor.apply();
    }

    @Override
    public void onItemClick(Country country) {
        Intent intent = new Intent(requireContext(), ExamDetailActivity.class);
        intent.putExtra(ARG_COUNTRY_ID, country.getCountryID());
        startActivity(intent);
    }

    private void changeScrollState(boolean isScroll) {
        CoordinatorLayout.LayoutParams lParams =
                (CoordinatorLayout.LayoutParams) mBarLayout.getLayoutParams();
        AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
        appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return isScroll;
            }
        });
        mErrorsView.setNestedScrollingEnabled(isScroll);
        lParams.setBehavior(appBarLayoutBehaviour);
        mBarLayout.setLayoutParams(lParams);
    }

    public static GameWorldPartsSummary newInstance(int score, ArrayList<Integer> errorsList) {
        Bundle args = new Bundle();
        args.putSerializable(ERROR_LIST_ARG, errorsList);
        args.putInt(SCORE_ARG, score);
        GameWorldPartsSummary fragment = new GameWorldPartsSummary();
        fragment.setArguments(args);

        return fragment;
    }
}
