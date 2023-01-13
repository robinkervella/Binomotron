import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/binomotron";
        String user = "root";
        String passwd = "root";
        String driver = "com.mysql.jdbc.Driver";

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        List<String> apprenants = getListeApprenants(url, user, passwd, con, st, rs);


        Collections.shuffle(apprenants);
        while (apprenants.size() >= 2) {
            String remove = apprenants.remove(0);
            String remove1 = apprenants.remove(0);
            System.out.println("Groupe : " + remove + " et " + remove1);

            if (apprenants.size() == 1) {
                System.out.println(apprenants.get(0));
            }
        }
    }

    private static List<String> getListeApprenants(String url, String user, String passwd, Connection con, Statement st, ResultSet rs) {

        ArrayList<String> apprenants = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, user, passwd);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM apprenants");


            while (rs.next()) {
                System.out.println(rs.getString("prenom") + " " + rs.getString("nom"));
                String nomComplet = rs.getString("prenom") + " " + rs.getString("nom");
                apprenants.add(nomComplet);

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {

            }
        }
        return apprenants;
    }
}


