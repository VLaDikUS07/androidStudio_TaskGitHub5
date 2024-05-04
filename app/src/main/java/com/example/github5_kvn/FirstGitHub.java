package com.example.github5_kvn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstGitHub extends Fragment {
    private String list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_git, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                final Call<List<Contributor>> call =
                        gitHubService.repoContributors("square", "picasso");

                call.enqueue(new Callback<List<Contributor>>() {
                    @Override
                    public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                        final TextView textView = (TextView) view.findViewById(R.id.textView);
                        list = response.body().toString().replaceAll(",", "")
                                .replaceAll("\\[", "")
                                .replaceAll("]", "");
                        textView.setText(list);

                    }

                    @Override
                    public void onFailure(Call<List<Contributor>> call, Throwable throwable) {
                        final TextView textView = (TextView) view.findViewById(R.id.textView);
                        textView.setText("Что-то пошло не так: " + throwable.getMessage());
                    }
                });
            }
        });
        return view;
    }
}
