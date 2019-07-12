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

public class MainActivity extends AppCompatActivity {

    private EditText name, email, contactNo, username, password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txtRegistrationName);
        email = findViewById(R.id.txtRegistrationEmail);
        contactNo = findViewById(R.id.txtRegistrationContactNo);
        username = findViewById(R.id.txtRegistrationUsername);
        password = findViewById(R.id.txtRegistrationPassword);

        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (contactNo.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter contact no", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter user name", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {

                    UserBean user = new UserBean();
                    user.setName(name.getText().toString()) ;
                    user.setEmail(email.getText().toString()) ;
                    user.setCotactNo(contactNo.getText().toString()) ;
                    user.setUsername(username.getText().toString()) ;
                    user.setPassword(password.getText().toString()) ;

                    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                    long row=db.addUser(user);

                    if(row > 0)
                    {
                        Toast.makeText(MainActivity.this,"User Registerd Successful",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"User Registerd Su",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}

