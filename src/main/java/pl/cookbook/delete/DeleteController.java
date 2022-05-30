package pl.cookbook.delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteController {

    @GetMapping("/delete-success")
    public String delete() {
        return "delete";
    }

}
