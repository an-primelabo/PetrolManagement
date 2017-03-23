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
import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.ICategoryLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class CategoryService extends CommonService {
	@Autowired
	private ICategoryLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_CATEGORY_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<CategoryResponseDto> select(@RequestBody final CategoryRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_CATEGORY_INSERT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody CategoryResponseDto save(@RequestBody final CategoryRequestDto dto) throws PetrolException {
		return logic.save(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_CATEGORY_UPDATE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody CategoryResponseDto update(@RequestBody final CategoryRequestDto dto) throws PetrolException {
		return logic.update(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_CATEGORY_DELETE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody CategoryResponseDto delete(@RequestBody final CategoryRequestDto dto) throws PetrolException {
		return logic.delete(dto);
	}
}