package timetracker_backend.timetracker_backend.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TimeController {
    
    @GetMapping
    public String getIndex() {
        return "{'message': 'Hello, world!'}";
    }
    

}
