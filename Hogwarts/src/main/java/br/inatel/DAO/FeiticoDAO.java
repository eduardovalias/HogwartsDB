package br.inatel.DAO;

import br.inatel.Model.Feitico;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeiticoDAO extends ConnectionDAO{
    public boolean insertFeitico(Feitico feitico) {
        connectToDB();

        boolean sucesso;
        String sql = "INSERT INTO Feitico (nome, tipo, efeito, dano) VALUES (?, ?, ?, ?)";

        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, feitico.getNome());
            pst.setString(2, feitico.getTipo());
            pst.setString(3, feitico.getEfeito());
            pst.setInt(4, feitico.getDano());
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir feitiço: " + e.getMessage());
            sucesso = false;
        } finally {
            try{
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updateFeitico(Feitico feitico) {
        connectToDB();

        boolean sucesso;
        String sql = "UPDATE Feitico SET nome=?, tipo=?, efeito=?, dano=? WHERE idFeitico=?";

        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, feitico.getNome());
            pst.setString(2, feitico.getTipo());
            pst.setString(3, feitico.getEfeito());
            pst.setInt(4, feitico.getDano());
            pst.setInt(5, feitico.getIdFeitico());
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar feitiço: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deleteFeitico(int idFeitico) {
        connectToDB();

        boolean sucesso;
        String sql = "DELETE FROM Feitico WHERE idFeitico=?";

        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, idFeitico);
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar feitiço: " + e.getMessage());
            sucesso = false;
        } finally {
            try{
                con.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Feitico> getAllFeiticos() {
        connectToDB();

        ArrayList<Feitico> feiticos = new ArrayList<>();
        String sql = "SELECT * FROM Feitico";

        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Feitico feitico = new Feitico(rs.getInt("idFeitico"), rs.getString("nome"), rs.getString("tipo"), rs.getString("efeito"), rs.getInt("dano"));
                System.out.println("Nome: " + feitico.getNome() + "Tipo: " + feitico.getTipo() + "Efeito" + feitico.getEfeito() + "Dano: " + feitico.getDano());
                feiticos.add(feitico);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar feitiços: " + e.getMessage());
        } finally {
            try{
                con.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return feiticos;
    }
}
