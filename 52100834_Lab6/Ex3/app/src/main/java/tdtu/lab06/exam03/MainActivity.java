package tdtu.lab06.exam03;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private EventAdapter mAdapter;
    private List<Event> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();

        registerForContextMenu(mListView);
    }

    private void initViews() {
        mListView = findViewById(R.id.listView);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Event("Sinh hoat chu nhiem", "c120", "09/03/2020", "04:00"));
        mData.add(new Event("Huong dan luan van", "c120", "09/03/2020", "04:45"));

        mAdapter = new EventAdapter(this, R.layout.row_layout, mData);
        mListView.setAdapter(mAdapter);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
        menu.setHeaderTitle(mData.get(position).getName());

        menu.add("Delete");
        menu.add("Edit");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
         if (item.getTitle().equals("Delete")) {
            mData.remove(position);
            mAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_option, menu);

        MenuItem menuItem = menu.findItem(R.id.miSwitch);
        menuItem.setActionView(R.layout.actionbar_switch);

        Switch switchEvent = menuItem.getActionView().findViewById(R.id.sw_event);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miDeleteAll:
                AlertDialog.Builder builder = new Builder(this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to remove all events?");
                builder.setPositiveButton("OK", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mData.clear();
                        mAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "The selected phone is deleted!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("CANCEL", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
                AlertDialog confirmDialog = builder.create();
                confirmDialog.show();
                break;
            case R.id.miAbout:
                builder = new Builder(this);
                builder.setTitle("About");
                builder.setMessage("Code by An");
                builder.setPositiveButton("OK", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Nguyen Thanh Phuoc :v", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.miAdd:
                Intent intent = new Intent(MainActivity.this, NewEvent.class);
                startActivityForResult(intent, 1234);
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            Bundle returnedBundle = data.getExtras();
            String title = returnedBundle.getString(NewEvent.TITLE);
            String place = returnedBundle.getString(NewEvent.PLACE);
            String date = returnedBundle.getString(NewEvent.DATE);
            String time = returnedBundle.getString(NewEvent.TIME);
            Event newEvent = new Event(title, place, date, time);
            mData.add(newEvent);
            mAdapter.notifyDataSetChanged();
        }
    }
}