package com.techathon.proteudriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techathon.proteudriver.app.App;
import com.techathon.proteudriver.models.Driver;
import com.techathon.proteudriver.utils.InputValidator;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Activity user for sign up
 *
 * @author Proteu
 */
public class SignupActivity extends AppCompatActivity {

    // UI references.
    private EditText mNameView;
    private EditText mCityView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mRePasswordView;
    private ProgressDialog progressDialog;

    /**
     * Called when the activity is first created. This method set necessary info on creation.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mNameView = (EditText) findViewById(R.id.name);
        mCityView = (EditText) findViewById(R.id.city);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mRePasswordView = (EditText) findViewById(R.id.repassword);

        Button mSignupButton = (Button) findViewById(R.id.sign_up_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        TextView mLoginLink = (TextView) findViewById(R.id.login_link);
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setTitle("Create Account");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sign Up");
        progressDialog.setMessage("Registering account");
        progressDialog.isIndeterminate();
    }

    /**
     * Method that performs an attempt on sign up
     */
    private void attemptSignup() {
        // Reset errors.
        mNameView.setError(null);
        mCityView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mRePasswordView.setError(null);

        // Store values at the time of the signup attempt.
        final String name = mNameView.getText().toString();
        final String city = mCityView.getText().toString();
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String repassword = mRePasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid retyped password
        if(!repassword.equals(password)) {
            mRePasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mRePasswordView;
            cancel = true;
        }

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if(!InputValidator.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!InputValidator.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for valid contact
        if(TextUtils.isEmpty(city)) {
            mCityView.setError(getString(R.string.error_field_required));
            focusView = mCityView;
            cancel = true;
        }

        // Check for a valid name.
        if(TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!InputValidator.isNameValid(name)) {
            mNameView.setError(getString(R.string.error_invalid_name));
            focusView = mNameView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner
            progressDialog.show();

            Driver newDriver = new Driver("", "", "", "", "", "", "", "");
            Call<Driver> call = App.getLoginService().createDriver(newDriver);
            call.enqueue(new Callback<Driver>() {
                @Override
                public void onResponse(Response<Driver> response, Retrofit retrofit) {
                    Driver created = response.body();

                    // TODO: Handle server errors like duplicated email, etc
                    if(response.code() == 200 && created != null) {
                        Toast.makeText(getBaseContext(), "Your account was successfully created!", Toast.LENGTH_LONG).show();

                        Intent i = new Intent();
                        i.putExtra("email", created.getEmail());
                        setResult(RESULT_OK, i);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "An error has occured (" + response.code() + ")", Toast.LENGTH_LONG).show();
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getBaseContext(), "An error has occured: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        }

    }

}
