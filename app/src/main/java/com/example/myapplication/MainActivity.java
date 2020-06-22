package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String ExtraKeyName = "Name";
    public static String ExtraKeySurname = "Surname";
    public static String ExtraKeyNumber = "Number";

    private EditText number;
    private EditText name;
    private EditText surname;
    private Button singButton;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number =  findViewById(R.id.editTextNumber);
        singButton =  findViewById(R.id.buttonLogIn);
        name =  findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextSurname);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        fillFieldsIfUserRegistered();
    }


    private void fillFieldsIfUserRegistered(){
        String stName = sharedPreferences.getString(ExtraKeyName, "");
        String stSurname = sharedPreferences.getString(ExtraKeySurname, "");
        String stNumber= sharedPreferences.getString(ExtraKeyNumber, "");

        if(!stName.isEmpty()&&!stName.isEmpty()&&!stName.isEmpty())
            singButton.setText("Login");

        number.setText(stNumber);
        name.setText(stName);
        surname.setText(stSurname);
    }


    public void openCallTaxiActivity(View view) {
        if (!IsAllFieldsFilled()) {
            ShowCenteredToast("Заполните все поля");
            return;
        }

        Intent intent = new Intent(this, CallActivity.class);
        PassUserDataToActivity(intent);
        SaveUser();
        startActivity(intent);
    }

    private void SaveUser(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ExtraKeyName, name.getText().toString());
        editor.putString(ExtraKeySurname, surname.getText().toString());
        editor.putString(ExtraKeyNumber, number.getText().toString());
        editor.commit();

    }

    private void PassUserDataToActivity(Intent intent){
        intent.putExtra(ExtraKeyName, name.getText().toString());
        intent.putExtra(ExtraKeySurname, surname.getText().toString());
        intent.putExtra(ExtraKeyNumber, number.getText().toString());
    }

    private boolean IsAllFieldsFilled(){
        if(
                number.getText().length() == 0 ||
                name.getText().length() == 0 ||
                surname.getText().length() == 0
        )
            return false;

        return true;
    }

    private void ShowCenteredToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}