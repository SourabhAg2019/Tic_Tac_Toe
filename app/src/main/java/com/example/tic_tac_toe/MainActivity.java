package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText player1,player2;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);
        start=findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_player1=player1.getText().toString();
                String get_player2=player2.getText().toString();
                if(get_player1.isEmpty() || get_player2.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Player Names", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(MainActivity.this,game.class);
                    intent.putExtra("name1",get_player1);
                    intent.putExtra("name2",get_player2);
                    startActivity(intent);
                    player1.setText("");
                    player2.setText("");
                }

            }
        });
    }
}