package com.example.twapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twapp.Login.MyAPIService;
import com.example.twapp.Login.RetrofitManager;
import com.example.twapp.Login.UserData;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAcount extends AppCompatActivity {
    TextView account,password,telephone,name;
    MyAPIService myAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);
        Button btn_create=findViewById(R.id.create_AccountButton);
        account=findViewById(R.id.create_AccountInput);
        password=findViewById(R.id.create_PasswordInput);
        telephone=findViewById(R.id.create_TelephoneInput);
        name=findViewById(R.id.create_NameInput);
        myAPIService= RetrofitManager.getInstance().getAPI();
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().length()<5||password.getText().length()>13|| !password.getText().toString().matches("[a-zA-Z0-9|\\\\.]*")){
                    Toast.makeText(CreateAcount.this,"Password has Error Format Please check the input",Toast.LENGTH_LONG).show();
                }else{
                    UserData userData=new UserData(account.getText().toString(),password.getText().toString(),name.getText().toString(),telephone.getText().toString());
                    Call<UserData> call=myAPIService.createUser(userData);
                    call.enqueue(new Callback<UserData>() {
                        @Override
                        public void onResponse(Call<UserData> call, Response<UserData> response) {
                            if(response.code()==200){
                                Toast.makeText(CreateAcount.this,"create successful",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<UserData> call, Throwable t) {


                                Toast.makeText(CreateAcount.this,"account already exist",Toast.LENGTH_LONG).show();
                            }



                    });
                }
            }
        });
    }

}