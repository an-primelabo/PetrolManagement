package ah.petrolmanagement.logic.impl;

import org.springframework.stereotype.Component;

import ah.petrolmanagement.logic.CommonLogic;

@Component
public class PersistentLoginDaoImpl extends CommonLogic {
//implements
//		PersistentLoginDao {
//	static final Logger logger = LoggerFactory
//			.getLogger(PersistentLoginDaoImpl.class);
//
//	@Override
//	public PersistentLoginEntity findByUsername(String username) {
//		logger.info("findByUsername : {}", username);
//		// Criteria criteria = createEntityCriteria();
//		// criteria.add(Restrictions.eq("username", username));
//		// PersistentLoginEntity entity = (PersistentLoginEntity)
//		// criteria.uniqueResult();
//		return getByKey(username);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PersistentLoginEntity> listPersistentLogins() {
//		logger.info("listPersistentLogins : {}");
//		Criteria criteria = createEntityCriteria();
//		criteria.addOrder(Order.asc("username"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return (List<PersistentLoginEntity>) criteria.list();
//	}
//
//	@Override
//	public void save(PersistentLoginEntity entity) {
//		logger.info("save : {}", entity);
//		persist(entity);
//	}
}