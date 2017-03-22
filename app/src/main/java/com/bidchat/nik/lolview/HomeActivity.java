package com.bidchat.nik.lolview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    public static final String USER = "user";
    Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final User newUser = new User();
        newUser.setUserId(123);
        newUser.setUserImageUrl("https://lh3.googleusercontent.com/-Y86IN-vEObo/AAAAAAAAAAI/AAAAAAAKyAM/6bec6LqLXXA/s0-c-k-no-ns/photo.jpg");
        newUser.setUserName("Nikesh Shetye");

        buttonClick = (Button) findViewById(R.id.button_click);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(HomeActivity.this, MainActivity.class);
                mainActivityIntent.putExtra(HomeActivity.USER, newUser);
                startActivity(mainActivityIntent);
            }
        });
    }
}
