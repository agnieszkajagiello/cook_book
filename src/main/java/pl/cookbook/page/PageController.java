package pl.cookbook.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about-me")
    public String aboutMe() {
        return "aboutMe";
    }
}
