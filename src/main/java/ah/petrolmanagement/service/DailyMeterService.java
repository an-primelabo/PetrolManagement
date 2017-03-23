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
import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.IDailyMeterLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class DailyMeterService extends CommonService {
	@Autowired
	private IDailyMeterLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_DAILY_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<DailyMeterResponseDto> select(@RequestBody final DailyMeterRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, 
					value = UrlConstants.URL_DAILY_INSERT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody DailyMeterResponseDto save(@RequestBody final DailyMeterRequestDto dto) throws PetrolException {
		return logic.save(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_DAILY_UPDATE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody DailyMeterResponseDto update(@RequestBody final DailyMeterRequestDto dto) throws PetrolException {
		return logic.update(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_DAILY_DELETE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody DailyMeterResponseDto delete(@RequestBody final DailyMeterRequestDto dto) throws PetrolException {
		return logic.delete(dto);
	}
}