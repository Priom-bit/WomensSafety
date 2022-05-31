package com.example.womenssafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.womenssafety.databinding.ActivityChatBinding;
import com.example.womenssafety.databinding.ActivityDangerBinding;

public class DangerActivity extends DrawerBaseActivity {
    ActivityDangerBinding activityDangerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDangerBinding= ActivityDangerBinding.inflate(getLayoutInflater());
        setContentView(activityDangerBinding.getRoot());
        allocateActivityTitle("Danger");
    }
}