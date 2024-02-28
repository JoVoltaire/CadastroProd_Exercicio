
package cadswing;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ConexaoDAO {
    
    public Connection conectaBD(){
       Connection conn = null;
       
        try {
            String url = "jdbc:mysql://localhost:3306/test?user=root&password=";
            conn = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoDAO " + erro.getMessage());
        }
       
       return conn;
    }
    
    
    
    
}
