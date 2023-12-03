package br.com.socialfolio.socialfolioapi.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Boolean> sayHello(){
        System.out.println("Hello World!");
        return ResponseEntity.ok(true);
    }
}
