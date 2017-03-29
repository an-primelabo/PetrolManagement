package ah.petrolmanagement.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.UserEntity;

public interface IUserMapper {
	List<UserEntity> select(final @Param("map") Map<String, Object> map);

	void save(final @Param("entity") UserEntity entity);

	void update(final @Param("entity") UserEntity entity);

	void delete(final @Param("entity") UserEntity entity);
}