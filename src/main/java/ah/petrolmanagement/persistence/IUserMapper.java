package ah.petrolmanagement.persistence;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.UserEntity;

public interface IUserMapper {
	Map<String, Object> login(@Param("username") String username);

	void save(@Param("entity") UserEntity entity);

	void update(@Param("entity") UserEntity entity);

	void delete(@Param("entity") UserEntity entity);
}