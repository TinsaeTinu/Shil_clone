package com.tinsaeLem.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public Button bt , bt_image ;
    public TextView  tv_name;
    public TextView  tv_country;
    public TextView  tv_phone;
    public ListView list_first;

    private String val_name;
    private String val_country;
    private String val_phone;
    public  SharedPreferences prefs;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // ASSIGMENT
        bt = (Button) findViewById(R.id.button);
        bt_image = (Button) findViewById(R.id.bt_image);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_country = (TextView) findViewById(R.id.tv_country);

        bt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        // button onclickk
        bt.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        //get and set value
                        val_name    = tv_name.getText().toString();
                        val_country = tv_country.getText().toString();
                        val_phone   = tv_phone.getText().toString();
                        // log message
                        Log.v("Name: " , val_name);
                        Log.v("Country_Code: " , val_country);
                        Log.v("Phone_num: " , val_phone);
                        // save to cache
                        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("name", val_name);
                        editor.putString("country_code", val_country);
                        editor.putString("phone_num", val_phone);
                        editor.commit();

                        // show data
                        String names = prefs.getString("name","");
                        String country = prefs.getString("country_code","");
                        String phone = prefs.getString("phone_num","");

                        Log.v("Cache Name: " , names);
                        Log.v("Cache Country_Code: " , country);
                        Log.v("Cache Phone_num: " , phone);

                        // Switching to the other activity
                        Intent intent = new Intent(getApplicationContext(), ScreenA.class);
                        startActivity(intent);
                    }
                });
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                    
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

}