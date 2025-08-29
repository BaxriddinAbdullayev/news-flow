package novares.uz.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import novares.uz.domain.auth.User;
import novares.uz.dto.AppResponse;
import novares.uz.dto.auth.auth.LoginResDto;
import novares.uz.dto.auth.auth.LoginReqDto;
import novares.uz.dto.auth.auth.RefreshReqDto;
import novares.uz.dto.auth.auth.RefreshResDto;
import novares.uz.enums.GeneralStatus;
import novares.uz.exps.AppBadException;
import novares.uz.mapper.auth.UserMapper;
import novares.uz.repository.auth.UserRepository;
import novares.uz.service.message.ResourceBundleService;
import novares.uz.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ResourceBundleService bundleService;
    private final JwtUtil jwtUtil;

    public AppResponse<LoginResDto> login(LoginReqDto dto) {
        User user = getUserIfExists(dto.getUsername());
        if (!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AppBadException(bundleService.getMessage("password.wrong"));
        }
        checkUserStatusOrThrow(user.getStatus(), GeneralStatus.ACTIVE);

        LoginResDto resDto = new LoginResDto(
                jwtUtil.generateAccessToken(user.getId()),
                jwtUtil.generateRefreshToken(user.getId()),
                jwtUtil.getAccessTokenExpirationSeconds()
        );
        return AppResponse.success(resDto, bundleService.getMessage("login.success"));
    }

    private User getUserIfExists(String username) {
        Optional<User> optional = userRepository.findByUsernameAndActiveTrueAndDeletedFalse(username);
        if (optional.isEmpty()) {
            throw new AppBadException(bundleService.getMessage("user.not.found"));
        }
        return optional.get();
    }

    private void checkUserStatusOrThrow(GeneralStatus userStatus, GeneralStatus status) {
        if (!userStatus.equals(status)) {
            throw new AppBadException(bundleService.getMessage("status.wrong"));
        }
    }

    public AppResponse<RefreshResDto> refreshToken(RefreshReqDto dto) {

        if (!jwtUtil.validateRefreshToken(dto.getRefreshToken())) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        String username = jwtUtil.extractUsername(dto.getRefreshToken());

        RefreshResDto refreshResDto = new RefreshResDto(
                jwtUtil.generateAccessToken(Long.valueOf(username)),
                jwtUtil.getAccessTokenExpirationSeconds()
        );

        return AppResponse.success(refreshResDto, bundleService.getMessage("refresh.success"));
    }

}
