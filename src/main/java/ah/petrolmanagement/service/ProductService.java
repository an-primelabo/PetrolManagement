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
import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.IProductLogic;

@Controller
@Scope(value = SCOPE_REQUEST)
public class ProductService extends CommonService {
	@Autowired
	private IProductLogic logic;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_PRODUCT_SELECT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody List<ProductResponseDto> select(@RequestBody final ProductRequestDto dto) throws PetrolException {
		return logic.select(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_PRODUCT_INSERT,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ProductResponseDto save(@RequestBody final ProductRequestDto dto) throws PetrolException {
		return logic.save(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_PRODUCT_UPDATE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ProductResponseDto update(@RequestBody final ProductRequestDto dto) throws PetrolException {
		return logic.update(dto);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST,
					value = UrlConstants.URL_PRODUCT_DELETE,
					headers = UrlConstants.REQUEST_HEADER_ACCEPT_JSON)
	public @ResponseBody ProductResponseDto delete(@RequestBody final ProductRequestDto dto) throws PetrolException {
		return logic.delete(dto);
	}
}