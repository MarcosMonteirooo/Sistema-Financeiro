package br.cefetrj.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.dao.utils.ConnectionFactory;
import br.cefetrj.model.PlanoDeContas;

public class PlanoDeContasDAO {

    public boolean cadastrarPlano(PlanoDeContas plano) throws SQLException {
        String sql = "INSERT INTO plano_de_contas (nome) VALUES (?)";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, plano.getNome());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<PlanoDeContas> listarPlanos() throws SQLException {
        String sql = "SELECT * FROM plano_de_contas";
        List<PlanoDeContas> listaPlanos = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlanoDeContas plano = new PlanoDeContas();
                plano.setId(rs.getLong("id"));
                plano.setNome(rs.getString("nome"));

                listaPlanos.add(plano);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaPlanos;
    }

    public PlanoDeContas buscarPlanoPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM plano_de_contas WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PlanoDeContas plano = new PlanoDeContas();
                plano.setId(rs.getLong("id"));
                plano.setNome(rs.getString("nome"));
                return plano;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizarPlano(PlanoDeContas plano) throws SQLException {
        String sql = "UPDATE plano_de_contas SET nome = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setString(1, plano.getNome());
            stmt.setLong(2, plano.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluirPlano(Long id) throws SQLException {
        String sql = "DELETE FROM plano_de_contas WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        try {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
