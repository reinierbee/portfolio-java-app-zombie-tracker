package zombies.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zombies.domain.Zombie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ZombieMapper implements RowMapper<Zombie> {
    public static final String ID = "id", TYPE = "type", GENDER = "gender";

    @Override
    public Zombie mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Zombie(resultSet.getInt(ID),Zombie.ZombieType.valueOf(resultSet.getString(TYPE).trim()),Zombie.Gender.valueOf(resultSet.getString(GENDER).trim()));
    }
}
