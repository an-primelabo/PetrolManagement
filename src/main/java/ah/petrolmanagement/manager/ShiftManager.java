package ah.petrolmanagement.manager;

import java.util.HashMap;
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
			} else {
				singleton = new ShiftManager();
				return singleton.reloadFromDB();
			}
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
						ItemData item = new ItemData(dto.getShiftCode(), dto.getShiftName(), dto.getUsername());
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

	public Integer getCode(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getShiftCode();
		} else {
			return id;
		}
	}

	public String getName(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getShiftName();
		} else {
			return id + ApiConstants.BLANK;
		}
	}

	public String getUser(int id) {
		if (this.shiftMap.get(id) != null) {
			return this.shiftMap.get(id).getUsername();
		} else {
			return id + ApiConstants.BLANK;
		}
	}

	private static class ItemData {
		private Integer shiftCode;
		private String shiftName;
		private String username;

		public ItemData(Integer shiftCode, String shiftName, String username) {
			super();
			this.shiftCode = shiftCode;
			this.shiftName = shiftName;
			this.username = username;
		}

		public Integer getShiftCode() {
			return shiftCode;
		}

		public String getShiftName() {
			return shiftName;
		}

		public String getUsername() {
			return username;
		}
	}
}