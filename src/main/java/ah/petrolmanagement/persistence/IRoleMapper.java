package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.RoleEntity;

public interface IRoleMapper {
	List<RoleEntity> select(@Param("map") Map<String, Object> map);
}