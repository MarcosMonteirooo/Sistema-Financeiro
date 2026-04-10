package br.cefetrj.dao;

import java.util.List;
import br.cefetrj.dao.utils.HibernateUtil;
import br.cefetrj.model.Usuario;
import jakarta.persistence.EntityManager;

public class UsuarioDAO  extends GenericDAO<Usuario> {

    public UsuarioDAO(){
        super();
    }

        public Usuario buscarPorEmail(String email) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            List<Usuario> lista = em
                    .createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getResultList();

            if (lista.isEmpty()) {
                return null;
            }

            return lista.get(0);
        } finally {
            em.close();
        }
    }

    public boolean existeEmail(String email) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            Long total = em
                    .createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return total > 0;
        } finally {
            em.close();
        }
    }

}