package joslabs.newsapp.register_login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import joslabs.newsapp.MainActivity;
import joslabs.newsapp.R;
import joslabs.newsapp.check_network.NointernetActivity;

public class RegisterActivity extends AppCompatActivity {
EditText email,password,cpassword;
    Button btnsignup;
    final static String REGISTER_URL="http://joslabs.co.ke/hama/testreg.php";
    final static String EMAIL="email";
    final static String PASSWORD="password";
    String emaila,pass;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);

        cpassword= (EditText) findViewById(R.id.cpassword);
        btnsignup= (Button) findViewById(R.id.btnSignUp);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//validate();
                emaila=email.getText().toString();
                pass=password.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.toString().matches("1")) {
                            Log.d("myrespo", response);
                            showLoginSuceessDialog();

                        } else {
                            registratiofail();
                        }


                        try {
                            // Toast.makeText(getContext(),"Success1",Toast.LENGTH_LONG).show();
                            JSONObject jsonObject = new JSONObject(response);
                            Log.d("responceserver", response);

                            JSONArray jsonArray = jsonObject.getJSONArray("status");
                            Log.d("dataserver", jsonArray.toString());


                            JSONObject jor = jsonArray.getJSONObject(1);
                            String status = jor.getString("success");
                            if (status.equals("success")) {
                                //   Toast.makeText(getApplicationContext(), "Successful Register", Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            Log.e("exception ", "Exception encoutered ");
                            //  Toast.makeText(getContext(),"Exception encoutered ",Toast.LENGTH_LONG);
                            e.printStackTrace();

                        }
                        //JsonArrayRequest jsonArrayRequest=
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), NointernetActivity.class);
                            startActivity(intent);
                        }


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put(EMAIL, emaila);
                        params.put(PASSWORD,pass);
//                        System.out.print("was here");
                        // Toast.makeText(getApplicationContext(),username+"\t"+phone,Toast.LENGTH_LONG).show();
                        Log.e("paramx", "params sent"+emaila+pass);
                       // Log.e("paramx2", username + "\t" + phone);
                        return params;
                    }
                };

                int x = 2;// retry count
                stringRequest.setRetryPolicy(new

                        DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                        x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                //stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volley.newRequestQueue(getApplicationContext()).

                        add(stringRequest);


            }
        });
    }

    private void registratiofail() {
        final AlertDialog dialog = new AlertDialog.Builder(RegisterActivity.this).create();
        dialog.setTitle("Fail");
        dialog.setMessage("Registration failed email already taken");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();



            }
        });
        dialog.setIcon(R.mipmap.warning);
        dialog.show();
    }

    private void showLoginSuceessDialog() {
        pref=getApplicationContext().getSharedPreferences("regd",0);
        SharedPreferences.Editor editor=pref.edit();
       editor.putString("email",emaila);
       editor.putString("phone",pass);
        editor.putString("regstatus","true");

        editor.commit();
        Log.e("dialogme", "showdialogme");
        final AlertDialog dialog = new AlertDialog.Builder(RegisterActivity.this).create();
        dialog.setTitle("Success");
        dialog.setMessage("Registration was successful");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });
        dialog.setIcon(R.mipmap.ic_action_tick);
        dialog.show();
    }


    private boolean validate() {
        if(password.getText().toString().length()<6)

        {
            password.setError("Password should not be less than 6 characters");
            password.requestFocus();
            return false;
        }

        if(!cpassword.getText().toString(). equals(password.getText().toString()))

        {
            cpassword.setError("Password do not match");
            return false;
        }
        return true;
    }

}
