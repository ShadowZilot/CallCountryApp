package com.shadowzilot.callcountry.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.utils.FragmentSwitchListener;

import java.util.ArrayList;

public class WorldPartGameActivity extends AppCompatActivity {
    private FragmentContainerView mHost;
    private FragmentManager mHostManager;

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_world_part_game);

        mHost = findViewById(R.id.world_part_game_host);
        mHostManager = getSupportFragmentManager();

        startGame();
    }

    private void startGame() {
        FragmentTransaction transaction = mHostManager.beginTransaction();
        GameWorldPartsFragment fragment = GameWorldPartsFragment.newInstance();
        fragment.setSwitchListener((score, errorsList) -> {
                startSummary(score, errorsList);
        });

        transaction.replace(R.id.world_part_game_host, fragment).commit();
    }

    private void startSummary(int score, ArrayList<Integer> errorsList) {
        FragmentTransaction transaction = mHostManager.beginTransaction();
        GameWorldPartsSummary fragment = GameWorldPartsSummary.newInstance(score, errorsList);

        transaction.replace(R.id.world_part_game_host, fragment).commit();
    }
}
