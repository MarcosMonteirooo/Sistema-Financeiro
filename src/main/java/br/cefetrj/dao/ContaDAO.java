package br.cefetrj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import java.math.BigDecimal;

import br.cefetrj.dao.utils.ConnectionFactory;
import br.cefetrj.dao.utils.HibernateUtil;
import br.cefetrj.model.Conta;
import jakarta.persistence.EntityManager;

public class ContaDAO {

    public boolean cadastrarConta(Conta conta) throws SQLException {

        /*String sql = "INSERT INTO conta (descricao, valor, vencimento, fornecedor_id, plano_de_contas_id) VALUES (?, ?, ?, ?, ?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, conta.getDescricao());
            stmt.setBigDecimal(2, conta.getValor());
            stmt.setDate(3, conta.getVencimento());
            stmt.setLong(4, conta.getFornecedorId());
            stmt.setLong(5, conta.getPlanoDeContasId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;*/

        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(conta);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
                e.printStackTrace();
        }
        return false;

    }


    public List<Conta> listarContas() throws SQLException {
        /*String sql = """
            SELECT c.id, c.descricao, c.valor, c.vencimento,
                   c.fornecedor_id, c.plano_de_contas_id,
                   f.nome AS nome_fornecedor,
                   p.nome AS nome_plano
            FROM conta c
            INNER JOIN fornecedor f ON c.fornecedor_id = f.id
            INNER JOIN plano_de_contas p ON c.plano_de_contas_id = p.id
            ORDER BY c.vencimento ASC
        """;

        List<Conta> listaContas = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getLong("id"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setValor(rs.getBigDecimal("valor"));
                conta.setVencimento(rs.getDate("vencimento"));
                conta.setFornecedorId(rs.getLong("fornecedor_id"));
                conta.setPlanoDeContasId(rs.getLong("plano_de_contas_id"));
                conta.setNomeFornecedor(rs.getString("nome_fornecedor"));
                conta.setNomePlanoDeContas(rs.getString("nome_plano"));

                listaContas.add(conta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaContas;*/

        try (EntityManager entityManager = HibernateUtil.getEntityManager()){
            return entityManager.createQuery("from Conta", Conta.class).getResultList();
        }
    }

    public Conta buscarContaPorId(Long id) throws SQLException {
        /*String sql = """
            SELECT c.id, c.descricao, c.valor, c.vencimento,
                   c.fornecedor_id, c.plano_de_contas_id
            FROM conta c
            WHERE c.id = ?
        """;

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getLong("id"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setValor(rs.getBigDecimal("valor"));
                conta.setVencimento(rs.getDate("vencimento"));
                conta.setFornecedorId(rs.getLong("fornecedor_id"));
                conta.setPlanoDeContasId(rs.getLong("plano_de_contas_id"));
                return conta;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;*/

        try(EntityManager entityManager = HibernateUtil.getEntityManager()){
            return entityManager.find(Conta.class, id);
        }
    }

    public boolean atualizarConta(Conta conta) throws SQLException {

        /*String sql = "UPDATE conta SET descricao = ?, valor = ?, vencimento = ?, fornecedor_id = ?, plano_de_contas_id = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, conta.getDescricao());
            stmt.setBigDecimal(2, conta.getValor());
            stmt.setDate(3, conta.getVencimento());
            stmt.setLong(4, conta.getFornecedorId());
            stmt.setLong(5, conta.getPlanoDeContasId());
            stmt.setLong(6, conta.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;*/

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        return false;

    }

    public boolean excluirConta(Long id) throws SQLException {

        /*String sql = "DELETE FROM conta WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setLong(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;*/

        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Conta conta = entityManager.find(Conta.class, id);
        if (conta !=  null){
            entityManager.remove(conta);
            return true;
        } 
        entityManager.getTransaction().commit();
        return false;
    }
        
        
}