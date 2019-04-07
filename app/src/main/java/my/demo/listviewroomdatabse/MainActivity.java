package my.demo.listviewroomdatabse;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int NEW_ACTIVITY_REQUEST_CODE = 1;
    ListView listView;
    UserRoomDatabase userRoomDatabase;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        customAdapter = new CustomAdapter(getApplicationContext());
        listView.setAdapter(customAdapter);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userRoomDatabase = UserRoomDatabase.getUserRoomDatabase(getApplicationContext());
                customAdapter.addUsers(userRoomDatabase.userDao().getAllUser());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Add New User");
                dialog.setContentView(R.layout.new_user);
                dialog.show();

                final EditText dia_name = dialog.findViewById(R.id.dia_name);
                final EditText dia_age = dialog.findViewById(R.id.dia_age);
                Button dia_save = dialog.findViewById(R.id.dia_save);

                dia_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final User user = new User(dia_name.getText().toString(), dia_age.getText().toString());
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                userRoomDatabase.userDao().insert(user);
                                customAdapter.addLastUser(userRoomDatabase.userDao().getLastUser());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                        dialog.cancel();
                    }
                });

//                Intent intent = new Intent(MainActivity.this, AddNewUserActivity.class);
//                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        final User user = new User(data.getStringExtra("name"), data.getStringExtra("age"));
//        if(requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    userRoomDatabase.userDao().insert(user);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            customAdapter.notifyDataSetChanged();
//                        }
//                    });
//                }
//            });
//        }else{
//
//        }
//    }
}
