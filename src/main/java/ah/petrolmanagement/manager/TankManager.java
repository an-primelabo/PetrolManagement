package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class TankManager {
	private static TankManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, ItemData> tankMap;

	private TankManager() {
		this.tankMap = new HashMap<Integer, ItemData>();
	}

	public static TankManager getInstance() {
		if (singleton == null) {
			synchronized (_LOCK) {
				if (singleton == null) {
					singleton = new TankManager();
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

	public static String getTankName(final int id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			} else {
				singleton = new TankManager();
				return singleton.reloadFromDB();
			}
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.tankMap) {
			Map<Integer, ItemData> temp = this.tankMap;

			try {
				this.tankMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_TANK_SELECT);
				TankResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, TankResponseDto[].class);

				if (dataList != null) {
					for (TankResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getProductId(), dto.getTankName());
						this.tankMap.put(dto.getId(), item);
					}
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.tankMap = temp;
				return false;
			}
		}
	}

	public Integer getProId(int id) {
		if (this.tankMap.get(id) != null) {
			return this.tankMap.get(id).getProductId();
		} else {
			return id;
		}
	}

	public List<Integer> getIds(int id) {
		List<Integer> list = new ArrayList<Integer>();

		for (Map.Entry<Integer, ItemData> entry : this.tankMap.entrySet()) {
			ItemData item = entry.getValue();

			if (item.getProductId() == id) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public String getName(int id) {
		if (this.tankMap.get(id) != null) {
			return this.tankMap.get(id).getTankName();
		} else {
			return id + ApiConstants.BLANK;
		}
	}

	private static class ItemData {
		private Integer productId;
		private String tankName;

		public ItemData(Integer productId, String tankName) {
			super();
			this.productId = productId;
			this.tankName = tankName;
		}

		public Integer getProductId() {
			return productId;
		}

		public String getTankName() {
			return tankName;
		}
	}
}