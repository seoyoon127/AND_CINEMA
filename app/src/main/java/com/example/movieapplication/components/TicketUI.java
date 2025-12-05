package com.example.movieapplication.components;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.example.movieapplication.api.UseMovieDetailApi;
import com.example.movieapplication.domain.Ticket;
import com.example.movieapplication.utils.DpToPx;
import com.example.movieapplication.utils.FilterTicket;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.List;

public class TicketUI {
    public static void setTicketList(AppCompatActivity activity, List<Ticket> ticketList, boolean recent) {

        // recent 조건에 따라 필터링된 리스트 얻기
        List<Ticket> filtered = FilterTicket.filterTickets(ticketList, recent);

        // 티켓을 붙일 layout
        FlexboxLayout container = activity.findViewById(R.id.ticketContainer);
        container.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(activity);

        for (Ticket t : filtered) {
            View item = inflater.inflate(R.layout.ticket_item, container, false);

            TextView movie = item.findViewById(R.id.movieTitle);
            TextView theater = item.findViewById(R.id.theaterText);
            TextView date = item.findViewById(R.id.dateText);
            TextView seat = item.findViewById(R.id.seatText);
            TextView price = item.findViewById(R.id.priceText);

            UseMovieDetailApi useMovieDetail = new UseMovieDetailApi(activity);
            useMovieDetail.loadMovieName(t.getMovieId(), name -> {
                movie.setText(name);
            });
            theater.setText(t.getTheater()+"점");
            date.setText(t.getDateTime());
            seat.setText(TextUtils.join(", ", t.getSeatList()));
            price.setText("결제금액: " + t.getTotalPrice() + "원");

            container.addView(item);
        }
    }


    public static void setButton(Activity activity, MaterialButton toRed, MaterialButton toWhite) {

        // 선택된 버튼(빨간색)
        toRed.setTextColor(Color.WHITE);
        toRed.setBackgroundTintList(
                ColorStateList.valueOf(Color.parseColor("#EA3F46"))
        );
        toRed.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#EA3F46"))
        );
        toRed.setStrokeWidth(DpToPx.convert(activity, 2));


        // 선택되지 않은 버튼(흰색)
        toWhite.setTextColor(Color.parseColor("#EA3F46"));
        toWhite.setBackgroundTintList(
                ColorStateList.valueOf(Color.WHITE)
        );
        toWhite.setStrokeColor(
                ColorStateList.valueOf(Color.parseColor("#EA3F46"))
        );
        toWhite.setStrokeWidth(DpToPx.convert(activity, 2));
    }
}
