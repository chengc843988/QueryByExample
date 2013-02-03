package net.glxn.qbe;

import javax.persistence.EntityManager;

/**
 *
 * QueryByExample enables querying an entity by passing in an example object, of any type.
 * The only requirement is that the field names match. It will pull any non-null field from the example
 * and match a given entity class with the same fields.
 *
 * See:<br/>
 * {@link QBE#QBE(javax.persistence.EntityManager)}<br/>
 * {@link QBE#query(Class)}<br/>
 */
public class QBE {
    private EntityManager entityManager;

    private QBE(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Construct a new {@link QBE} using the supplied entityManager<br/>
     * <br/>
     * The {@link QBE} gives the ability to: <br/>
     * * get a list of entities matching the given criteria {@link net.glxn.qbe.QBEExample#list()}<br/>
     * {@code new QueryByExample(entityManager).query(UserEntity.class).example(new User("foo")).list()}<br/><br/>
     * * get a single entity matching the given criteria {@link net.glxn.qbe.QBEExample#item()}<br/>
     * {@code new QueryByExample(entityManager).query(UserEntity.class).example(new User("foo")).item()}<br/><br/>
     * *get the resulting typed query without executing it {@link net.glxn.qbe.QBEExample#getQuery()}<br/>
     * {@code new QueryByExample(entityManager).query(UserEntity.class).example(new User("foo")).getQuery()}<br/><br/>
     * {@link net.glxn.qbe.QBEExample#getQuery()} can be useful if you need to do paging on the typed query.
     *
     * @param entityManager the entityManager to use to create the queries.
     */
    public static QBE using(EntityManager entityManager) {
        return new QBE(entityManager);
    }

    /**
     * Create a {@link QBEQuery} for the given JPA entity class.
     * @param entityClass a JPA entity class. Must be known to the entityManager this {@link QBE} instance was created with
     * @param <E> the entity class type
     * @return a new {@link QBEQuery} for the given entity class
     */
    public <E> QBEQuery<E> query(Class<E> entityClass) {
        return new QBEQuery<E>(entityClass, entityManager);
    }
}