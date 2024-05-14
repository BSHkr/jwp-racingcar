package racingcar.repository.dao;

import racingcar.repository.entity.PositionEntity;

import java.util.List;

public interface PositionDao {
    long save(PositionEntity positionEntity);

    List<PositionEntity> findByGameId(final long gameId);
}
