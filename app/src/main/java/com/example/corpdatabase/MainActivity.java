package com.example.corpdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

import io.github.tonnyl.whatsnew.WhatsNew;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    TextView tvOut;
    EditText editName, editSurname, editYear;
    Button butDel, butAdd, butGet;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this, null, null, 1);
        tvOut = findViewById(R.id.tvOut);
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editYear = findViewById(R.id.editYear);
        butDel = findViewById(R.id.buttonDel);
        butAdd = findViewById(R.id.buttonAdd);
        butGet = findViewById(R.id.buttonGet);
        butDel.setOnClickListener(this);
        butAdd.setOnClickListener(this);
        butGet.setOnClickListener(this);

        WhatsNew news = new WhatsNew();

    }

    @Override
    public void onClick(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (v.getId() == R.id.buttonDel) {
                    dbHelper.deleteAll();
                    tvOut.setText(null);
                }
                if (v.getId() == R.id.buttonAdd) {
//            String name = editName.getText().toString();
//            String surname = editSurname.getText().toString();
//            int year = Integer.parseInt(editYear.getText().toString());

                    Data data = new Data(editName.getText().toString(),
                            editSurname.getText().toString(),
                            Integer.parseInt(editYear.getText().toString()));
                    dbHelper.addEmployee(data);
                }
                if (v.getId() == R.id.buttonGet) {
                    LinkedList<Data> list = dbHelper.getAll();

                    text = "";
                    for (Data d : list) text = text + d.name + " " + d.surname + " " + d.year + "\n";

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvOut.setText(text);
                        }
                    });

                }
            }
        }).start();

    }
}