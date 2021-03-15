package com.cech.loginapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cech.loginapp.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("data");

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        ButterKnife.bind(this);

        Picasso.with(this).load(data[0]).into(binding.imageView);
        binding.titleTextView.setText(data[1]);
        binding.longDescTextView.setText(data[2]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.longDescTextView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}
