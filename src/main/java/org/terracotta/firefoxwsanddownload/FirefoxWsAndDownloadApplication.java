package org.terracotta.firefoxwsanddownload;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class FirefoxWsAndDownloadApplication {


  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private SimpMessagingTemplate template;


  public static void main(String[] args) {
    SpringApplication.run(FirefoxWsAndDownloadApplication.class, args);
  }

  @PostConstruct
  public void sendEventsOverWS() throws Exception {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        sendGreetingToWSClient();
      }
    };
    timer.schedule(timerTask, 1000L, 1000L);
  }

  private void sendGreetingToWSClient() {
    try {
      template.convertAndSend("/topic/greetings",
              objectMapper.readTree("{\"content\":\"Hello, Anthony!\"}"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
