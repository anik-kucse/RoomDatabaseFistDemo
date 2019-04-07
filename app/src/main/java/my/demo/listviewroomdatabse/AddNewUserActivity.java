package my.demo.listviewroomdatabse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewUserActivity extends AppCompatActivity {

    EditText editName;
    EditText editAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_age);

        final Button button = findViewById(R.id.edit_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editName.getText()) || TextUtils.isEmpty(editAge.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String name = editName.getText().toString();
                    String age = editAge.getText().toString();
                    replyIntent.putExtra("name", name);
                    replyIntent.putExtra("age", age);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });
    }
}
