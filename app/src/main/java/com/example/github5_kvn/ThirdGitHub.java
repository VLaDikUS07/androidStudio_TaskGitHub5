package com.example.github5_kvn;

import android.os.Bundle;
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

public class ThirdGitHub extends Fragment {
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

                final Call<List<Repos>> call = gitHubService.getRepos(String.valueOf(usernameEnter.getText()));

                call.enqueue(new Callback<List<Repos>>() {
                    @Override
                    public void onResponse(Call<List<Repos>> call, Response<List<Repos>> response) {
                        // response.isSuccessfull() is true if the response code is 2xx
                        if (response.isSuccessful()) {
                            // Выводим массив имён
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(response.body().toString() + "\n");
                            for (int i = 0; i < response.body().size(); i++) {
                                // Выводим имена по отдельности
                                textView.append(response.body().get(i).getName() + "\n");
                            }

                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            int statusCode = response.code();
                            // Обрабатываем ошибку
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
                    public void onFailure(Call<List<Repos>> call, Throwable throwable) {
                        textView.setText("Что-то пошло не так: " + throwable.getMessage());
                    }
                });
            }
        });
        return view;
    }
}