package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.ProductResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class ProductManager {
	private static ProductManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, ItemData> productMap;

	private ProductManager() {
		this.productMap = new HashMap<Integer, ItemData>();
	}

	public static ProductManager getInstance() {
		if (singleton == null) {
			synchronized (_LOCK) {
				if (singleton == null) {
					singleton = new ProductManager();
					singleton.reloadFromDB();
				}
			}
		}
		return singleton;
	}

	public static Integer getCategoryId(final int id) {
		return getInstance().getCateId(id);
	}

	public static List<Integer> getIdList(final int id) {
		return getInstance().getIds(id);
	}

	public static String getProduct(final int id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			} else {
				singleton = new ProductManager();
				return singleton.reloadFromDB();
			}
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.productMap) {
			Map<Integer, ItemData> temp = this.productMap;

			try {
				this.productMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_PRODUCT_SELECT);
				ProductResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, ProductResponseDto[].class);

				if (dataList != null) {
					for (ProductResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getCategoryId(), dto.getProductName());
						this.productMap.put(dto.getId(), item);
					}
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.productMap = temp;
				return false;
			}
		}
	}

	public Integer getCateId(int id) {
		if (this.productMap.get(id) != null) {
			return this.productMap.get(id).getCategoryId();
		} else {
			return id;
		}
	}

	public List<Integer> getIds(int id) {
		List<Integer> list = new ArrayList<Integer>();

		for (Map.Entry<Integer, ItemData> entry : this.productMap.entrySet()) {
			ItemData item = entry.getValue();

			if (item.getCategoryId() == id) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public String getName(int id) {
		if (this.productMap.get(id) != null) {
			return this.productMap.get(id).getProductName();
		} else {
			return id + ApiConstants.BLANK;
		}
	}

	private static class ItemData {
		private Integer categoryId;
		private String productName;

		public ItemData(Integer categoryId, String productName) {
			super();
			this.categoryId = categoryId;
			this.productName = productName;
		}

		public Integer getCategoryId() {
			return categoryId;
		}

		public String getProductName() {
			return productName;
		}
	}
}