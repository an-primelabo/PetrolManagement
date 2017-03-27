package ah.petrolmanagement.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.constants.UrlConstants;
import ah.petrolmanagement.dto.response.ShiftResponseDto;
import ah.petrolmanagement.utils.ControllerUtil;

public class ShiftManager {
	private static ShiftManager singleton;

	private static final Object _LOCK = new Object();

	private Map<Integer, ItemData> shiftMap;

	private ShiftManager() {
		this.shiftMap = new HashMap<Integer, ItemData>();
	}

	public static ShiftManager getInstance() {
		if (singleton == null) {
			synchronized (_LOCK) {
				if (singleton == null) {
					singleton = new ShiftManager();
					singleton.reloadFromDB();
				}
			}
		}
		return singleton;
	}

	public static List<ItemData> getShiftList() {
		return getInstance().getList();
	}

	public static Integer getShiftCode(final int id) {
		return getInstance().getCode(id);
	}

	public static String getShiftName(final int id) {
		return getInstance().getName(id);
	}

	public static String getUserName(final int id) {
		return getInstance().getUser(id);
	}

	public static boolean reload() {
		synchronized (_LOCK) {
			if (singleton != null) {
				return singleton.reloadFromDB();
			}
			singleton = new ShiftManager();
			return singleton.reloadFromDB();
		}
	}

	private boolean reloadFromDB() {
		synchronized (this.shiftMap) {
			Map<Integer, ItemData> temp = this.shiftMap;

			try {
				this.shiftMap = new HashMap<Integer, ItemData>();

				String data = ControllerUtil.callAPI(UrlConstants.URL_SHIFT_SELECT);
				ShiftResponseDto[] dataList = ControllerUtil.convertJson2Dto(data, ShiftResponseDto[].class);

				if (dataList != null) {
					for (ShiftResponseDto dto : dataList) {
						ItemData item = new ItemData(dto.getId(), dto.getShiftCode(), dto.getShiftName(), dto.getUsername());
						this.shiftMap.put(dto.getId(), item);
					}
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.shiftMap = temp;
				return false;
			}
		}
	}

	public List<ItemData> getList() {
		List<ItemData> list = new ArrayList<ItemData>();

		if (this.shiftMap != null) {
			list = new ArrayList<ItemData>(this.shiftMap.values());
		}
		return list;
	}

	public Integer getCode(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getShiftCode();
		}
		return id;
	}

	public String getName(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getShiftName();
		}
		return id + ApiConstants.BLANK;
	}

	public String getUser(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getUsername();
		}
		return id + ApiConstants.BLANK;
	}

	public static class ItemData {
		private Integer id;
		private Integer shiftCode;
		private String shiftName;
		private String username;

		public ItemData(Integer id, Integer shiftCode, String shiftName,
				String username) {
			super();
			this.id = id;
			this.shiftCode = shiftCode;
			this.shiftName = shiftName;
			this.username = username;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getShiftCode() {
			return shiftCode;
		}

		public void setShiftCode(Integer shiftCode) {
			this.shiftCode = shiftCode;
		}

		public String getShiftName() {
			return shiftName;
		}

		public void setShiftName(String shiftName) {
			this.shiftName = shiftName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
}