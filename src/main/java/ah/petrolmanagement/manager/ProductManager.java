package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static List<ItemData> getProductList() {
		return getInstance().getList();
	}

	public static Integer getCategoryId(final Integer id) {
		return getInstance().getCateId(id);
	}

	public static List<Integer> getIdList(final Integer id) {
		return getInstance().getIds(id);
	}

	public static String getProduct(final Integer id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			}
			singleton = new ProductManager();
			return singleton.reloadFromDB();
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.productMap) {
			Map<Integer, ItemData> temp = this.productMap;

			try {
				this.productMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_API_PRODUCT_SELECT);
				ProductResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, ProductResponseDto[].class);

				if (dataList != null) {
					for (ProductResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getId(), dto.getCategoryId(), dto.getProductName());
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

	public List<ItemData> getList() {
		List<ItemData> list = new ArrayList<ItemData>();

		if (this.productMap != null) {
			list = new ArrayList<ItemData>(this.productMap.values());
		}
		return list;
	}

	public Integer getCateId(Integer id) {
		if (this.productMap.get(id) != null) {
			return this.productMap.get(id).getCategoryId();
		}
		return id;
	}

	public List<Integer> getIds(Integer id) {
		List<Integer> list = new ArrayList<Integer>();

		for (Map.Entry<Integer, ItemData> entry : this.productMap.entrySet()) {
			ItemData item = entry.getValue();

			if (item.getCategoryId() == id) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public String getName(Integer id) {
		if (this.productMap.get(id) != null) {
			return this.productMap.get(id).getProductName();
		}
		return id.toString();
	}

	public static class ItemData {
		private Integer id;
		private Integer categoryId;
		private String productName;

		public ItemData(Integer id, Integer categoryId, String productName) {
			super();
			this.id = id;
			this.categoryId = categoryId;
			this.productName = productName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Integer categoryId) {
			this.categoryId = categoryId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}
	}
}