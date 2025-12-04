package com.example.movieapplication.components;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.BookingActivity;
import com.example.movieapplication.R;
import com.example.movieapplication.SeatActivity;
import com.example.movieapplication.data.Theater;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modal {
    public static BottomSheetDialog createTheaterModal(AppCompatActivity activity){
        View bottomSheetView = activity.getLayoutInflater().inflate(R.layout.bottom_modal, null);
        BottomSheetDialog dialog = new BottomSheetDialog(activity);
        dialog.setContentView(bottomSheetView);

        LinearLayout toggleContainer = bottomSheetView.findViewById(R.id.toggleContainer);

        // 액티비티에 연결
        if (activity instanceof BookingActivity) {
            ((BookingActivity) activity).toggleContainer = toggleContainer;
        }

        Map<String, List<String>> regionMap = Theater.getTheaterMap();

        for (String city : regionMap.keySet()) {
            LinearLayout sectionLayout = new LinearLayout(activity);
            sectionLayout.setOrientation(LinearLayout.VERTICAL);
            sectionLayout.setPadding(0, 8, 0, 8);

            Switch cityToggle = new Switch(activity);
            cityToggle.setText(city);
            sectionLayout.addView(cityToggle);

            RadioGroup subRegionGroup = new RadioGroup(activity);
            subRegionGroup.setOrientation(RadioGroup.VERTICAL);
            subRegionGroup.setVisibility(View.GONE);

            for (String district : regionMap.get(city)) {
                RadioButton radio = new RadioButton(activity);
                radio.setText(district);
                subRegionGroup.addView(radio);
            }

            sectionLayout.addView(subRegionGroup);

            cityToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
                subRegionGroup.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (!isChecked) subRegionGroup.clearCheck();
            });

            toggleContainer.addView(sectionLayout);
        }

        return dialog;
    }
}