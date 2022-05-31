package com.example.womenssafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private boolean passwordshowing = false;
    Button signupBtn;
    TextView signinBtn;
    private EditText usernameET,emailET,mobileET,passwordET;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupBtn=findViewById(R.id.signupBtn);
        usernameET=findViewById(R.id.usernameET);
        emailET=findViewById(R.id.emailET);
        mobileET=findViewById(R.id.mobileET);
        passwordET=findViewById(R.id.passwordET);
        signinBtn=findViewById(R.id.signinBtn);
        mAuth =FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Register.this);

        final ImageView passwordIcon= findViewById(R.id.passwordIcon);




        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (passwordshowing){
                    passwordshowing = false;

                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.show_password);
                }

                else{
                    passwordshowing=true;

                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.hide_password);
                }
                passwordET.setSelection(passwordET.length());
            }
        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkCrededentials() {
        String username=usernameET.getText().toString();
        String email=emailET.getText().toString();
        String mobile=mobileET.getText().toString();
        String password=passwordET.getText().toString();

        if (username.isEmpty() || username.length()<7)
        {
            showError(usernameET,"Your username is not valid");
        }
           else if  (email.isEmpty() || !email.contains("@"))
        {
            showError(emailET,"Email is not valid");
        }
         else if (mobile.isEmpty() || mobile.length()<11)
        {
            showError(mobileET,"Mobile number must be 11 character");
        }
         else if (password.isEmpty() || password.length()<6)
        {
            showError(passwordET,"Password must be 6 character");
        }
        else{
            mLoadingBar.setTitle("Sign Up");
            mLoadingBar.setMessage("Please wait,while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();

                        Intent intent=new Intent(Register.this,Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText ET, String your_username_is_not_valid) {
        ET.setError(your_username_is_not_valid);
        ET.requestFocus();
    }
}
