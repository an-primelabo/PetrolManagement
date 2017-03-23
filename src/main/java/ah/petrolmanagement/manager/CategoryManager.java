package ah.petrolmanagement.manager;

import java.util.HashMap;
import java.util.Map;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class CategoryManager {
	private static CategoryManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, String> categoryMap;

	private CategoryManager() {
		this.categoryMap = new HashMap<Integer, String>();
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

	public static String getCategory(final int id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			} else {
				singleton = new CategoryManager();
				return singleton.reloadFromDB();
			}
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.categoryMap) {
			Map<Integer, String> temp = this.categoryMap;

			try {
				this.categoryMap = new HashMap<Integer, String>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_CATEGORY_SELECT);
				CategoryResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, CategoryResponseDto[].class);

				if (dataList != null) {
					for (CategoryResponseDto dto : dataList) {
						this.categoryMap.put(dto.getId(), dto.getCategoryName());
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

	public String getName(int id) {
		if (this.categoryMap.get(id) != null) {
			return this.categoryMap.get(id);
		} else {
			return id + ApiConstants.BLANK;
		}
	}
}