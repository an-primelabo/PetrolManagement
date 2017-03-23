package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.ProductPriceResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class ProductPriceManager {
	private static ProductPriceManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, ItemData> priceMap;

	private ProductPriceManager() {
		this.priceMap = new HashMap<Integer, ItemData>();
	}

	public static ProductPriceManager getInstance() {
		if (singleton == null) {
			synchronized (_LOCK) {
				if (singleton == null) {
					singleton = new ProductPriceManager();
					singleton.reloadFromDB();
				}
			}
		}
		return singleton;
	}

	public static Integer getProductId(final int id) {
		return getInstance().getProId(id);
	}

	public static List<Integer> getIdList(final int id) {
		return getInstance().getIds(id);
	}

	public static Integer getPrice(final int id) {
		return getInstance().getProPrice(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			} else {
				singleton = new ProductPriceManager();
				return singleton.reloadFromDB();
			}
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.priceMap) {
			Map<Integer, ItemData> temp = this.priceMap;

			try {
				this.priceMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_PRICE_SELECT);
				ProductPriceResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, ProductPriceResponseDto[].class);

				if (dataList != null) {
					for (ProductPriceResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getProductId(), dto.getPrice());
						this.priceMap.put(dto.getId(), item);
					}
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.priceMap = temp;
				return false;
			}
		}
	}

	public Integer getProId(int id) {
		if (this.priceMap.get(id) != null) {
			return this.priceMap.get(id).getProductId();
		} else {
			return id;
		}
	}

	public List<Integer> getIds(int id) {
		List<Integer> list = new ArrayList<Integer>();

		for (Map.Entry<Integer, ItemData> entry : this.priceMap.entrySet()) {
			ItemData item = entry.getValue();

			if (item.getProductId() == id) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public Integer getProPrice(int id) {
		if (this.priceMap.get(id) != null) {
			return this.priceMap.get(id).getPrice();
		} else {
			return id;
		}
	}

	private static class ItemData {
		private Integer productId;
		private Integer price;

		public ItemData(Integer productId, Integer price) {
			super();
			this.productId = productId;
			this.price = price;
		}

		public Integer getProductId() {
			return productId;
		}

		public Integer getPrice() {
			return price;
		}
	}
}