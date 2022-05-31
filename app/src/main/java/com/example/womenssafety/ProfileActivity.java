package com.example.womenssafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.womenssafety.databinding.ActivityChatBinding;
import com.example.womenssafety.databinding.ActivityProfileBinding;

public class ProfileActivity extends DrawerBaseActivity {
    ActivityProfileBinding activityProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding= ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());
        allocateActivityTitle("Profile");
    }
}