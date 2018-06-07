package com.tp.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tp.daointerface.IDao;
import com.tp.enity.TpRowMapping;

@Repository
public class ImpDao implements IDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	// 可能需要
	@Autowired
	private Formatter<Date> toDate;

	@Override
	public List<TpRowMapping> getTp(Date startData, Date endDate) {
		// TODO Auto-generated method stub
		String sDate = toDate.print(startData, null);
		String eDate = toDate.print(endDate, null);
		List<TpRowMapping> tpList = jdbctemplate.query("select * from weather where date >= ? and date<= ?",
				new Object[] { sDate, eDate }, new RowMapper<TpRowMapping>() {

					@Override
					public TpRowMapping mapRow(ResultSet rs, int rownub) throws SQLException {
						// TODO Auto-generated method stub
						TpRowMapping tpRowMapping = new TpRowMapping();
						tpRowMapping.setId(rs.getInt("id"));
						tpRowMapping.setDate(toDate.print(rs.getTimestamp("date"), null));
						tpRowMapping.setTemperature(rs.getFloat("temperature"));
						tpRowMapping.setLocal(rs.getString("local"));
						return tpRowMapping;
					}

				});
		return tpList;
	}

	@Override
	public boolean insert(float tp, String local) {
		// TODO Auto-generated method stub
		int temp = 0;
		temp = jdbctemplate.update("insert into weather (temperature,local) values(?,?)", tp, local);
		if (temp > 0)
			return true;
		return false;
	}

}
