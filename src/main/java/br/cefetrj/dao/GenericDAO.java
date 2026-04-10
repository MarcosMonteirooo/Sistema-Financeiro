package br.cefetrj.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.cefetrj.dao.utils.HibernateUtil;

import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T>{


   public boolean salvar(T entidade) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(T entidade) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try{
            entityManager.getTransaction().begin();
           entityManager.merge(entidade); // antes: update()
           entityManager.getTransaction().commit();
           return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            e.printStackTrace();
           return false;
        }
    }

    public boolean deletar(Long id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try{
        entityManager.getTransaction().begin();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        T entidade = entityManager.find(entityClass, id);
        if (entidade != null) {
            entityManager.remove(entidade); // antes: delete()
        }
        entityManager.getTransaction().commit();
        return true;
    
        }catch(Exception e){
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        
    }

    public T buscarPorId(Long id) {
        try (EntityManager entityManager = HibernateUtil.getEntityManager()) {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            T usuario = entityManager.find(entityClass, id);
            return usuario;
        }
    }

    public List<T> listarTodos() {
        try (EntityManager entityManager = HibernateUtil.getEntityManager()) {
            Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            return entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
        }
    }

}  
    

