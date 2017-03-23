package ah.petrolmanagement.logic.impl;

import org.springframework.stereotype.Component;

import ah.petrolmanagement.logic.CommonLogic;

@Component
public class UserDaoImpl extends CommonLogic {
	// implements
	// }
	// UserDao {
	// static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	//
	// @Override
	// public UserEntity findById(int id) {
	// logger.info("findById : {}", id);
	// // Criteria criteria = createEntityCriteria();
	// // criteria.add(Restrictions.eq("id", id));
	// // UserEntity entity = (UserEntity) criteria.uniqueResult();
	// return getByKey(id);
	// }
	//
	// @Override
	// public UserEntity findByUsername(String username) {
	// logger.info("findByUsername : {}", username);
	// Criteria criteria = createEntityCriteria();
	// criteria.add(Restrictions.eq("username", username));
	// return (UserEntity) criteria.uniqueResult();
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<UserEntity> findAllUsers() {
	// logger.info("findAllUsers : {}");
	// Criteria criteria = createEntityCriteria();
	// criteria.addOrder(Order.asc("username"));
	// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	// return (List<UserEntity>) criteria.list();
	// }
	//
	// @Override
	// public void save(UserEntity entity) {
	// logger.info("save : {}", entity);
	// persist(entity);
	// }
}