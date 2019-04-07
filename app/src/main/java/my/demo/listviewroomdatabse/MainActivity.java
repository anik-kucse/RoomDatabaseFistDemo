package my.demo.listviewroomdatabse;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
                Intent intent = new Intent(MainActivity.this, AddNewUserActivity.class);
                startActivityForResult(intent, NEW_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final User user = new User(data.getStringExtra("name"), data.getStringExtra("age"));
        if(requestCode == NEW_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    userRoomDatabase.userDao().insert(user);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            customAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }else{

        }
    }
}
