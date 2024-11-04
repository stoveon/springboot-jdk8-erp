package com.erp.util.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     구글 Oauth 인증 util
 *     - calendar 서비스 생성
 * </pre>
 */
@Slf4j
public class GoogleAuthUtil {
    /**
     * <pre>
     *     서비스의 이름으로 원하는 이름으로 작성하면 됨.
     * </pre>
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * <pre>
     *     client_secret.json을 구글 API에 맞게 파싱해주는 구글 API 라이브러리에 포함된 JacksonFacotry
     * </pre>
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * <pre>
     *     해당 계정에게 요구되는 권한으로 계정에서 Oauth 동의 화면에서 승인하게 되는 내용
     *     CALENDAR_READONLY는 읽기만 하겠다는 것
     * </pre>
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    private static final String CREDENTIALS_FILE_PATH = "client_secret.json";


    private static final String CALENDAR_ID = "geenieus";

    /**
     * <pre>
     *     DATA_STORE_DIR로 지정된 파일이 없는 경우에 구글 Oauth 인증 절차를 진행하여 해당 파일을 생성하며, 파일이 있는 경우 파일 안의 인증 정보로 인증을 갱신합니다.
     * </pre>
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * <pre>
     *     Google API 통신을 위한 HTTP TRANSPORT
     * </pre>
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * <pre>
     *     OAuth 인증을 이용해 구글에서 인증을 가져온느 메서드
     *     - 앞에서 만든 인증 json을 불러와야 함.
     *     - 이 인증 메서드는 api 요청 때 마다 실행해서 인증정보를 반환함.
     * </pre>
     *
     * @param HTTP_TRANSPORT
     * @return
     * @throws IOException
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        String filePath = File.separator + "static" + File.separator + CREDENTIALS_FILE_PATH;
        InputStream in = GoogleAuthUtil.class.getResourceAsStream(filePath);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + filePath);
        }

        // Build flow and trigger user authorization request.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        //returns an authorized Credential object.
        return credential;
    }

    /**
     * <pre>
     *     구글캘린더에 일정을 추가하는 메서드
     *     - 일정정보를 담기 위해 Event 라는 객체를 사용함.
     *     - Event 생성은 크게 제목, 시작시간, 종료시간, 참여자 정보가 필요함.
     * </pre>
     * @param event
     * @return
     */
    public static Event addEvent(Event event) {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            return service.events().insert(CALENDAR_ID, event).execute();
        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        }
        return null;
    }

    /**
     * <pre>
     *     구글캘린더에 일정을 삭제하는 메서드
     *     - addEvent 했을 때 구글에서 응답받은 eventKey가 필요함.
     * </pre>
     * @param eventKey
     */
    public static void delEvent(String eventKey) {
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            service.events().delete(CALENDAR_ID, eventKey).execute();
        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            // List the next 10 events from the primary calendar.
            DateTime now = new DateTime(System.currentTimeMillis());
            Events events = service.events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            List<Event> items = events.getItems();
            if (items.isEmpty()) {
                log.debug("No upcoming events found.");
            } else {
                log.debug("Upcoming events");
                for (Event event : items) {
                    DateTime start = event.getStart().getDateTime();
                    if (start == null) {
                        start = event.getStart().getDate();
                    }
                    log.debug("{} ({})", event.getSummary(), start);
                }
            }
        } catch (Exception e) {
            log.error("{} | {}", e, e.getMessage());
        }
    }


}
