package com.cech.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.cech.loginapp.databinding.ActivityMainBinding;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<DataIO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);

        RetrofitConfig config = new RetrofitConfig();

        config.getMockableService().buscar().enqueue(new Callback<List<DataIO>>() {

            @Override
            public void onResponse(Call<List<DataIO>> call, Response<List<DataIO>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();
                    return;
                }

                MainActivity.this.list = response.body();
                binding.recyclerView.setAdapter(
                        new Adapter(
                                MainActivity.this,
                                MainActivity.this.list,
                                new CustomClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        long postId = MainActivity.this.list.get(position).getId();

                                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);

                                        String[] message =
                                                new String[] {
                                                        MainActivity.this.list.get(position).getUrl(),
                                                        MainActivity.this.list.get(position).getTitle(),
                                                        MainActivity.this.list.get(position).getLong_desc()
                                                };
                                        intent.putExtra("data", message);

                                        startActivity(intent);
                                    }
                                }
                        ));
                binding.recyclerView.setLayoutManager(
                        new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.VERTICAL,
                                false));

                // Fade in animation on constraint layout
                Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                binding.ConstraintLayoutRecyclerView.startAnimation(animFadeIn);
            }

            @Override
            public void onFailure(Call<List<DataIO>> call, Throwable t) { }
        });
    }
}