package com.shadowzilot.callcountry.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.shadowzilot.callcountry.utils.FragmentSwitchListener;
import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.database.Country;
import com.shadowzilot.callcountry.database.WPGList;
import com.shadowzilot.callcountry.views.TimerView;
import com.shadowzilot.callcountry.views.WorldPartButton;

import java.util.ArrayList;

public class GameWorldPartsFragment extends Fragment {
    private static final String LOG_TAG = GameWorldPartsFragment.class.getSimpleName();

    private static final int PRE_START_TIMER_MILLISECONDS = 3000;
    private static final int START_TIMER_MILLISECONDS =  60000;
    private static final int DELTA_TIMER_MILLISECONDS =  10;

    private FragmentSwitchListener mListener;

    private int mScore;

    private WPGList mWPGList;
    private final ArrayList<Integer> mErrorsList = new ArrayList<>();

    private TextView mScoreView;
    private WorldPartButton mAnswers;
    private ImageView mFlagImage;
    private Country mCurrentCountry;

    private TimerView mTimerView;
    private CountDownTimer mTimer;
    private ViewGroup mPauseLayout;
    private AppCompatButton mResumeButton;
    private AppCompatButton mLeaveButton;
    private float mSeconds = -1;

    private boolean isEnd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_world_part_game, container,
                false);

        mWPGList = new WPGList(requireActivity());

        mScoreView = rootView.findViewById(R.id.score_view);
        mFlagImage = rootView.findViewById(R.id.wpg_image_flag);
        mAnswers = rootView.findViewById(R.id.wpg_answers);
        mAnswers.setOnWorldPartClickListener(worldPartCode -> {
            if (worldPartCode != -1) {
                checkAnswer(worldPartCode);
            }
        });

        mTimerView = rootView.findViewById(R.id.wpg_timer);
        mPauseLayout = rootView.findViewById(R.id.pause_layout);

        mResumeButton = rootView.findViewById(R.id.wpg_resume_button);
        mResumeButton.setOnClickListener(v -> {
            resumeTimer();
        });
        mLeaveButton = rootView.findViewById(R.id.wpg_leave_button);
        mLeaveButton.setOnClickListener(v -> {
            getActivity().finish();
        });

        return rootView;
    }

    private void checkAnswer(int itemCode) {
        try {
            if (itemCode == mCurrentCountry.getWorldPartCode()) {
                mScore += 10;
            } else {
                mScore -= 5;
                if (!mErrorsList.contains(mCurrentCountry.getCountryID())) {
                    mErrorsList.add(mCurrentCountry.getCountryID());
                }
                if (mScore < 0) {
                    mScore = 0;
                }
            }
            mScoreView.setText(String.valueOf(mScore));
            initQuestion();
        } catch (java.lang.NullPointerException e) {
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    private void initQuestion() {
        mCurrentCountry = mWPGList.getNextCountry();
        mFlagImage.setImageDrawable(mCurrentCountry.getDrawableFlag());
    }

    private void startTimer() {
        isEnd = false;
        mTimerView.setMaxTime(START_TIMER_MILLISECONDS / 1000);
        if (mSeconds == -1) {
            initQuestion();
            mSeconds = START_TIMER_MILLISECONDS / 1000f;
        } else {
            mFlagImage.setImageDrawable(mCurrentCountry.getDrawableFlag());
        }
        mTimer = new CountDownTimer((long) (mSeconds*1000f), DELTA_TIMER_MILLISECONDS) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSeconds = millisUntilFinished/1000f;
                mTimerView.setCurrentTime(mSeconds);
            }

            @Override
            public void onFinish() {
                isEnd = true;
                mTimerView.setCurrentTime(0);
                mListener.switchFragment(mScore, mErrorsList);
            }
        }.start();
    }

    private void resumeTimer() {
        mPauseLayout.setVisibility(View.GONE);

        mTimerView.setMaxTime(3);
        mTimer = new CountDownTimer(PRE_START_TIMER_MILLISECONDS, DELTA_TIMER_MILLISECONDS) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerView.setCurrentTime(millisUntilFinished/1000f);
            }

            @Override
            public void onFinish() {
                startTimer();
            }
        }.start();
    }

    private void pauseTimer() {
        mFlagImage.setImageResource(R.drawable.ic_unknow);
        mPauseLayout.setVisibility(View.VISIBLE);
        mTimer.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isEnd) {
            pauseTimer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPauseLayout.getVisibility() != View.VISIBLE) {
            resumeTimer();
        }
    }

    public void setSwitchListener(FragmentSwitchListener listener) {
        mListener = listener;
    }

    static public GameWorldPartsFragment newInstance() {
        GameWorldPartsFragment fragment = new GameWorldPartsFragment();

        return fragment;
    }
}
