package ar.com.dboullon.login.DTO;

import lombok.Data;
import java.util.List;

@Data
public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponseDTO(String accessToken, String username) {
        this.token = accessToken;
        this.username = username;
    }
}
