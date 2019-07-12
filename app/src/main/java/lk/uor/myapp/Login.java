package lk.uor.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lk.uor.myapp.bean.UserBean;
import lk.uor.myapp.db.DatabaseHandler;

public class Login extends AppCompatActivity {

    private EditText  username, password;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txtRegistrationUsername);
        password = findViewById(R.id.txtRegistrationPassword);

        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                if (username.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHandler db = new DatabaseHandler(Login.this);
                    UserBean user = db.getUser(username.getText().toString(),password.getText().toString());
                    if(user == null) {
                        Toast.makeText(Login.this, "iNVALID  ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(Login.this, "Login Susscess ", Toast.LENGTH_LONG).show();



                }
            }
        });


    }
}
