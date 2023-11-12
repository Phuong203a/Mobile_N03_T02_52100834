package com.example.ex4;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExplorerActivity extends AppCompatActivity {

    private File currentDirectory;
    private ListView listView;
    private List<String> fileList;
    private ArrayAdapter<String> adapter;
    private Button backButton;
    private Button createFolderButton;
    private Button createFileButton;
    private Button deleteButton;
    private TextView currentPathTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        currentDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        listView = findViewById(R.id.listView);
        backButton = findViewById(R.id.backButton);
        createFolderButton = findViewById(R.id.createFolderButton);
        createFileButton = findViewById(R.id.createFileButton);
        deleteButton = findViewById(R.id.deleteButton);
        currentPathTextView = findViewById(R.id.currentPathTextView);

        fileList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fileList);
        listView.setAdapter(adapter);

        showFiles(currentDirectory);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                File clickedFile = new File(currentDirectory, selectedItem);
                if (clickedFile.isDirectory()) {
                    showFiles(clickedFile);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentDirectory.getParentFile() != null) {
                    showFiles(currentDirectory.getParentFile());
                }
            }
        });

        createFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFolder();
            }
        });

        createFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFile();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedFiles();
            }
        });
    }

    private void showFiles(File directory) {
        currentDirectory = directory;
        currentPathTextView.setText(currentDirectory.getAbsolutePath());
        fileList.clear();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                fileList.add(file.getName());
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void createNewFolder() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Folder");

        final File currentPath = currentDirectory;

        builder.setView(R.layout.dialog_create_folder);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String folderName = ((AlertDialog) dialog).findViewById(R.id.folderNameEditText).getText().toString().trim();
                if (!folderName.isEmpty()) {
                    File newFolder = new File(currentPath, folderName);
                    if (newFolder.mkdir()) {
                        Toast.makeText(getApplicationContext(), "Folder created", Toast.LENGTH_SHORT).show();
                        showFiles(currentPath);
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to create folder", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a folder name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    private void createNewFile() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New File");

        final File currentPath = currentDirectory;

        builder.setView(R.layout.dialog_create_file);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fileName = ((AlertDialog) dialog).findViewById(R.id.fileNameEditText).getText().toString().trim();
                if (!fileName.isEmpty()) {
                    File newFile = new File(currentPath, fileName);
                    try {
                        if (newFile.createNewFile()) {
                            Toast.makeText(getApplicationContext(), "File created", Toast.LENGTH_SHORT).show();
                            showFiles(currentPath);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to create file", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a file name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    private void deleteSelectedFiles() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Selected Files");

        builder.setMessage("Are you sure you want to delete the selected files and folders?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Get selected items from the adapter
                SparseBooleanArray selectedItems = listView.getCheckedItemPositions();
                for (int i = selectedItems.size() - 1; i >= 0; i--) {
                    int position = selectedItems.keyAt(i);
                    if (selectedItems.get(position)) {
                        String fileName = adapter.getItem(position);
                        File fileToDelete = new File(currentDirectory, fileName);
                        deleteFileOrDirectory(fileToDelete);
                    }
                }
                showFiles(currentDirectory);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    private void deleteFileOrDirectory(File file) {
        if (file.isDirectory()) {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File current : contents) {
                    deleteFileOrDirectory(current);
                }
            }
        }
        file.delete();
    }
}
