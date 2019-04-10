package zombies.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import zombies.dao.mapper.ZombieMapper;
import zombies.domain.Zombie;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static zombies.dao.mapper.ZombieMapper.*;

@Component
public class ZombieDbImpl implements ZombieDao {

    private static final String SELECT_ID = "SELECT * FROM public.zombie WHERE id=:" + ID;
    private static final String SELECT_ALL = "SELECT * FROM public.zombie";
    private static final String INSERT = "INSERT INTO public.zombie (" + TYPE + ", "+ GENDER +") VALUES (:" + TYPE + ", :"+ GENDER +")";
    private static final String UPDATE = "UPDATE public.zombie SET (" + TYPE + ", "+ GENDER +") = (:" + TYPE + ", :"+ GENDER +") WHERE id=:" + ID;
    private static final String DELETE = "DELETE FROM public.zombie WHERE id=:" + ID;
    private static final String DELETE_SIGHTING = "DELETE FROM public.sight WHERE zombieid=:" + ID;

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ZombieMapper zombieMapper;

    @Autowired
    public ZombieDbImpl(final DataSource dataSource, final ZombieMapper zombieMapper){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.zombieMapper = zombieMapper;
    }

    @Override
    public Zombie addZombie(Zombie zombie) {
        final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue(TYPE, zombie.getType().toString());
        parameterMap.addValue(GENDER , zombie.getGender().toString());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(INSERT, parameterMap, keyHolder, new String[]{ID});
        if(rowsAffected == 1){
            return new Zombie(keyHolder.getKey().intValue(),zombie.getType(), zombie.getGender());
        } else {
            throw new DataAccessResourceFailureException(String.format("Rows affected should be 1, but was: %s", rowsAffected));
        }
    }

    @Override
    public List<Zombie> get() {

        List<Zombie> zombies = jdbcTemplate.query(SELECT_ALL,zombieMapper);
        return zombies;
    }

    @Override
    public Zombie getZombie(int id) {
        final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue(ID, id);

        Zombie zombie = jdbcTemplate.queryForObject(SELECT_ID, parameterMap, zombieMapper);
        return zombie;
    }

   @Override
    public void delete(int id) {
       final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
       parameterMap.addValue(ID, id);

       jdbcTemplate.update(DELETE, parameterMap);
       jdbcTemplate.update(DELETE_SIGHTING, parameterMap);
   }


    @Override
    public Optional<Zombie> modify(int id, Zombie.Gender gender, Zombie.ZombieType zombieType) {
        final MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue(ID, id);
        parameterMap.addValue(TYPE, zombieType.toString());
        parameterMap.addValue(GENDER, gender.toString() );

        int rowsAffected = jdbcTemplate.update(UPDATE, parameterMap);
        if (rowsAffected == 1) {
            return Optional.of(new Zombie(id, zombieType,gender));
        }
        return Optional.empty();
    }
}
