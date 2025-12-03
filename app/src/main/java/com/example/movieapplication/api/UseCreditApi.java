package com.example.movieapplication.api;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.movieapplication.R;
import com.example.movieapplication.components.CreditUI;
import com.example.movieapplication.domain.Credit;
import com.example.movieapplication.domain.CreditResponse;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UseCreditApi {
    private TMDBController api;
    private Activity activity;

    public UseCreditApi(Activity activity) {
        this.activity = activity;
        api = TMDBApi.getRetrofit().create(TMDBController.class);
    }

    public void loadCredits(int id, String backdrop){
        List<FlexboxLayout> layouts = Arrays.asList(
                activity.findViewById(R.id.directorContainer),
                activity.findViewById(R.id.castContainer),
                activity.findViewById(R.id.crewContainer),
                activity.findViewById(R.id.effectContainer)
        );
        api.getCredit(id).enqueue(new Callback<CreditResponse>() {
            @Override
            public void onResponse(Call<CreditResponse> call, Response<CreditResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CreditResponse data = response.body();
                    ImageView image = activity.findViewById(R.id.posterImg);
                    CreditUI.setBackdropPath(activity,backdrop,image);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (Credit credit : data.getResults()) {
                                CreditUI.setCreditInfo(activity, credit);
                            }
                            for (FlexboxLayout layout : layouts){
                                CreditUI.setCreditNull(activity, layout);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CreditResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
