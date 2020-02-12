package com.example.proyectoandroid.API;

import com.example.proyectoandroid.models.LoginState;
import com.example.proyectoandroid.models.PerfilActividades;
import com.example.proyectoandroid.models.Question;
import com.example.proyectoandroid.models.QuestionTags;
import com.example.proyectoandroid.models.Tag;
import com.example.proyectoandroid.models.User;
import com.example.proyectoandroid.models.UsuarioTag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginState> postlogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("registro.php")
    Call<LoginState> registrologin(
            @Field("nombre") String nombre,
            @Field("email") String email,
            @Field("password") String password,
            @Field("tags[]") List<Integer> tags
    );

    @GET("usuarios.php")
    Call<List<UsuarioTag>> getusuarios(@Query("type") String type);


    @FormUrlEncoded
    @POST("perfil.php")
    Call<PerfilActividades> getperfil(@Field("email") String email);


    @GET("tags.php")
    Call<List<Tag>> gettags();

    @FormUrlEncoded
    @POST("preguntas.php")
    Call<List<QuestionTags>> getPreguntas(
            @Field("order") String order,
            @Field("email") String email
    );
}
