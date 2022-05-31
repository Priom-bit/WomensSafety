package com.example.womenssafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private boolean passwordshowing = false;
    Button signinBtn;
    TextView signupBtn;
    EditText emailET,passwordET;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signinBtn=findViewById(R.id.signinBtn);
        signupBtn=findViewById(R.id.signupBtn);
        emailET=findViewById(R.id.emailET);
        passwordET=findViewById(R.id.passwordET);
        mAuth =FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Login.this);



        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final TextView forgotpassBtn = findViewById(R.id.forgotpassBtn);


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrededentials();
                startActivity(new Intent(Login.this, HomeActivity.class));
            }
        });

        forgotpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassword.class));  
            }
        });

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
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    private void checkCrededentials() {
        String email=emailET.getText().toString();
        String password=passwordET.getText().toString();

        if  (email.isEmpty() || !email.contains("@"))
        {
            showError(emailET,"Email is not valid");
        }
        else if (password.isEmpty() || password.length()<6)
        {
            showError(passwordET,"Password must be 6 character");
        }
        else{
            mLoadingBar.setTitle("Sign In");
            mLoadingBar.setMessage("Please wait,while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        mLoadingBar.dismiss();
                        Intent intent=new Intent(Login.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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