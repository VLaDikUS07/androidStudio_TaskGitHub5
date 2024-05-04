package com.example.github5_kvn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FourthGitHub extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_git, container, false);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        TextView textView = view.findViewById(R.id.textView);
        EditText usernameEnter = view.findViewById(R.id.usernameEnter);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.INVISIBLE);

                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);

                final Call<GitResult> call = gitHubService.getUsers(String.valueOf(usernameEnter.getText()));

                call.enqueue(new Callback<GitResult>() {
                    @Override
                    public void onResponse(Call<GitResult> call, Response<GitResult> response) {
                        // response.isSuccessful() is true if the response code is 2xx
                        if (response.isSuccessful()) {
                            textView.setVisibility(View.VISIBLE);
                            GitResult result = response.body();

                            // Получаем json из github-сервера и конвертируем его в удобный вид
                            // Покажем только первого пользователя
                            String user = "Аккаунт Github: " + result.getItems().get(0).getLogin();
                            textView.setText(user);
                            Log.i("Git", String.valueOf(result.getItems().size()));

                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            int statusCode = response.code();

                            // handle request errors yourself
                            ResponseBody errorBody = response.errorBody();
                            try {
                                textView.setText(errorBody.string());
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GitResult> call, Throwable throwable) {
                        textView.setText("Что-то пошло не так: " + throwable.getMessage());
                    }
                });
            }
        });
        return view;
    }
}