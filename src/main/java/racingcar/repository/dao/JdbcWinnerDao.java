package racingcar.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import racingcar.repository.entity.WinnerEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class JdbcWinnerDao implements WinnerDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<WinnerEntity> actorRowMapper = (resultSet, rownum) -> new WinnerEntity(
            resultSet.getLong("id"),
            resultSet.getLong("gameId"),
            resultSet.getLong("userId")
    );

    public JdbcWinnerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(WinnerEntity winnerEntity) {
        final String sql = "INSERT INTO winner (gameId, userId) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(Connection -> {
            final PreparedStatement preparedStatement = Connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, winnerEntity.getGameId());
            preparedStatement.setLong(2, winnerEntity.getUesrId());

            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<WinnerEntity> findByGameId(long gameId) {
       final String sql = "SELECT id, gameId, userId "
               + "FROM winner "
               + "WHERE game_id = ?";

        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
