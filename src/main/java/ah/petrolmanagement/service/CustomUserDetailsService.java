package ah.petrolmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String responseJson = null;
	private Map<String, Object> mapJson = null;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		mapJson = new HashMap<String, Object>();
		mapJson.put("username", username);

		responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_USER_SELECT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			UserResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, UserResponseDto[].class);

			if (dataList != null) {
				for (UserResponseDto user : dataList) {
					return new User(user.getUsername(), user.getPassword(),
							true, true, true, true, getGrantedAuthorities(username));
				}
			}
		}
		throw new UsernameNotFoundException("Username not found");
	}

	private List<GrantedAuthority> getGrantedAuthorities(String username) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		mapJson = new HashMap<String, Object>();
		mapJson.put("username", username);

		responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_ROLE_SELECT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			RoleResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, RoleResponseDto[].class);

			if (dataList != null) {
				for (RoleResponseDto role : dataList) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
				}
			}
		}
		return authorities;
	}
}