package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        EditText email = findViewById(R.id.email);
        EditText pswrd = findViewById(R.id.pswrd);

        //ретрофит-клиент
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Zapros zapros = new Zapros(email.getText().toString(),
                        pswrd.getText().toString());
                Call<Otvet> call = api.auth(zapros);
                call.enqueue(new Callback<Otvet>() {
                    @Override
                    public void onResponse(Call<Otvet> call, Response<Otvet> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                    response.code()+"",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Otvet otvet = response.body();
                        Toast.makeText(getApplicationContext(),
                                otvet.token, Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onFailure(Call<Otvet> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }


}