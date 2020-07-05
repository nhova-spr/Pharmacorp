package apps.coders4togo.tests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;

public class Con_activity extends AppCompatActivity {

    private FloatingActionButton ins;
    private Button Conn;
    private FirebaseAuth mAuth;
    private EditText Email, pass;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.con_activity);

        Email = findViewById(R.id.mail);
        pass = findViewById(R.id.password);
        Conn = findViewById(R.id.connet);
        progressBar = findViewById(R.id.progressBar);
        ins = findViewById(R.id.btRegister);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Con_activity.this, Inscr_activity.class);
                startActivity(intent);
            }
        });

        Conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });

        if (mAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            startActivity(new Intent(getApplicationContext(), Principal_activity.class));
        }
    }

    private void loginUserAccount() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = Email.getText().toString();
        password = pass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Veuillez entrer votre email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Veuillez entrer votre mot de passe...", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Connection en cours Patientez...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Votre connexion fut un succes", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(Con_activity.this, Principal_activity.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(), "Echec de connection, Veuillez réessayer...", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }

        });

    }





    }

