package ah.petrolmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.enums.Categories;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class DailyMeterController extends CommonController {
	private static final String TITLE = "Số liệu xăng dầu hằng ngày";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { UrlConstants.URL_DAILY, UrlConstants.URL_DAILY_INDEX }, method = { RequestMethod.GET }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String index(final Model map) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "index");

		String checkUser = checkUserLoggedIn();

		if (StringUtils.isNotBlank(checkUser)) {
			logger.error(checkUser);
			return checkUser;
		}
		ProductPriceRequestDto request = new ProductPriceRequestDto();
		request.setCategoryId(Categories.FUEL.getCode());

		List<ProductPriceResponseDto> priceList = getPriceList(request);
		List<TankResponseDto> tankList = getTankList();

		map.addAttribute("priceList", priceList);
		map.addAttribute("tankList", tankList);
		map.addAttribute(ApiConstants.REQUEST_TITLE, TITLE);

		LogUtil.endMethod(this.getClass().getSimpleName(), "index", priceList);
		return ApiConstants.VIEW_DAILY;
	}

	@RequestMapping(value = { UrlConstants.URL_DAILY_SELECT }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<DailyMeterResponseDto> dailySelect(
			@RequestBody final DailyMeterRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "dailySelect",
				model);

		List<DailyMeterResponseDto> list = getDailyList(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), "dailySelect", list);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_DAILY_ACTION }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody DailyMeterResponseDto dailyAction(
			@RequestBody final DailyMeterRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "dailyAction",
				model);

		DailyMeterResponseDto response = doDailyAction(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), "dailyAction",
				response);
		return response;
	}

	private List<ProductPriceResponseDto> getPriceList(
			ProductPriceRequestDto request) {
		List<ProductPriceResponseDto> list = new ArrayList<ProductPriceResponseDto>();

		try {
			list = priceService.selectNewestPrice(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private List<TankResponseDto> getTankList() {
		List<TankResponseDto> list = new ArrayList<TankResponseDto>();

		try {
			list = tankService.selectFuelTanks();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private List<DailyMeterResponseDto> getDailyList(
			DailyMeterRequestDto request) {
		List<DailyMeterResponseDto> list = new ArrayList<DailyMeterResponseDto>();

		try {
			list = dailyService.select(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private DailyMeterResponseDto doDailyAction(DailyMeterRequestDto request) {
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		switch (request.getMode()) {
		case ApiConstants.INSERT:
			try {
				request.setInsUser(getUsernameLoggedIn());
				response = dailyService.save(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.UPDATE:
			try {
				request.setUpdUser(getUsernameLoggedIn());
				response = dailyService.update(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.DELETE:
			try {
				request.setDelUser(getUsernameLoggedIn());
				response = dailyService.delete(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		return response;
	}
}