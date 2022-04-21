package de.deminosa.core.utils.mysql.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import de.deminosa.core.YukiLib;

/*
*	Class Create by Deminosa
*	YouTube: 	Deminosa
* 	Web:	 	deminosa.de
*	Create at: 	13:19:18 # 21.04.2022
*
*/

public class Table {

	private final String tableName;
	
	public Table(@Nonnull String name) {
		this.tableName = name;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void create(@Nonnull Colum colum) {
		if(!checkConnection()) return;
		String s = "";
		for(String colums : colum.getMap().keySet()) {
			s = s + colums + " " + colum.getMap().get(colums).getArguments() + ",";
		}
		if(s.endsWith(",")) {
			s = s.substring(0, s.length()-1);
		}
		
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("CREATE TABLE IF NOT EXISTS " + getTableName() 
			+ "("+s+")");
	}
	
	public void setFirstColum(@Nonnull ColumValue value) {
		if(!checkConnection()) return;
		if(YukiLib.DEBUG) System.out.println(">> set "+getTableName()+" in colum " + value.getColumName() 
				+ " with the value '"+value.getColumValue()+"'");
		
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("INSERT INTO "+getTableName()+" ("
				+value.getColumName()+") VALUES ('"+value.getColumValue()+"')");
	}
	
	public void updateColum(@Nonnull Update update) {
		if(!checkConnection()) return;
		if(YukiLib.DEBUG) System.out.println(">> Update " + getTableName() + " in colum " 
				+ update.getColum().getColumName()
				+ " with value '"+update.getColum().getColumValue()+"' and searching by " 
				+ update.getSearch().getWhere() + " with the value '" + update.getColum() + "'");
		
		YukiLib.get().getAsyncMySQL().getMySQL().queryUpdate("UPDATE "+getTableName()
				+" SET "+update.getColum().getColumName()+"='"+update.getColum().getColumValue()
				+"' WHERE "+update.getSearch().getWhere()+"='"+update.getSearch().getMatch()+"'");
	}
	
	public boolean exsistValue(Update update) {
		if(!checkConnection()) return false;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "
					+getTableName()+" WHERE "+update.getSearch().getWhere()+"= '"
					+update.getSearch().getMatch()+"'");
			if (rs.next()){
				String s = rs.getString(update.getColum().getColumName());
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
	
	public Object getValue(Update update) {
		if(!checkConnection()) return null;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "
					+getTableName()+" WHERE "+update.getSearch().getWhere()+"= '"
					+update.getSearch().getMatch()+"'");
			if (rs.next()){
				Object s = rs.getObject(getTableName());
				rs.close();
				return s;
			}
		}catch (SQLException e){
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public <T> T getValue(Update update, Class<? extends T> clazz, T t) {
		if(!checkConnection()) return null;
		ResultSet rs = null;
		try{
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "
					+getTableName()+" WHERE "+update.getSearch().getWhere()+"= '"
					+update.getSearch().getMatch()+"'");
			if (rs.next()){
				T s = rs.getObject(getTableName(), clazz);
				rs.close();
				return s;
			}
		}catch (SQLException e){
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public void deletRow(@Nonnull Search search) {
		if(!checkConnection()) return;
		YukiLib.get().getAsyncMySQL().getMySQL().
			queryUpdate("DELETE FROM " + getTableName() + " WHERE " + search.getWhere() 
				+ " = '"+search.getMatch()+"'");
	}
	
	public ArrayList<String> getArrayList(@Nonnull Update update){
		if(!checkConnection()) return null;
		
		ArrayList<String> list = new ArrayList<>();

		ResultSet rs = null;
		try {
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+getTableName()+
					" WHERE "+update.getSearch().getWhere()+"= '"
					+update.getSearch().getMatch()+"'");
			while (rs.next()) {
				list.add(rs.getString(update.getColum().getColumName()));
			}
			return list;
		}catch (SQLException e) {
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<String> getArrayList(@Nonnull ColumValue value){
		if(!checkConnection()) return null;
		
		ArrayList<String> list = new ArrayList<>();

		ResultSet rs = null;
		try {
			rs = YukiLib.get().getAsyncMySQL().getMySQL().query("SELECT * FROM "+getTableName());
			while (rs.next()) {
				list.add(rs.getString(value.getColumName()));
			}
			return list;
		}catch (SQLException e) {
			if(YukiLib.DEBUG) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean checkConnection() {
		if(YukiLib.get().getAsyncMySQL().getMySQL() == null) {
			return false;
		}
		return true;
	}
}
