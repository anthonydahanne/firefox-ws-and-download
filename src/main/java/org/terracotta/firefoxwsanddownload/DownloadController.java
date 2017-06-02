package org.terracotta.firefoxwsanddownload;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class DownloadController {

    @GetMapping(value = "/download", produces = "text/plain" )
    public String downloadFile(HttpServletResponse response) throws Exception {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"" + String.join("-", "download", now()) + ".txt\"");
        return "Say Bye bye to Web Sockets !";
    }


    private String now() {
        return LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}