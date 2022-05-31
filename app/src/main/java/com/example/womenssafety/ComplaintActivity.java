package com.example.womenssafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.womenssafety.databinding.ActivityChatBinding;
import com.example.womenssafety.databinding.ActivityComplaintBinding;

public class ComplaintActivity extends DrawerBaseActivity {
    ActivityComplaintBinding activityComplaintBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComplaintBinding= ActivityComplaintBinding.inflate(getLayoutInflater());
        setContentView(activityComplaintBinding.getRoot());
        allocateActivityTitle("Complaint");
    }
}