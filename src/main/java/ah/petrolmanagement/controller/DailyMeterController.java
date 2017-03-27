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
import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.enums.Categories;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.manager.ProductManager;
import ah.petrolmanagement.manager.TankManager;
import ah.petrolmanagement.manager.TankManager.ItemData;
import ah.petrolmanagement.utils.ControllerUtil;
import ah.petrolmanagement.utils.DateUtil;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class DailyMeterController extends CommonController {
	private String responseJson = null;
	private Map<String, Object> mapJson = null;

	@RequestMapping(value = { ApiConstants.BLANK, UrlConstants.URL_DAILY, UrlConstants.URL_DAILY_INDEX },
					method = { RequestMethod.GET },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String index(final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY);

		List<Integer> productIdList = ProductManager.getIdList(Categories.FUEL.getCode());
		List<TankManager.ItemData> tankList = TankManager.getTankList();

		DailyMeterRequestDto dailyRequest = new DailyMeterRequestDto();
		dailyRequest.setInsTime(DateUtil.getToday());
		List<DailyMeterResponseDto> dailies = searchDaily(dailyRequest);

		ProductPriceRequestDto priceRequest = new ProductPriceRequestDto();
		priceRequest.setProductIdList(productIdList);
		List<ProductPriceResponseDto> oldPrice = searchPrice(priceRequest, UrlConstants.URL_PRICE_SELECT_OLD_PRICE);
		List<ProductPriceResponseDto> newPrice = searchPrice(priceRequest, UrlConstants.URL_PRICE_SELECT_NEW_PRICE);

		map.addAttribute("dailies", dailies);
		map.addAttribute("oldPrice", oldPrice);
		map.addAttribute("newPrice", newPrice);
		map.addAttribute("productIdList", productIdList);
		map.addAttribute("tankList", tankList);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY);
		return ApiConstants.VIEW_DAILY;
	}

	@RequestMapping(value = { UrlConstants.URL_DAILY_SEARCH },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<DailyMeterResponseDto> search(
			@RequestBody final DailyMeterRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY_SEARCH);

		List<DailyMeterResponseDto> list = searchDaily(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY_SEARCH);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_DAILY_INSERT_PRICE },
					method = { RequestMethod.POST },
					headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<ProductPriceResponseDto> insertPrice(
			@RequestBody final ProductPriceRequestDto model,
			final Model map) throws PetrolException {
		LogUtil.startMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY_INSERT_PRICE);

		boolean flag = insertPrice(model);

		if (!flag) {
			return null;
		}
		ProductPriceRequestDto request = new ProductPriceRequestDto();
		request.setProductId(model.getProductId());
		List<ProductPriceResponseDto> prices = searchPrice(request, UrlConstants.URL_PRICE_SELECT_PRICE);

		LogUtil.endMethod(this.getClass().getSimpleName(), ApiConstants.VIEW_DAILY_INSERT_PRICE);
		return prices;
	}

	private List<DailyMeterResponseDto> searchDaily(DailyMeterRequestDto request) {
		List<DailyMeterResponseDto> list = new ArrayList<DailyMeterResponseDto>();
		mapJson = new HashMap<String, Object>();

		if (request != null) {
			mapJson.put(DailyMeterRequestDto.SHIFT_ID, request.getShiftId());
			mapJson.put(DailyMeterRequestDto.INS_TIME, request.getInsTime());
		}
		responseJson = ControllerUtil.callAPI(UrlConstants.URL_DAILY_SELECT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			DailyMeterResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, DailyMeterResponseDto[].class);

			if (dataList != null) {
				for (DailyMeterResponseDto daily : dataList) {
					daily.setData();
					list.add(daily);
				}
			}
		}
		return list;
	}

	private List<ProductPriceResponseDto> searchPrice(ProductPriceRequestDto request, String mode) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();
		mapJson = new HashMap<String, Object>();

		if (request != null) {
			mapJson.put(ProductPriceRequestDto.ID, request.getId());
			mapJson.put(ProductPriceRequestDto.PRODUCT_ID, request.getProductId());
			mapJson.put(ProductPriceRequestDto.PRICE, request.getPrice());
			mapJson.put(ProductPriceRequestDto.PRODUCT_ID_LIST, request.getProductIdList());
		}
		if (StringUtils.equals(mode, UrlConstants.URL_PRICE_SELECT_OLD_PRICE)) {
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_PRICE_SELECT_OLD_PRICE, mapJson);
		} else if (StringUtils.equals(mode, UrlConstants.URL_PRICE_SELECT_NEW_PRICE)) {
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_PRICE_SELECT_NEW_PRICE, mapJson);
		} else {
			responseJson = ControllerUtil.callAPI(UrlConstants.URL_PRICE_SELECT_PRICE, mapJson);
		}

		if (StringUtils.isNotBlank(responseJson)) {
			ProductPriceResponseDto[] dataList = ControllerUtil.convertJson2Dto(responseJson, ProductPriceResponseDto[].class);

			if (dataList != null) {
				for (ProductPriceResponseDto price : dataList) {
					list.add(price);
				}
			}
		}
		return list;
	}

	private boolean insertPrice(ProductPriceRequestDto request) {
		boolean flag = false;
		mapJson = new HashMap<String, Object>();
		mapJson.put(ProductPriceRequestDto.ID, request.getId());
		mapJson.put(ProductPriceRequestDto.PRODUCT_ID, request.getProductId());
		mapJson.put(ProductPriceRequestDto.PRICE, request.getPrice());

		ControllerUtil.callAPI(UrlConstants.URL_PRICE_UPDATE, mapJson);
		responseJson = ControllerUtil.callAPI(UrlConstants.URL_PRICE_INSERT, mapJson);

		if (StringUtils.isNotBlank(responseJson)) {
			ProductPriceResponseDto data = ControllerUtil.convertJson2Dto(responseJson, ProductPriceResponseDto.class);

			if (data != null && data.getStatus() == ApiConstants.STATUS_CODE_SUCCESS) {
				flag = true;
			}
		}
		return flag;
	}
}