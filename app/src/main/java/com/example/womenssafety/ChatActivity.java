package com.example.womenssafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.womenssafety.databinding.ActivityChatBinding;

public class ChatActivity extends DrawerBaseActivity {
    ActivityChatBinding activityChatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding= ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());
        allocateActivityTitle("Chat");
    }
}