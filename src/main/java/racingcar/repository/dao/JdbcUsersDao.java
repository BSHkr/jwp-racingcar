package racingcar.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import racingcar.repository.entity.PositionEntity;
import racingcar.repository.entity.UsersEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Objects;

public class JdbcUsersDao implements UsersDao{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UsersEntity> actorRowMapper = (resultSet, rowNum) -> new UsersEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    public JdbcUsersDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(UsersEntity usersEntity) {
        String sql = "INSERT INTO users (name) VALUES (?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usersEntity.getName());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public UsersEntity findById(long id) {
        String sql = "select id, name from users where id = ?";

        return jdbcTemplate.queryForObject(sql, actorRowMapper, id);
    }

    @Override
    public UsersEntity findByName(String name) {
        String sql = "select id, name from users where name = ?";

        return jdbcTemplate.queryForObject(sql, actorRowMapper, name);
    }
}
