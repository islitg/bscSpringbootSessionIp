import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        return "Hello World - v3! " + sessionId + " " + WebUtils.getClientIp();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }

    @RequestMapping("/a")
    public void newVisitor(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        System.out.println("Hello World - v3! " + sessionId + " " + WebUtils.getClientIp(request));


        String ipAddress = request.getRemoteAddr();
        System.out.println("ip: " + ipAddress);
        WebUtils.getRequestHeadersInMap(request);


        if (request == null || response == null) {
            System.out.println("Request or Response failed for NEWVISITOR METHOD..");
            throw new RuntimeException(
                    "Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
                    new NullPointerException());
        }
    }



}
