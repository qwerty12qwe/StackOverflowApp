package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoandroid.API.LoginApi;
import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MakeQuestion extends AppCompatActivity {

    private RecyclerView reci;
    private ReciclerViewAdapterTagSelector tags;
    private Button preguntar;
    private EditText titulo,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_question);

        reci = findViewById(R.id.question_tags_selector);
        reci.setLayoutManager(new LinearLayoutManager(this));

        preguntar = findViewById(R.id.make_question_btnpreguntar);
        titulo = findViewById(R.id.make_question_titulo);
        descripcion = findViewById(R.id.make_question_descripcion);

        loadJSON();

//

    }

    private void loadJSON() {

        Retrofit retrofit = Login.getretrofit();

        LoginApi restClient = retrofit.create(LoginApi.class);
        Call<List<Tag>> call = restClient.gettags();

        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                List<Tag> data = response.body();
                tags = new ReciclerViewAdapterTagSelector(data);
                reci.setAdapter(tags);
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                Toast.makeText(MakeQuestion.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
