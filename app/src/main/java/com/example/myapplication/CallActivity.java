package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {
    public static  final int REQUEST_ACCESS_TYPE=1;
    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";
    private TextView FullName;
    private TextView Number;
    private TextView ArrivalData;
    private Button CallTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        CallTaxi = findViewById(R.id.buttonCallTaxi);
        FullName = findViewById(R.id.textViewFullName);
        Number = findViewById(R.id.textViewNumberAfterRegistration);
        ArrivalData = findViewById(R.id.textViewArrivalData);

        CallTaxi.setEnabled(false);
        FillFields();
    }


    private void FillFields(){
        Intent intent = getIntent();
        String fullName = intent.getStringExtra(MainActivity.ExtraKeyName) + " " + intent.getStringExtra(MainActivity.ExtraKeySurname);

        FullName.setText(fullName);
        Number.setText(intent.getStringExtra(MainActivity.ExtraKeyNumber));
    }



    public void openChoosePointActivity(View view) {
        Intent intent = new Intent(this, ChoosePointActivity.class);
        startActivityForResult(intent, REQUEST_ACCESS_TYPE);
    }

    public void callTaxi(View view){
        ShowCenteredToast("Такси успешно вызвано");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==REQUEST_ACCESS_TYPE){
            if(resultCode==RESULT_OK){
                ShowCenteredToast("Данные получены");
                CallTaxi.setEnabled(true);
                FillArrivalData(data);
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void FillArrivalData(Intent intent){
        String arrivalData;
        arrivalData =
                "Taxi will arrive at " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyPointA) + ", " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyHouseA) + ", " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyFlatA) +
                " in 5 minutes and take you in " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyPointB) + ", " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyHouseB) + ", " +
                intent.getStringExtra(ChoosePointActivity.ExtraKeyFlatB) +
                ". If you are agre click Call Taxi";

        ArrivalData.setText(arrivalData);
    }

    private void ShowCenteredToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}