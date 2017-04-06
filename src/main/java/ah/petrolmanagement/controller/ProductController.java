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
import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.request.ProductPriceRequestDto;
import ah.petrolmanagement.dto.request.ProductRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.utils.LogUtil;

@Controller
public class ProductController extends CommonController {
	private static final String TITLE = "Hạng mục hàng hóa";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { UrlConstants.URL_PRODUCT, UrlConstants.URL_PRODUCT_INDEX }, method = { RequestMethod.GET }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT })
	public String index(final Model map) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "index");

		String checkUser = isAdmin();

		if (StringUtils.isNotBlank(checkUser)) {
			logger.error(checkUser);
			return checkUser;
		}
		map.addAttribute(ApiConstants.REQUEST_TITLE, TITLE);

		LogUtil.endMethod(this.getClass().getSimpleName(), "index");
		return ApiConstants.VIEW_PRODUCT;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_CATEGORY_SELECT }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<CategoryResponseDto> categorySelect(
			@RequestBody final CategoryRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "categorySelect",
				model);

		List<CategoryResponseDto> list = getCategoryList();

		LogUtil.endMethod(this.getClass().getSimpleName(), "categorySelect",
				list);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_CATEGORY_ACTION }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody CategoryResponseDto categoryAction(
			@RequestBody final CategoryRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "categoryAction",
				model);

		CategoryResponseDto response = doCategoryAction(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), "categoryAction",
				response);
		return response;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_SELECT }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody List<ProductResponseDto> productSelect(
			@RequestBody final ProductRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "productSelect",
				model);

		List<ProductResponseDto> list = getProductList(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), "productSelect",
				list);
		return list;
	}

	@RequestMapping(value = { UrlConstants.URL_PRODUCT_ACTION }, method = { RequestMethod.POST }, headers = { UrlConstants.REQUEST_HEADER_ACCEPT_JSON })
	public @ResponseBody ProductResponseDto productAction(
			@RequestBody final ProductRequestDto model, final Model map)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "productAction",
				model);

		ProductResponseDto response = doProductAction(model);

		LogUtil.endMethod(this.getClass().getSimpleName(), "productAction",
				response);
		return response;
	}

	private List<CategoryResponseDto> getCategoryList() {
		List<CategoryResponseDto> list = new ArrayList<CategoryResponseDto>();

		try {
			list = categoryService.select();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private CategoryResponseDto doCategoryAction(CategoryRequestDto request) {
		CategoryResponseDto response = new CategoryResponseDto();

		switch (request.getMode()) {
		case ApiConstants.INSERT:
			try {
				request.setInsUser(getUsernameLoggedIn());
				response = categoryService.save(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.UPDATE:
			try {
				request.setUpdUser(getUsernameLoggedIn());
				response = categoryService.update(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.DELETE:
			try {
				request.setDelUser(getUsernameLoggedIn());
				response = categoryService.delete(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		return response;
	}

	private List<ProductResponseDto> getProductList(ProductRequestDto request) {
		List<ProductResponseDto> list = new ArrayList<ProductResponseDto>();

		try {
			list = productService.selectByCategoryId(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	private ProductResponseDto doProductAction(ProductRequestDto request) {
		ProductResponseDto response = new ProductResponseDto();

		switch (request.getMode()) {
		case ApiConstants.INSERT:
			try {
				request.setInsUser(getUsernameLoggedIn());
				response = productService.save(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.UPDATE:
			try {
				if (request.isUpdateProduct()) {
					request.setUpdUser(getUsernameLoggedIn());
					response = productService.update(request);
				}
				if (request.isUpdatePrice()) {
					ProductPriceRequestDto priceRequest = new ProductPriceRequestDto();
					priceRequest.setId(request.getPriceId());
					priceRequest.setProductId(request.getId());
					priceRequest.setPrice(request.getPrice());
					priceRequest.setInsUser(getUsernameLoggedIn());
					priceRequest.setUpdUser(getUsernameLoggedIn());

					ProductPriceResponseDto priceResponse = priceService.save(priceRequest);
					response.setStatus(priceResponse.getStatus());
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		case ApiConstants.DELETE:
			try {
				request.setDelUser(getUsernameLoggedIn());
				response = productService.delete(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		return response;
	}
}