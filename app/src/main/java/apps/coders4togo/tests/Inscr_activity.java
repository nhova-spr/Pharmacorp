package apps.coders4togo.tests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Inscr_activity extends AppCompatActivity {

    private Button con;
    private EditText editTextEmail, editTextPassword,name;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscr_activity);

        editTextEmail = findViewById(R.id.mail2);
        name = findViewById(R.id.user);
        editTextPassword = findViewById(R.id.password2);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressBar = findViewById(R.id.progressBar2);
        con = findViewById(R.id.connet);

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), Principal_activity.class));
        }
    }

    private void registerUser(){

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String names = name.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Inscr_activity.this, "Veuillez entrer votre email...",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(Inscr_activity.this,"Veuillez entrer votre mot de passe...",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(names)){
            Toast.makeText(Inscr_activity.this,"Veuillez entrer votre nom d'utilisateur...",Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Inscription en cours Patientez...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Inscr_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            startActivity(new Intent(getApplicationContext(),Principal_activity.class) );
                            Toast.makeText(Inscr_activity.this, "Votre enrégistrement fut un succès...",Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(Inscr_activity.this, "Echec d'inscription, Réessayez svp...", Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }

}