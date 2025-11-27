package com.example.movieapplication.components;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

public class NavigationBar {
    public static void setNavigate(MaterialToolbar toolbar, AppCompatActivity activity) {
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.backBtn) {
                Toast.makeText(activity, "뒤로가기 클릭", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.homeBtn) {
                Toast.makeText(activity, "홈 클릭", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.mypageBtn) {
                Toast.makeText(activity, "마이페이지 클릭", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}
