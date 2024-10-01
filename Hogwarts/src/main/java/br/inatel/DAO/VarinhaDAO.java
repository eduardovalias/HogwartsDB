package br.inatel.DAO;

import br.inatel.Model.Varinha;

import java.sql.SQLException;
import java.util.ArrayList;

public class VarinhaDAO extends ConnectionDAO{
    public boolean insertVarinha(Varinha varinha){
        connectToDB();

        boolean sucesso;
        String sql = "INSERT INTO Varinha (nucleo, madeira) VALUES (?, ?)";

        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getNucleo());
            pst.setString(2, varinha.getMadeira());
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir varinha: " + e.getMessage());
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

    public boolean updateVarinha(Varinha varinha) {
        connectToDB();

        boolean sucesso;
        String sql = "UPDATE Varinha SET nucleo=?, madeira=? WHERE idVarinha=?";

        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, varinha.getNucleo());
            pst.setString(2, varinha.getMadeira());
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar varinha: " + e.getMessage());
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

    public boolean deleteVarinha(int idVarinha) {
        connectToDB();

        boolean sucesso;
        String sql = "DELETE FROM Varinha WHERE idVarinha=?";

        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, idVarinha);
            pst.execute();
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar varinha: " + e.getMessage());
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

    public ArrayList<Varinha> getAllVarinhas() {
        connectToDB();

        ArrayList<Varinha> varinhas = new ArrayList<>();
        String sql = "SELECT * FROM Varinha";

        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Varinha varinha = new Varinha(rs.getInt("idVarinha"), rs.getString("nucleo"), rs.getString("madeira"));
                System.out.println("Nucleo: " + varinha.getNucleo() + "Madeira: " + varinha.getMadeira());
                varinhas.add(varinha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar varinhas: " + e.getMessage());
        } finally {
            try{
                con.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return varinhas;
    }
}
