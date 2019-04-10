package zombies.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zombies.domain.Sight;
import zombies.domain.Zombie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SightMapper implements RowMapper<Sight> {
    public static final String  LAT = "lat", LNG = "lng", DESCRIPTION = "description", TIME = "time", ZOMBIE_ID = "zombieid", TYPE = "type", GENDER = "gender";

    @Override
    public Sight mapRow(ResultSet resultSet, int ID) throws SQLException {
        return new Sight(resultSet.getFloat(LAT), resultSet.getFloat(LNG), resultSet.getString(DESCRIPTION), resultSet.getTime(TIME), new Zombie(resultSet.getInt(ZOMBIE_ID),Zombie.ZombieType.valueOf(resultSet.getString(TYPE).trim()),Zombie.Gender.valueOf(resultSet.getString(GENDER).trim())));
    }
}
