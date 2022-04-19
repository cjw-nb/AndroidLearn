package com.example.uiwidgettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.edit_text);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                //Toast.makeText(this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something Important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.button2:
                if(progressBar.getVisibility() ==View.GONE )
                    progressBar.setVisibility(View.VISIBLE);
                else progressBar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}