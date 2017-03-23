package ah.petrolmanagement.logic.impl;

import org.springframework.stereotype.Component;

import ah.petrolmanagement.logic.CommonLogic;

@Component
public class RoleDaoImpl extends CommonLogic{
//implements
//		RoleDao {
//	static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
//
//	@Override
//	public RoleEntity findById(int id) {
//		logger.info("findById : {}", id);
//		// Criteria criteria = createEntityCriteria();
//		// criteria.add(Restrictions.eq("id", id));
//		// RoleEntity entity = (RoleEntity) criteria.uniqueResult();
//		return getByKey(id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<RoleEntity> findAllRoles() {
//		logger.info("findAllRoles : {}");
//		Criteria criteria = createEntityCriteria();
//		criteria.addOrder(Order.asc("role"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return (List<RoleEntity>) criteria.list();
//	}
//
//	@Override
//	public void save(RoleEntity entity) {
//		logger.info("save : {}", entity);
//		persist(entity);
//	}
}