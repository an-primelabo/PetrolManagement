package ah.petrolmanagement.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import ah.petrolmanagement.exception.WrapRuntimeException;

public final class DatabaseMetaDataUtil {
	private DatabaseMetaDataUtil() {
	}

	public static Set<String> getColumns(final DataSource ds, final String table) {
		Set<String> columns = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		Connection con = null;

		try {
			con = ds.getConnection();
			DatabaseMetaData dmd = con.getMetaData();
			ResultSet rs = null;

			try {
				rs = dmd.getColumns(null, null, table, null);

				while (rs.next()) {
					columns.add(rs.getString("COLUMN_NAME"));
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			throw new WrapRuntimeException(e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new WrapRuntimeException(e);
				}
			}
		}
		return columns;
	}
}