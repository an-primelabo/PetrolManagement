package ah.petrolmanagement.service;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.IUserLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class UserService extends CommonService {
	@Autowired
	private IUserLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_API_USER_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<UserResponseDto> select(@RequestBody final UserRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_API_USER_INSERT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody UserResponseDto save(@RequestBody final UserRequestDto dto) throws PetrolException {
		return logic.save(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_API_USER_UPDATE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody UserResponseDto update(@RequestBody final UserRequestDto dto) throws PetrolException {
		return logic.update(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_API_USER_DELETE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody UserResponseDto delete(@RequestBody final UserRequestDto dto) throws PetrolException {
		return logic.delete(dto);
	}
}