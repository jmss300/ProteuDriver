package com.techathon.proteudriver;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.techathon.proteudriver.app.App;
import com.techathon.proteudriver.managers.SessionManager;
import com.techathon.proteudriver.models.Driver;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private Button bAccident;
    private boolean simulatingAccident = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(getApplicationContext());

        if (!sessionManager.isLoggedIn()) {
            sessionManager.startLoginActivity();
            finish();
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Connecting Server");
        progressDialog.setMessage("Fetching User Information");
        progressDialog.isIndeterminate();

        String accessToken = sessionManager.getLoggedAccessToken();
        Toast.makeText(this.getApplicationContext(), accessToken, Toast.LENGTH_LONG);
        App.setClubService(accessToken);

        if(App.currentUser == null) {
            getCurrentUser();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bAccident = (Button) findViewById(R.id.accident);
        setTextButtonAccident();

        bAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulatingAccident = !simulatingAccident;
                setTextButtonAccident();

                if(simulatingAccident) {
                    
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getCurrentUser() {
        progressDialog.show();

        Call<Driver> call = App.getDriverService().getCurrentUser();
        call.enqueue(new Callback<Driver>() {
            @Override
            public void onResponse(Response<Driver> response, Retrofit retrofit) {
                Driver user = response.body();

                if(user != null) {
                    App.setCurrentUser(user);
                } else {
                    Toast.makeText(getBaseContext(), "An error has occured getting user: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(), "An error has occured get user: " + t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    public void setTextButtonAccident() {
        String text = (simulatingAccident) ?  "Stop Accident": "Simulate Accident";
        bAccident.setText(text);
    }
}
