package br.cefetrj.dao.utils;

import br.cefetrj.dao.utils.HibernateUtil;
import br.cefetrj.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


public class InicializadorBanco implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", "admin@admin.com");

            boolean existe = !query.getResultList().isEmpty();

            if (!existe) {
                em.getTransaction().begin();

                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@admin.com");
                admin.setSenha("123456");
                admin.setPerfil("SUPERVISOR FINANCEIRO");

                em.persist(admin);

                em.getTransaction().commit();

                System.out.println("Usuário admin criado com sucesso.");
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}