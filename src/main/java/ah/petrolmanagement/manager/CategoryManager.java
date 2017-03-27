package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class CategoryManager {
	private static CategoryManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, ItemData> categoryMap;

	private CategoryManager() {
		this.categoryMap = new HashMap<Integer, ItemData>();
	}

	public static CategoryManager getInstance() {
		if (singleton == null) {
			synchronized (_LOCK) {
				if (singleton == null) {
					singleton = new CategoryManager();
					singleton.reloadFromDB();
				}
			}
		}
		return singleton;
	}

	public static List<ItemData> getCategoryList() {
		return getInstance().getList();
	}

	public static String getCategory(final int id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			}
			singleton = new CategoryManager();
			return singleton.reloadFromDB();
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.categoryMap) {
			Map<Integer, ItemData> temp = this.categoryMap;

			try {
				this.categoryMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_CATEGORY_SELECT);
				CategoryResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, CategoryResponseDto[].class);

				if (dataList != null) {
					for (CategoryResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getId(), dto.getCategoryName());
						this.categoryMap.put(dto.getId(), item);
					}
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.categoryMap = temp;
				return false;
			}
		}
	}

	public List<ItemData> getList() {
		List<ItemData> list = new ArrayList<ItemData>();

		if (this.categoryMap != null) {
			list = new ArrayList<CategoryManager.ItemData>(this.categoryMap.values());
		}
		return list;
	}

	public String getName(int id) {
		if (this.categoryMap.get(id) != null) {
			return this.categoryMap.get(id).getCategoryName();
		}
		return id + ApiConstants.BLANK;
	}

	public static class ItemData {
		private Integer id;
		private String categoryName;

		public ItemData(Integer id, String categoryName) {
			super();
			this.id = id;
			this.categoryName = categoryName;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
	}
}