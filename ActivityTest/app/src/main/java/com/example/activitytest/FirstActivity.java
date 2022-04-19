package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.InetSocketAddress;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "clicked Remove", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "from secondactivity", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("firstactivity", this.toString());
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this, "clicked button 1", Toast.LENGTH_SHORT).show();
//                finish();
                //显示声明intent
                String data = "hello secondactivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data",data);
//                Intent intent = new Intent("com.example.activity.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                //隐式intent
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
                //startActivity(intent);
                //startActivityForResult(intent,1);

                Intent intent1 = new Intent(FirstActivity.this, FirstActivity.class);
                startActivity(intent1);
            }
        });

    }
}