package org.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
	public static Connection getConnection() throws SQLException{
        Connection con = null;
        //String url = "jdbc:mysql://192.168.2.10:3306/astro_ceriadunia";
        //String url = "jdbc:mysql://192.168.2.229:3306/astro_ceriadunia";
        String url = "jdbc:mysql://54.251.105.97:3306/astro_ceriadunia";
        String user = "root";
        String password = "As1213$$$";
        
        try {
            con = DriverManager.getConnection(url, user, password);
        }catch(SQLException e){
        	System.out.println(e);
        	throw new SQLException("connection error",e);
        }
        return con;
    }

	public static void testDBManager(){
        try {
        	ArrayList<Object[]> resultList = DBManager.readDatabase("SELECT * FROM tb_users");
        	
        	for(int i=0;i<resultList.size();i++){
        		for(int j=0;j<resultList.get(i).length;j++){
        			System.out.println(resultList.get(i)[j]);
        		}
        	}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
    }    
    
	public static void connectionTest(){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con=getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_users");
            if(rs.first()){
            	System.out.println(rs.getString("name"));            	
            }
            
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DBManager.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
	}
	
    public static ArrayList<Object[]> readDatabase(String sql) throws SQLException{
    	Connection con = null;
		ResultSet rs = null;
		ArrayList<Object[]> resultList=new ArrayList<Object[]>();
		Object[] result = null;
		try{
			con = getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			int count=rs.getMetaData().getColumnCount();
			
			for(int i=0;i<count;i++){
				//System.out.println(rs.getMetaData().getColumnName(i+1));
			}
			
			while(rs.next()){
				ResultSetMetaData meta = rs.getMetaData();
		        int cols = meta.getColumnCount();
		        result = new Object[cols];
		        for (int i = 0; i < cols; i++) {
		            result[i] = rs.getObject(i + 1);
		        }
		        resultList.add(result);
			}
			
		}catch(SQLException e){
			throw new SQLException("read sql error", e);
    	}finally{
    		try{
    			if(con!=null){
    				con.close();
    			}
    		}catch(SQLException e){
    			throw new SQLException("close connection error", e);
    		}
    	}
		return resultList;
	}
    
	public static void writeDatabase(String sql) throws SQLException{
		Connection con = null;
		try{
			con = getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
	    	stmt.executeUpdate();
	    	
		}catch(SQLException e){
        	throw new SQLException("write database error");
		}finally{
			try{
				if(con!=null){
					con.close();
				}
			}catch(SQLException e){
				throw new SQLException("close connection error");
			}
		}
		
	}
}
