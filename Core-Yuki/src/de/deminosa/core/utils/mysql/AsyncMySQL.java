package de.deminosa.core.utils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import de.deminosa.core.YukiLib;

public class AsyncMySQL {
	private ExecutorService executor;
	private YukiLib plugin;
	private MySQL sql;
	
	public AsyncMySQL(YukiLib owner, String host, String user, String password, String database) {
		try {
			sql = new MySQL(host, user, database, password);
			executor = Executors.newCachedThreadPool();
			plugin = owner;
			de.deminosa.core.utils.mysql.MySQL.isMySQLcon = true;
		} catch (Exception e) {
			if(YukiLib.DEBUG) e.printStackTrace();
			System.err.println("====================[ ERROR ]=========================");
			System.err.println(e.getMessage());
			System.err.println("====================[ ERROR ]=========================");
			de.deminosa.core.utils.mysql.MySQL.isMySQLcon = false;
		}
	}

	public void update(PreparedStatement statement) {
		executor.execute(() -> sql.queryUpdate(statement));
	}
	
	public void update(String statement) {
		executor.execute(() -> sql.queryUpdate(statement));
	}

	public void query(PreparedStatement statement, Consumer<ResultSet> consumer) {
		executor.execute(() -> {
			ResultSet result = sql.query(statement);
			Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(result));
		});
	}

	public ResultSet query(String statement, Consumer<ResultSet> consumer) {
		executor.execute(() -> {
			ResultSet result = sql.query(statement);
			Bukkit.getScheduler().runTask(plugin, () -> consumer.accept(result));
		});
		return null;
	}

	public PreparedStatement prepare(String query) {
		try {
			return sql.getConnection().prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MySQL getMySQL() {
		return sql;
	}

	public static class MySQL {

		private String host, user, password, database;

		private Connection conn;

		public MySQL(String host, String user, String database, String password) throws Exception {
			this.host = host;
			this.user = user;
			this.password = password;
			this.database = database;

			this.openConnection();
		}

		public void queryUpdate(String query) {
			checkConnection();
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				queryUpdate(statement);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void queryUpdate(PreparedStatement statement) {
			checkConnection();
			try {
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public ResultSet query(String query) {
			checkConnection();
			try {
				return query(conn.prepareStatement(query));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public ResultSet query(PreparedStatement statement) {
			checkConnection();
			try {
				return statement.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public Connection getConnection() {
			return this.conn;
		}

		private void checkConnection() {
			try {
				if (this.conn == null || !this.conn.isValid(10) || this.conn.isClosed()) openConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Connection openConnection() throws Exception {
			Class.forName("com.mysql.jdbc.Driver");
			return this.conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + this.host + ":3306/" + this.database, this.user, this.password);
		}

		public void closeConnection() {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conn = null;
			}
		}
	}
}