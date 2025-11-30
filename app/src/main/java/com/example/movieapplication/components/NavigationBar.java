package com.example.movieapplication.components;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

public class NavigationBar {
    public static void setNavigate(MaterialToolbar toolbar, AppCompatActivity activity, Class<?> prev, Class<?> home, Class<?> myPage) {
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.backBtn) {
                Intent intent = new Intent(activity, prev);
                intent.putExtra("prevClass", activity.getClass());
                activity.startActivity(intent);
            } else if (id == R.id.homeBtn) {
                Intent intent = new Intent(activity, home);
                intent.putExtra("prevClass", activity.getClass());
                activity.startActivity(intent);
            } else if (id == R.id.mypageBtn) {
                Intent intent = new Intent(activity, myPage);
                intent.putExtra("prevClass", activity.getClass());
                activity.startActivity(intent);
            }
            return false;
        });
    }
}
