package br.com.socialfolio.socialfolioapi.demo;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.socialfolio.socialfolioapi.curriculo.CurriculoResponse;
import br.com.socialfolio.socialfolioapi.curriculo.CurriculoService;
import br.com.socialfolio.socialfolioapi.user.UserInfoResponse;
import br.com.socialfolio.socialfolioapi.user.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;
    private final CurriculoService curriculoService;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Boolean> sayHello(){
        System.out.println("Hello World!");
        return ResponseEntity.ok(true);
    }

    @CrossOrigin
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Integer userId){
        System.out.println("ROTA ACESSADA: /api/v1/demo/userinfo/" + userId);
        try {
            var userInfo = userService.extractUserDetails(userId);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            System.out.println("Erro ao obter dados: " + e);
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @GetMapping("/curriculo/{userId}")
    public ResponseEntity<CurriculoResponse> getUserCurriculum(@PathVariable Integer userId){
        System.out.println("ROTA ACESSADA: /api/v1/demo/curriculo/" + userId);
        try {
            var curriculoInfo = curriculoService.extractCurriculoDetails(userId);
            return ResponseEntity.ok(curriculoInfo);
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao obter dados: " + e);
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            System.out.println("Erro ao obter dados: " + e);
            return ResponseEntity.badRequest().build();
        }
    }
}
