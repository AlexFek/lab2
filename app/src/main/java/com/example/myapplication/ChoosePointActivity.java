package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChoosePointActivity extends AppCompatActivity {

    public static String ExtraKeyPointA = "PointA";
    public static String ExtraKeyHouseA = "HouseA";
    public static String ExtraKeyFlatA = "FlatA";
    public static String ExtraKeyPointB = "PointA";
    public static String ExtraKeyHouseB = "HouseA";
    public static String ExtraKeyFlatB = "FlatA";

    private EditText pointA;
    private EditText houseA;
    private EditText flatA;
    private EditText pointB;
    private EditText houseB;
    private EditText flatB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_point);

        pointA = (EditText) findViewById(R.id.editTextStreetA);
        houseA = (EditText) findViewById(R.id.editTextHouseA);
        flatA = (EditText) findViewById(R.id.editTextFlatA);
        pointB = (EditText) findViewById(R.id.editTextStreetB);
        houseB = (EditText) findViewById(R.id.editTextHouseB);
        flatB = (EditText) findViewById(R.id.editTextFlatB);
    }


    private void PassUserDataToActivity(Intent intent){
        intent.putExtra(ExtraKeyPointA, pointA.getText().toString());
        intent.putExtra(ExtraKeyHouseA, houseA.getText().toString());
        intent.putExtra(ExtraKeyFlatA, flatA.getText().toString());
        intent.putExtra(ExtraKeyPointB, pointB.getText().toString());
        intent.putExtra(ExtraKeyHouseB, houseB.getText().toString());
        intent.putExtra(ExtraKeyFlatB, flatB.getText().toString());
    }

    private boolean IsAllFieldsFilled(){
        if(
                pointA.getText().length() == 0 ||
                houseA.getText().length() == 0 ||
                flatA.getText().length() == 0 ||
                pointB.getText().length() == 0 ||
                houseB.getText().length() == 0 ||
                flatB.getText().length() == 0
        )
            return false;

        return true;
    }

    public void openCallTaxiActivity(View view) {
        if (!IsAllFieldsFilled()) {
            ShowCenteredToast("Заполните все поля");
            return;
        }

        Intent intent = new Intent(this, CallActivity.class);
        PassUserDataToActivity(intent);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void ShowCenteredToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}