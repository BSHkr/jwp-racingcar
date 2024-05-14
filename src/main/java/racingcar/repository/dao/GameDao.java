package racingcar.repository.dao;

import racingcar.repository.entity.GameEntity;

import java.util.List;

public interface GameDao {

    long save(final GameEntity gameEntity);

    List<GameEntity> findAll();
}
