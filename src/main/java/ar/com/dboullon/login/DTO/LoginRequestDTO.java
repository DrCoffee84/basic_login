package ar.com.dboullon.login.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class LoginRequestDTO {
	private String username;
	private String password;
}
