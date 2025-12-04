package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.components.BookUI;
import com.example.movieapplication.components.Modal;
import com.example.movieapplication.components.NavigationBar;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BookingActivity extends AppCompatActivity {

    public LinearLayout toggleContainer;
    private BottomSheetDialog bottomSheetDialog;
    private String theaterSelected;
    private String dateSelected;
    public String timeSelected;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Intent prevIntent = getIntent();
        id = prevIntent.getIntExtra("id", 0);

        MaterialToolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("예매하기");

        NavigationBar.setNavigate(toolbar, this, null);

        // 영화 정보 UI
        LinearLayout movieContainer = findViewById(R.id.movieContainer);
        UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(this);
        useMovieDetail.loadMovieInfo(id, movieContainer);

        // 영화관 모달
        bottomSheetDialog = Modal.createTheaterModal(this);

        Button bookButton = findViewById(R.id.bookButton);
        bookButton.setOnClickListener(v -> bottomSheetDialog.show());

        // 날짜 선택
        FlexboxLayout dateContainer = findViewById(R.id.dateContainer);
        BookUI.setDate(this, dateContainer, selectedDate -> this.dateSelected = selectedDate);

        // 시간 선택
        FlexboxLayout timeContainer = findViewById(R.id.timeContainer);
        useMovieDetail.loadMovieTime(id, timeContainer);
        BookUI.setTimeListener(timeContainer, selectedTime -> this.timeSelected = selectedTime);
    }

    public void clickSave(View view){
        // toggleContainer에서 선택된 영화관 읽기
        for (int i = 0; i < toggleContainer.getChildCount(); i++) {
            LinearLayout sectionLayout = (LinearLayout) toggleContainer.getChildAt(i);
            Switch cityToggle = (Switch) sectionLayout.getChildAt(0);
            RadioGroup subRegionGroup = (RadioGroup) sectionLayout.getChildAt(1);

            if (cityToggle.isChecked()) {
                int selectedId = subRegionGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    theaterSelected = ((RadioButton)subRegionGroup.findViewById(selectedId)).getText().toString();
                    break;
                }
            }
        }

        if (theaterSelected != null) {
            TextView theaterText = findViewById(R.id.theaterText);
            theaterText.setText("영화관: " + theaterSelected);
        }

        bottomSheetDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_bar, menu);
        return true;
    }

    public void moveToSeat(View view){
        Intent intent = new Intent(this, SeatActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("theater", theaterSelected);
        intent.putExtra("date", dateSelected);
        intent.putExtra("time", timeSelected);
        Log.d("BookingActivity", "theaterSelected: " + theaterSelected);
        Log.d("BookingActivity", "dateSelected: " + dateSelected);
        Log.d("BookingActivity", "timeSelected: " + timeSelected);

        startActivity(intent);
    }
}
