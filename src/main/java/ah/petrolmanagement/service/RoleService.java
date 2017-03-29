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
import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.IRoleLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class RoleService extends CommonService {
	@Autowired
	private IRoleLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_API_ROLE_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<RoleResponseDto> select(@RequestBody final RoleRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}
}