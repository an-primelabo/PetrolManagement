package ah.petrolmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.utils.ControllerUtil;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class ProductController extends CommonController {
	private static final String TITLE = "Hạng mục hàng hóa";
	private String responseJson = null;
	private Map<String, Object> mapJson = null;

	@RequestMapping(value = { UrlConstants.URL_PRODUCT, UrlConstants.URL_PRODUCT_INDEX },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String index(final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT);

		map.addAttribute("title", TITLE);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT);
		return ApiConstants.VIEW_PRODUCT;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_CATEGORY_SELECT },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<CategoryResponseDto> categorySelect(
			@RequestBody final CategoryRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_CATEGORY_SELECT, model);

		List<CategoryResponseDto> list = getCategoryList(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_CATEGORY_SELECT, model);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_CATEGORY_ACTION },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody CategoryResponseDto categoryAction(
			@RequestBody final CategoryRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_CATEGORY_ACTION, model);

		CategoryResponseDto response = doCategoryAction(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_CATEGORY_ACTION, model);
		return response;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_SELECT },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<ProductResponseDto> productSelect(
			@RequestBody final ProductRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_SELECT, model);

		List<ProductResponseDto> list = getProductList(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_SELECT, model);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_ACTION },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody ProductResponseDto productAction(
			@RequestBody final ProductRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_ACTION, model);

		ProductResponseDto response = doProductAction(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_PRODUCT_ACTION, model);
		return response;
	}

	private List<CategoryResponseDto> getCategoryList(CategoryRequestDto request) {
		List<CategoryResponseDto> list = new ArrayList<CategoryResponseDto>();
		mapJson = new HashMap<String, Object>();
		mapJson.put(CategoryRequestDto.ID, request.getId());

		responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_CATEGORY_SELECT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			CategoryResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, CategoryResponseDto[].class);

			if (dataList != null) {
				for (CategoryResponseDto category : dataList) {
					list.add(category);
				}
			}
		}
		return list;
	}

	private CategoryResponseDto doCategoryAction(CategoryRequestDto request) {
		CategoryResponseDto response = new CategoryResponseDto();
		mapJson = new HashMap<String, Object>();
		mapJson.put(CategoryRequestDto.ID, request.getId());
		mapJson.put(CategoryRequestDto.CATEGORY_NAME, request.getCategoryName());

		switch (request.getMode()) {
		case ApiConstants.INSERT:
			mapJson.put(CategoryRequestDto.INS_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_CATEGORY_INSERT, mapJson);
			break;
		case ApiConstants.UPDATE:
			mapJson.put(CategoryRequestDto.UPD_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_CATEGORY_UPDATE, mapJson);
			break;
		case ApiConstants.DELETE:
			mapJson.put(CategoryRequestDto.DEL_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_CATEGORY_DELETE, mapJson);
			break;
		}
		if (StringUtils.isNotBlank(responseJson)) {
			response = ControllerUtil.convertJson2Dto(responseJson, CategoryResponseDto.class);
		}
		return response;
	}

	private List<ProductResponseDto> getProductList(ProductRequestDto request) {
		List<ProductResponseDto> list = new ArrayList<ProductResponseDto>();
		mapJson = new HashMap<String, Object>();
		mapJson.put(ProductRequestDto.CATEGORY_ID, request.getCategoryId());

		responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_PRODUCT_SELECT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			ProductResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, ProductResponseDto[].class);

			if (dataList != null) {
				for (ProductResponseDto product : dataList) {
					list.add(product);
				}
			}
		}
		return list;
	}

	private ProductResponseDto doProductAction(ProductRequestDto request) {
		ProductResponseDto response = new ProductResponseDto();
		mapJson = new HashMap<String, Object>();
		mapJson.put(ProductRequestDto.ID, request.getId());
		mapJson.put(ProductRequestDto.CATEGORY_ID, request.getCategoryId());
		mapJson.put(ProductRequestDto.PRODUCT_NAME, request.getProductName());

		switch (request.getMode()) {
		case ApiConstants.INSERT:
			mapJson.put(ProductRequestDto.INS_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_PRODUCT_INSERT, mapJson);
			break;
		case ApiConstants.UPDATE:
			mapJson.put(ProductRequestDto.UPD_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_PRODUCT_UPDATE, mapJson);
			break;
		case ApiConstants.DELETE:
			mapJson.put(ProductRequestDto.DEL_USER, getPrincipal());
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_PRODUCT_DELETE, mapJson);
			break;
		}
		if (StringUtils.isNotBlank(responseJson)) {
			response = ControllerUtil.convertJson2Dto(responseJson, ProductResponseDto.class);
		}
		return response;
	}

	private List<ProductPriceResponseDto> getProductPriceList(List<Integer> productIdList, Integer selectTop) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();
		mapJson = new HashMap<String, Object>();

		if (productIdList != null && !productIdList.isEmpty()) {
			mapJson.put(ProductPriceRequestDto.PRODUCT_ID_LIST, productIdList);
			mapJson.put(ProductPriceRequestDto.SELECT_TOP, selectTop);
		}
		responseJson = ControllerUtil.callAPI(UrlConstants.URL_API_PRICE_SELECT_PRICE, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			ProductPriceResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, ProductPriceResponseDto[].class);

			if (dataList != null) {
				for (ProductPriceResponseDto price : dataList) {
					price.setData();
					list.add(price);
				}
			}
		}
		return list;
	}
}