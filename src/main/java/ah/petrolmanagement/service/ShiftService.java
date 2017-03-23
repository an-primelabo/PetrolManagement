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
import ah.petrolmanagement.dto.request.ShiftRequestDto;
import ah.petrolmanagement.dto.response.ShiftResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.IShiftLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class ShiftService extends CommonService {
	@Autowired
	private IShiftLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_SHIFT_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<ShiftResponseDto> select(@RequestBody final ShiftRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_SHIFT_INSERT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ShiftResponseDto save(@RequestBody final ShiftRequestDto dto) throws PetrolException {
		return logic.save(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_SHIFT_UPDATE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ShiftResponseDto update(@RequestBody final ShiftRequestDto dto) throws PetrolException {
		return logic.update(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_SHIFT_DELETE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ShiftResponseDto delete(@RequestBody final ShiftRequestDto dto) throws PetrolException {
		return logic.delete(dto);
	}
}