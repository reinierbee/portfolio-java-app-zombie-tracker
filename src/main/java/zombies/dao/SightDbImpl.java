package zombies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import zombies.dao.mapper.SightMapper;
import zombies.dao.mapper.ZombieMapper;
import zombies.domain.Sight;

import javax.sql.DataSource;
import java.util.List;

import static zombies.dao.mapper.SightMapper.*;

@Component
public class SightDbImpl implements SightDao {

    private static final String SELECT_ALL = "SELECT * FROM public.sight, public.zombie WHERE public.zombie." + ZombieMapper.ID + " = public.sight." + ZOMBIE_ID ;
    private static final String INSERT = "INSERT INTO public.sight (" + LAT + ", " + LNG + ", "+ DESCRIPTION +", "+ TIME +", "+ ZOMBIE_ID +") VALUES (:" + LAT + ", :" + LNG + ", :" + DESCRIPTION+ ", :" + TIME +", :" + ZOMBIE_ID +")";
    private static final String DELETE = "DELETE FROM public.sight WHERE zombieid=:" + ZOMBIE_ID;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SightMapper sightMapper;

    @Autowired
    public SightDbImpl(final DataSource dataSource, final SightMapper sightMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.sightMapper = sightMapper;
    }

    @Override
    public Sight addSight(Sight sight) {
        final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue(LAT, sight.getLat());
        parameterMap.addValue(LNG, sight.getLng());
        parameterMap.addValue(DESCRIPTION, sight.getDescription());
        parameterMap.addValue(TIME, sight.getTime());
        parameterMap.addValue(ZOMBIE_ID, sight.getZombie().getId());

        int rowsAffected = jdbcTemplate.update(INSERT, parameterMap);
        if(rowsAffected == 1){
            return sight;
        } else {
            throw new DataAccessResourceFailureException(String.format("Rows affected should be 1, but was: %s", rowsAffected));
        }

    }

    @Override
    public List<Sight> get() {
        return jdbcTemplate.query(SELECT_ALL, sightMapper);
    }

    @Override
    public void delete(int zombieid) {
        final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue(ZOMBIE_ID, zombieid);

        int rowsAffected = jdbcTemplate.update(DELETE, parameterMap);
        if (rowsAffected == 1) {
//            return Optional.of(new Zombie(id));
        }
    }
}