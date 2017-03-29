package ah.petrolmanagement.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ah.petrolmanagement.entity.PersistentLoginEntity;

public interface IPersistentLoginMapper {
	List<PersistentLoginEntity> select(final @Param("series") String series);

	void save(final @Param("entity") PersistentLoginEntity entity);

	void update(final @Param("entity") PersistentLoginEntity entity);

	void delete(final @Param("username") String username);
}