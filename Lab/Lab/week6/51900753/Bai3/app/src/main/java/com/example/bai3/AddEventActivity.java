package com.example.bai3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {
    EditText etName;
    EditText etPlace;
    EditText etDate;
    EditText etTime;

    Dialog dialog;

    String name;
    String place;
    Calendar calendar;

    static final String OBJECT_ITEM = "OBJECT_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new Dialog(this);

        etName = findViewById(R.id.item_name);
        etPlace = findViewById(R.id.item_place);
        etDate = findViewById(R.id.item_date);
        etTime = findViewById(R.id.item_time);
        calendar = Calendar.getInstance();

        //disable keyboard
        etPlace.setShowSoftInputOnFocus(false);
        etDate.setShowSoftInputOnFocus(false);
        etTime.setShowSoftInputOnFocus(false);

        etPlace.setOnFocusChangeListener(onFocusChangeListener);
        etDate.setOnFocusChangeListener(onFocusChangeListener);
        etTime.setOnFocusChangeListener(onFocusChangeListener);

        etPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPlaceDialog();
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDiaglog();
            }
        });
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_event_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //################################## SAVE NEW ITEM ##############################################
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save_item) {
//            Date date = new Date("26/3/2022 16:22");
//            Date date = Calendar.getInstance().getTime();

            name = etName.getText().toString();
            place = etPlace.getText().toString();

            if (name.isEmpty()) {
                etName.setError("Please enter event name.");
                return false;
            }
            if (place.isEmpty()) {
                etPlace.setError("Please enter event name.");
                return false;
            }
            if (etDate.getText().toString().isEmpty()) {
                etDate.setError("Please enter event name.");
                return false;
            }
            if (etTime.getText().toString().isEmpty()) {
                etTime.setError("Please enter event name.");
                return false;
            }

            Item newItem = new Item(name, place, calendar);
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();

            bundle.putSerializable(OBJECT_ITEM, newItem);
            intent.putExtras(bundle);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean enterFocus) {
            if (enterFocus) {
                if (view.getId() == R.id.item_place) showSelectPlaceDialog();
                else if (view.getId() == R.id.item_date) showDatePickerDiaglog();
                else if (view.getId() == R.id.item_time) showTimePickerDialog();
            }
        }
    };

    //############################### PICK PLACE ########################################################3
    public void showSelectPlaceDialog() {
        CompoundButton.OnCheckedChangeListener onSelectedPlaceListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etPlace.setText(compoundButton.getText());
                    etPlace.clearFocus();
                    etPlace.setError(null);
                    dialog.dismiss();
                }
            }
        };

        dialog.setContentView(R.layout.dialog_places);

        RadioButton rd1 = dialog.findViewById(R.id.rd1);
        RadioButton rd2 = dialog.findViewById(R.id.rd2);
        RadioButton rd3 = dialog.findViewById(R.id.rd3);
        RadioButton rd4 = dialog.findViewById(R.id.rd4);

        rd1.setOnCheckedChangeListener(onSelectedPlaceListener);
        rd2.setOnCheckedChangeListener(onSelectedPlaceListener);
        rd3.setOnCheckedChangeListener(onSelectedPlaceListener);
        rd4.setOnCheckedChangeListener(onSelectedPlaceListener);

        dialog.show();
    }

    //############################### PICK DATE ########################################################3
    public void showDatePickerDiaglog() {
        dialog.setContentView(R.layout.dialog_datepicker);

        Button btnOK = dialog.findViewById(R.id.btnOK);
        TextView tvCancel = dialog.findViewById(R.id.tvCancel);
        DatePicker datePicker = dialog.findViewById(R.id.dpDP);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day  = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                calendar.set(year, month, day);

//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, month, day);
//                Date a = new Date();
//
////                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//                String formatedDate = sdf.format(calendar.getTime());
//                etDate.setText(formatedDate);

                SimpleDateFormat sdfTime = new SimpleDateFormat("dd-MM-yyyy");
                etDate.setText(sdfTime.format(calendar.getTime()));
                etDate.setError(null);
                etDate.clearFocus();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDate.clearFocus();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //############################### PICK TIME ########################################################3
    public void showTimePickerDialog() {
        dialog.setContentView(R.layout.dialog_timepicker);

        Button btnOK = dialog.findViewById(R.id.btnOK);
        TextView tvCancel = dialog.findViewById(R.id.tvCancel);
        TimePicker timePicker = dialog.findViewById(R.id.tpTP);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
                etTime.setText(sdfTime.format(calendar.getTime()));
                etTime.setError(null);
                etTime.clearFocus();
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDate.clearFocus();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}