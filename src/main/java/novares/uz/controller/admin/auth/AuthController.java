package novares.uz.controller.admin.auth;

import lombok.RequiredArgsConstructor;
import novares.uz.dto.AppResponse;
import novares.uz.dto.auth.auth.LoginReqDto;
import novares.uz.dto.auth.auth.LoginResDto;
import novares.uz.dto.auth.auth.RefreshReqDto;
import novares.uz.dto.auth.auth.RefreshResDto;
import novares.uz.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class AuthController {

    private final AuthService service;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<LoginResDto>> login(@RequestBody LoginReqDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.POST)
    public ResponseEntity<AppResponse<RefreshResDto>> refresh(@RequestBody RefreshReqDto dto) {
        return ResponseEntity.ok(service.refreshToken(dto));
    }
}
