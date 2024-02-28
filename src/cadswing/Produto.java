
package cadswing;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Produto extends Grupo {
    
    private String nomeProd;
    private double valorVenda;
    Connection conn;
    PreparedStatement pstm;
    
    public Produto(String nomeProd, double valorVenda, String nomeGrup) {
        super(nomeGrup, true);
        this.nomeProd = nomeProd;
        this.valorVenda = valorVenda;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
   public void cadastrarProduto(Produto p1){
       String sql = "insert into estoque (nome, valvenda, grupo) values (?, ?, ?)";
       
       conn = new ConexaoDAO().conectaBD();
           try {
               
               pstm =conn.prepareStatement(sql);
               pstm.setString(1, p1.getNomeProd());
               pstm.setDouble(2, p1.getValorVenda());
               pstm.setString(3, p1.getNomeGrup());
               
               pstm.execute();
               pstm.close();
           
       } catch (Exception erro) {
           
               JOptionPane.showMessageDialog(null,"cadastrarProduto" + erro );
           
       }
   } 
   
   
   public void deletarProduto(DefaultTableModel modeloTabela, JTable tabela) {
        // Verifica se há alguma linha selecionada na tabela
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtém o nome do produto da linha selecionada
        String nomeProduto = (String) modeloTabela.getValueAt(linhaSelecionada, 0);

        // Remove a linha selecionada da tabela
        modeloTabela.removeRow(linhaSelecionada);

        // Executa a instrução SQL para deletar o registro do banco de dados
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            String sql = "DELETE FROM estoque WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomeProduto);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
   
   public void carregarDadosEstoque(DefaultTableModel modeloTabela) {
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")) {
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estoque");
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valvenda");
                String grupo = rs.getString("grupo");
                String cor = rs.getString("corgrupo");
                
                modeloTabela.addRow(new Object[]{nome, valor, grupo, cor});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
