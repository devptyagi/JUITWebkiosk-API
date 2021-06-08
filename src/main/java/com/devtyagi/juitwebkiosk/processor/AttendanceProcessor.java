package com.devtyagi.juitwebkiosk.processor;

import com.devtyagi.juitwebkiosk.constants.Constants;
import com.devtyagi.juitwebkiosk.exception.InvalidCredentialsException;
import com.devtyagi.juitwebkiosk.model.Attendance;
import com.devtyagi.juitwebkiosk.util.CookieUtility;
import com.devtyagi.juitwebkiosk.util.StringUtility;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendanceProcessor {

    public static List<Attendance> getAttendance(String enrollmentNumber, String password, String url) throws IOException, InvalidCredentialsException {
        List<Attendance> attendanceResult = new ArrayList<>();

        Map<String, String> cookies = CookieUtility.getCookiesFor(enrollmentNumber, password);
        Document document = Jsoup.connect(url)
                .cookies(cookies)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute().parse();

        if (document.body().toString().toLowerCase().contains("session timeout")) {
            throw new InvalidCredentialsException();
        } else {
            Elements elements = document.body()
                    .getElementsByTag("table")
                    .get(2)
                    .getElementsByTag("tbody")
                    .get(0)
                    .children();

            for (Element element : elements) {
                Elements subElements = element.children();

                Attendance attendance = new Attendance();
                attendance.setSubjectName(StringUtility.getSubjectNameFromAttendance(subElements.get(1).text()));
                attendance.setSubjectCode(StringUtility.getSubjectCodeFromAttendance(subElements.get(1).text()));

                attendance.setOverallAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(2).text()));
                attendance.setLectureAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(3).text()));
                attendance.setTutorialAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(4).text()));
                attendance.setPracticalAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(5).text()));

                //Set the link for detailed attendance
                attendance.setDetailAttendanceUrl(Constants.INITIAL_KIOSK_ACADEMIC_URL + subElements.get(2).getElementsByTag("a").attr("href"));

                //Check if the lecture, tutorial and overall attendance is null i.e. it is a practical subject
                if (attendance.getOverallAttendance() == null
                        && attendance.getLectureAttendance() == null
                        && attendance.getTutorialAttendance() == null) {
                    attendance.setOverallAttendance(attendance.getPracticalAttendance());

                    //Check if the url is not null
                    String detailAttendanceUrl = StringUtility.cleanString(subElements.get(5).getElementsByTag("a").attr("href"));

                    if (detailAttendanceUrl != null && detailAttendanceUrl.length() != 0) {
                        attendance.setDetailAttendanceUrl(Constants.INITIAL_KIOSK_ACADEMIC_URL + detailAttendanceUrl);
                    } else {
                        attendance.setDetailAttendanceUrl(null);
                    }
                }

                attendanceResult.add(attendance);
            }
        }

        return attendanceResult;
    }

}
