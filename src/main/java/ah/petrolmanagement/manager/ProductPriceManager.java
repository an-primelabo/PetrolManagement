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

	public static List<ItemData> getPriceList() {
		return getInstance().getList();
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
			}
			singleton = new ProductPriceManager();
			return singleton.reloadFromDB();
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.priceMap) {
			Map<Integer, ItemData> temp = this.priceMap;

			try {
				this.priceMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_API_PRICE_SELECT);
				ProductPriceResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, ProductPriceResponseDto[].class);

				if (dataList != null) {
					for (ProductPriceResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getId(), dto.getProductId(), dto.getPrice());
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

	public List<ItemData> getList() {
		List<ItemData> list = new ArrayList<ItemData>();

		if (this.priceMap != null) {
			list = new ArrayList<ItemData>(this.priceMap.values());
		}
		return list;
	}

	public Integer getProId(int id) {
		if (this.priceMap.get(id) != null) {
			return this.priceMap.get(id).getProductId();
		}
		return id;
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
		}
		return id;
	}

	public static class ItemData {
		private Integer id;
		private Integer productId;
		private Integer price;

		public ItemData(Integer id, Integer productId, Integer price) {
			super();
			this.id = id;
			this.productId = productId;
			this.price = price;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getProductId() {
			return productId;
		}

		public void setProductId(Integer productId) {
			this.productId = productId;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}
	}
}