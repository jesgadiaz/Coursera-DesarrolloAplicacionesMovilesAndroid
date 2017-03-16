package com.anncode.aplicacioncontactos.configuser;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anncode.aplicacioncontactos.MainActivity;
import com.anncode.recyclerviewfragments.R;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class ConfigUserActivity extends AppCompatActivity {

    TextInputEditText textInputEditText;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_user);

        textInputEditText = (TextInputEditText) findViewById(R.id.newUser);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = textInputEditText.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("newUser",newUser);
                setResult(MainActivity.REQUEST_CODE,intent);
                finish();

            }
        });
    }

}
