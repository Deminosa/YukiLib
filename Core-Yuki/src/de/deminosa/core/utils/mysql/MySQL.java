package de.deminosa.core.utils.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import de.deminosa.core.YukiLib;
import de.deminosa.core.utils.thread.SimpleThreadWorker;

/*
 *	Class Create by Deminosa
 *	YouTube: 	Deminosa
 * 	Web:	 	deminosa.de
 *	Create at: 	14:10:17 # 10.03.2019
 *
 */

public class MySQL {

	public static boolean isMySQLcon = false; 

	public static void query(String query) {
		if(!checkConnection()) return;
		
		new Thread(() -> {
			YukiLib.get().getAsyncMySQL().getMySQL().query(query);
		}).start();
	}

	public static void query(PreparedStatement preparedStatement) {
		if(!checkConnection()) return;
		
		new Thread(() -> {
			YukiLib.get().getAsyncMySQL().getMySQL().query(preparedStatement);
		}).start();
	}

	public static AsyncMySQL getMySQL() {
		return YukiLib.get().getAsyncMySQL();
	}
	
	public static boolean checkConnection() {
		if(YukiLib.get().getAsyncMySQL().getMySQL() == null) {
			return false;
		}
		return true;
	}
	
	@Deprecated
	public static void createTable(String table, String clumName, ColumType type) {
		if(!checkConnection()) return;
		
		new Thread(() -> {
			YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("CREATE TABLE IF NOT EXISTS " + table + "("+clumName+" "+type.getArguments()+")");
		}).start();
	}

	@Deprecated
	public static void createTable(String table, ArrayList<String> list, ArrayList<ColumType> types) {
		if(!checkConnection()) return;
		
		new SimpleThreadWorker("Core-MySQL") {
			@Override
			public void update() {
				String s = "";
				for(String clumName : list) {
					for(ColumType typ : types) {
						s = s + clumName + " " + typ.getArguments() + ",";
					}
				}
				if(s.endsWith(",")) {
					s = s.substring(0, s.length()-1);
				}
				YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("CREATE TABLE IF NOT EXISTS " + table + "("+s+")");
			}
		}.start();
	}

	@Deprecated
	public static void createTable(String table, String[] list, ColumType[] types) {
		if(!checkConnection()) return;
		
		new SimpleThreadWorker("Core-MySQL") {
			
			@Override
			public void update() {
				String s = "";
				for(int i = 0; i < list.length; i++ ) {
					s = s + list[i] + " " + types[i].getArguments() + ",";
				}
				if(s.endsWith(",")) {
					s = s.substring(0, s.length()-1);
				}
				YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("CREATE TABLE IF NOT EXISTS " + table + "("+s+")");
			}
		}.start();
	}

	@Deprecated
	public static void createTable(String table, HashMap<String, ColumType> map) {
		if(!checkConnection()) return;
		
		new SimpleThreadWorker("Core-MySQL") {
			@Override
			public void update() {
				String s = "";
				for(String colums : map.keySet()) {
					s = s + colums + " " + map.get(colums).getArguments() + ",";
				}
				if(s.endsWith(",")) {
					s = s.substring(0, s.length()-1);
				}
				YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("CREATE TABLE IF NOT EXISTS " + table + "("+s+")");
			}
		}.start();
	}

	@Deprecated
	public static String getString(String table, String where, String match, String colum) {
		if(!checkConnection()) return null;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+table+" WHERE "+where+"= '"+match+"'");
			if (rs.next()){
				String s = rs.getString(colum);
				rs.close();
				return s;
			}
		}catch (SQLException e){
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}

		}
		return "";
	}

	/**
	 * <p>Wenn MySQL nicht verbunden dann kommt -1 zurück</p>
	 * <p>Wenn clum von where nicht gefunden oder wert ist gleich null,
	 * dann kommt -2 zurück</p>
	 * 
	 * @param table
	 * @param colum
	 * @param where
	 * @param match
	 * @return int
	 */
	@Deprecated
	public static int getInt(String table, String colum, String where, String match) {
		if(!checkConnection()) return -3;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+table+" WHERE "+where+"= '"+match+"'");
			if (rs.next()){
				int s = rs.getInt(colum);
				rs.close();
				return s;
			}
		}catch (SQLException e){
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return -2;
	}

	@Deprecated
	public static boolean exsistValue(String table, String where, String match, String colum) {
		if(!checkConnection()) return false;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+table+" WHERE "+where+"= '"+match+"'");
			if (rs.next()){
				String s = rs.getString(colum);
				rs.close();
				return s != null;
			}
		}catch (SQLException e){
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}

		}
		return false;
	}

	@Deprecated
	public static ArrayList<String> getArrayList(String table, String where, String match, String colum){
		if(!checkConnection()) return null;
		
		ArrayList<String> list = new ArrayList<>();

		ResultSet rs = null;
		try {
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+table+" WHERE "+where+"= '"+match+"'");
			while (rs.next()) {
				list.add(rs.getString(colum));
			}
			return list;
		}catch (SQLException e) {
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Deprecated
	public static ArrayList<String> getArrayList(String table, String colum){
		if(!checkConnection()) return null;
		
		ArrayList<String> list = new ArrayList<>();

		ResultSet rs = null;
		try {
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+table);
			while (rs.next()) {
				list.add(rs.getString(colum));
			}
			return list;
		}catch (SQLException e) {
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Deprecated
	public static void set(String table, String colum, Object value) {
		if(!checkConnection()) return;
		if(YukiLib.DEBUG) System.out.println(">> set "+table+" in colum " + colum + " with the value '"+value+"'");
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("INSERT INTO "+table+" ("+colum+") VALUES ('"+value+"')");
	}

	@Deprecated
	public static void update(String table, String colum, String value, String where, String match) {
		if(!checkConnection()) return;
		if(YukiLib.DEBUG) System.out.println(">> Update " + table + " in colum " + colum + " with value '"+value+"' and searching by " + where + " with the value '" + match + "'");
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("UPDATE "+table+" SET "+colum+"='"+value+"' WHERE "+where+"='"+match+"'");
	}
	
	@Deprecated
	public static void update(String table, String colum, int value, String where, String match) {
		if(!checkConnection()) return;
		if(YukiLib.DEBUG) System.out.println(">> Update " + table + " in colum " + colum + " with value "+value+" and searching by " + where + " with the value '" + match + "'");
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("UPDATE "+table+" SET "+colum+"="+value+" WHERE "+where+"='"+match+"'");
	}

	@Deprecated
	public static void deleteRow(String table, String where, String match) {
		if(!checkConnection()) return;
		new SimpleThreadWorker("Core-MySQL") {
			@Override
			public void update() {
				YukiLib.get().getAsyncMySQL().getMySQL().
					queryUpdate("DELETE FROM " + table + " WHERE " + where + " = '"+match+"'");
			}
		}.start();
	}

}
