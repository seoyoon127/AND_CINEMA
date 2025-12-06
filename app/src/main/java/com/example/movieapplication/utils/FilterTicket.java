package com.example.movieapplication.utils;

import com.example.movieapplication.domain.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FilterTicket {

    public static List<Ticket> filterTickets(List<Ticket> ticketList, boolean recent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        // 오늘 날짜(00:00:00)
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();

        return ticketList.stream()
                .filter(t -> {
                    try {
                        String dateStr = t.getDateTime().split(" ")[0]; // yyyy-MM-dd
                        Date ticketDate = sdf.parse(dateStr);
                        if (ticketDate == null) return false;

                        if (recent) {
                            // 오늘 이후(포함)만
                            return !ticketDate.before(today);
                        } else {
                            // 오늘 이전만
                            return ticketDate.before(today);
                        }

                    } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}