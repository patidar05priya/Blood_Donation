package com.deepak.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;

import com.deepak.utils.HibernateUtil;

public class JPABaseDao<Pk, Entity> {

	/**
	 * Creates an entity in the persistent store.
	 * 
	 * @param anEntity
	 *            Entity to create
	 * 
	 */
	public Entity create(Entity anEntity) {
		if (anEntity == null) {
			throw new IllegalArgumentException("Cannot create null entity");
		}
		//anEntity = HibernateUtil.getEntityManager().merge(anEntity);
		try{
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.persist(anEntity);
		entityManager.getTransaction().commit();
		return anEntity;
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
			return null;	
		}
	}

	/**
	 * Updates an entity in the persistent store.
	 * 
	 * @param anEntity
	 *            Entity to update.
	 * 
	 */
	public Entity update(Entity anEntity) {
		if (anEntity == null) {
			throw new IllegalArgumentException("Cannot update null entity");
		}
		return HibernateUtil.getEntityManager().merge(anEntity);
	}

	/**
	 * Delete the entity from the persistent store. Note: Entity's primary key
	 * must be populated.
	 * 
	 * @param anEntity
	 */
	public void delete(Entity anEntity) {
		if (anEntity == null) {
			throw new IllegalArgumentException("Cannot delete null entity");
		}
		HibernateUtil.getEntityManager().remove(HibernateUtil.getEntityManager().contains(anEntity) ? anEntity : HibernateUtil.getEntityManager().merge(anEntity));
	}

	/**
	 * Delete the entity from the persistent store identified by its primary
	 * key.
	 * 
	 * @param entityPk
	 *            Primary key object of the entity.
	 */
	/*public void deleteByPk(Pk entityPk) {
		if (entityPk == null) {
			throw new IllegalArgumentException("Cannot delete entity");
		}
		Entity anEntity = this.findByPk(entityPk);
		this.delete(anEntity);
	}
*/
	/**
	 * Checks if an entity with similar attribute values exists.
	 * 
	 * @param anEntity
	 *            represents entity which is referred for equality comparison.
	 * @return returns true if one or more entities exists else returns false.
	 */
	public boolean contains(Entity anEntity) {
		if (anEntity == null) {
			return false;
		}

		return HibernateUtil.getEntityManager().contains(HibernateUtil.getEntityManager().contains(anEntity) ? anEntity : HibernateUtil.getEntityManager().merge(anEntity));
	}

	/**
	 * Find by primary key. Search for an entity of the specified class and
	 * primary key. If the entity instance is contained in the persistence
	 * context it is returned from there.
	 * 
	 * @param entityPk
	 *            primary key.
	 * @return the found entity instance or null if the entity does not exist
	 * 
	 */
	
	/*public Entity findByPk(Pk entityPk) {
		if (entityPk == null) {
			return null;
		}

		return HibernateUtil.getEntityManager().find(getType(), entityPk);
	}
*/
	/**
	 * @return returns all the entities in the persistent store.
	 *//*
	public List<Entity> findAll() {
		return this.find(new QueryObject());
	}

	public List<Entity> findAll(String order, SortOrder sort) {
		HashMap<String, SortOrder> orderMap = new HashMap<String, QueryObject.SortOrder>();
		orderMap.put(order, sort);
		QueryObject query = new QueryObject();
		query.setOrderByMode(orderMap);
		return this.find(query);
	}*/
}
