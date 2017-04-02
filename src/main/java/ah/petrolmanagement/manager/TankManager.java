package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static List<ItemData> getTankList() {
		return getInstance().getList();
	}

	public static Integer getProductId(final Integer id) {
		return getInstance().getProId(id);
	}

	public static List<Integer> getIdList(final Integer id) {
		return getInstance().getIds(id);
	}

	public static String getTankName(final Integer id) {
		return getInstance().getName(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			}
			singleton = new TankManager();
			return singleton.reloadFromDB();
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.tankMap) {
			Map<Integer, ItemData> temp = this.tankMap;

			try {
				this.tankMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_API_TANK_SELECT);
				TankResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, TankResponseDto[].class);

				if (dataList != null) {
					for (TankResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getId(), dto.getProductId(), dto.getTankName());
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

	public List<ItemData> getList() {
		List<ItemData> list = new ArrayList<ItemData>();

		if (this.tankMap != null) {
			list = new ArrayList<ItemData>(this.tankMap.values());
		}
		return list;
	}

	public Integer getProId(Integer id) {
		if (this.tankMap.get(id) != null) {
			return this.tankMap.get(id).getProductId();
		}
		return id;
	}

	public List<Integer> getIds(Integer id) {
		List<Integer> list = new ArrayList<Integer>();

		for (Map.Entry<Integer, ItemData> entry : this.tankMap.entrySet()) {
			ItemData item = entry.getValue();

			if (item.getProductId() == id) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public String getName(Integer id) {
		if (this.tankMap.get(id) != null) {
			return this.tankMap.get(id).getTankName();
		}
		return id.toString();
	}

	public static class ItemData {
		private Integer id;
		private Integer productId;
		private String tankName;

		public ItemData(Integer id, Integer productId, String tankName) {
			super();
			this.id = id;
			this.productId = productId;
			this.tankName = tankName;
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

		public String getTankName() {
			return tankName;
		}

		public void setTankName(String tankName) {
			this.tankName = tankName;
		}
	}
}