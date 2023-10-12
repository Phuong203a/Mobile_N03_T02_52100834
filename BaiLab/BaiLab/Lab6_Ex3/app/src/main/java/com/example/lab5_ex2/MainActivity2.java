package com.example.lab5_ex2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    EditText name,place,date,time;
    Button save;
    TextView forName, forPlace, forDate, forTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        place = findViewById(R.id.place);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        save = findViewById(R.id.save);
        forName = findViewById(R.id.forName);
        forPlace = findViewById(R.id.forPlace);
        forDate = findViewById(R.id.forDate);
        forTime = findViewById(R.id.forTime);

        SuKien suKien = new SuKien();

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(name.getText() != null)
                    forName.setText("");
                suKien.setTen(name.getText().toString());
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place.getText() != null) {
                    forPlace.setText("");
                    System.out.println(place.getText());
                }
                String[] listItems = {"C201",
                        "C202",
                        "C203",
                        "C204"};

                int selectedIndex = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setSingleChoiceItems(listItems, selectedIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                place.setText(listItems[which]);
                                suKien.setPhong(listItems[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                System.out.println(suKien.getPhong());
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time.getText() != null)
                    forTime.setText("");
                Date current = Calendar.getInstance().getTime();
                int hour = current.getHours();
                int minute = current.getMinutes();
                boolean isMode24H = true;
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity2.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                time.setText(hourOfDay+":"+minute);
                                suKien.setGio(hourOfDay+":"+minute);
                            }
                        },
                        hour, minute, isMode24H);

                timePickerDialog.setCanceledOnTouchOutside(false);
                timePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.getText() != null) {
                    forDate.setText("");
                    System.out.println(date.getText());
                }
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this);
//                datePickerDialog.updateDate(2017, 10, 7);
                datePickerDialog.setCanceledOnTouchOutside(false);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date current = Calendar.getInstance().getTime();
                        datePickerDialog.updateDate(current.getYear(), current.getMonth()+1, current.getDate());
                        String time = current.getDate()+"/"+current.getMonth()+1+"/"+current.getYear();
                        date.setText(time);
                        suKien.setNgay(time);
                    }
                });
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(suKien.getTen() == null) {
                    forName.setText("Please enter event name");
                    forName.requestFocus();
                }
                else if(suKien.getPhong()== null) {
                    forPlace.setText("Please enter event place");
                    forPlace.requestFocus();
                }
                else if(suKien.getNgay()== null) {
                    forDate.setText("Please enter event date");
                    forDate.requestFocus();
                }
                else if(suKien.getGio()== null) {
                    forTime.setText("Please enter event time");
                    forTime.requestFocus();
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("sukien", suKien);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}