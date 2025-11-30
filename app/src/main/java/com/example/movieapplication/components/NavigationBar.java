package com.example.movieapplication.components;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.MainActivity;
import com.example.movieapplication.MyPageActivity;
import com.example.movieapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

public class NavigationBar {
    public static void setNavigate(MaterialToolbar toolbar, AppCompatActivity activity,Intent prev) {
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.backBtn) {
                Intent intent = new Intent(activity, prev.getClass());
                Bundle extras = prev.getExtras();
                setExtras(activity, intent,extras);
                activity.startActivity(intent);
            } else if (id == R.id.homeBtn) {
                Intent intent = new Intent(activity, MainActivity.class);
                setExtras(activity, intent,null);
                activity.startActivity(intent);
            } else if (id == R.id.mypageBtn) {
                Intent intent = new Intent(activity, MyPageActivity.class);
                setExtras(activity, intent,null);
                activity.startActivity(intent);
            }
            return false;
        });
    }
    private static void setExtras(Activity activity, Intent intent, Bundle extras){
        intent.putExtra("prevClass", activity.getClass());
        if (extras != null) {
            intent.putExtras(extras);
        }
    }
}
