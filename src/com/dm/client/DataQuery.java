package com.dm.client;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataQuery {

	private Connection conn;
	private String page;
	private ApplicationContext context;

	// UUID id;

	public DataQuery(String page) {
		try {
			context = new AnnotationConfigApplicationContext(
					DataApplication.class);
			DataComponentStaging ds = context.getBean(DataComponentStaging.class);
			this.conn = ds.getConnection();
			this.page = page;
			// id = UUID.randomUUID();
			// Debug.println("DataQuery: conn.open()["+id+"]");
		} catch (Exception e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		}
	}

	public String setData(String query, String params[]) {
		String stat = "0";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
			for (int i = 0; i < params.length; i++) {
				ps.setString(i + 1, params[i]);
			}
			ps.execute();
			stat = "1";
		} catch (Exception e) {
			stat = "0";
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
		}
		return stat;
	}

	public ArrayList<ArrayList<String>> getData(String query, String params[]) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			for (int i = 0; i < params.length; i++) {
				ps.setString(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int no = metadata.getColumnCount();
			while (rs.next()) {
				try {
					int i = 1;
					while (i <= no) {
						result.add(rs.getString(i++));
					}
				} catch (Exception e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
			output.add(result);
		} catch (Exception e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
		}
		return output;
	}

	public void call(String query, String[] params) {
		CallableStatement cs = null;

		try {
			cs = conn.prepareCall(query);
			for (int i = 0; i < params.length; i++) {
				cs.setString(i + 1, params[i]);
			}
			cs.executeUpdate();
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
		}
	}

	public ArrayList<ArrayList<String>> callResult(String query, String[] params) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> result = new ArrayList<String>();
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			cs = conn.prepareCall(query);
			for (int i = 0; i < params.length; i++) {
				cs.setString(i + 1, params[i]);
			}
			rs = cs.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int no = metadata.getColumnCount();
			while (rs.next()) {
				try {
					int i = 1;
					while (i <= no) {
						result.add(rs.getString(i++));
					}
				} catch (Exception e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
			output.add(result);
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
		}
		return output;
	}

	public ArrayList<ArrayList<String>> callOut(String query, String[] params,
			int out) {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> result = new ArrayList<String>();
		CallableStatement cs = null;

		try {
			cs = conn.prepareCall(query);
			for (int i = 0; i < params.length; i++) {
				cs.setString(i + 1, params[i]);
			}
			for (int j = (params.length + 1); j < out; j++) {
				cs.registerOutParameter(j + 1, java.sql.Types.VARCHAR);
			}
			cs.executeUpdate();
			for (int k = (params.length + 1); k < out; k++) {
				result.add(cs.getString(k + 1));
			}
			output.add(result);
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					Debug.printStackTrace(e);
					Debug.log(e.getMessage(), page);
				}
			}
		}
		return output;
	}

	public void setAutoCommit(boolean status) {
		try {
			conn.setAutoCommit(status);
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		}
	}

	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		}
	}

	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			Debug.printStackTrace(e);
			Debug.log(e.getMessage(), page);
		}
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
				// Debug.println("DataQuery: conn.close()["+id+"]");
			} catch (SQLException e) {
				Debug.printStackTrace(e);
				Debug.log(e.getMessage(), page);
			}
		}
	}
}
