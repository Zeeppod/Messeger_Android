package messeger.e.messeger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView enteredName;
    private Button enteredMesseger;
    public static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        enteredName = findViewById(R.id.enteredName);
        enteredMesseger = findViewById(R.id.goToChat);
        enteredMesseger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = enteredName.getText().toString();
                if(name.trim().length() != 0){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    nicknameEmpty(this);

                }
            }
        });
    }

    private void nicknameEmpty(View.OnClickListener view) {

        Toast toast = Toast.makeText(this, "Введите имя", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 250);
        toast.show();
    }
}
