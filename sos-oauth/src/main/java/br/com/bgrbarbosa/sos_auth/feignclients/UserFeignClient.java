package br.com.bgrbarbosa.sos_auth.feignclients;

import br.com.bgrbarbosa.sos_auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "sos-user", path = "/user")
public interface UserFeignClient {

	@GetMapping(value = "/login")
	ResponseEntity<User> findByEmail(@RequestParam String email);
}
