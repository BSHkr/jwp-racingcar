package racingcar.repository.dao;

import racingcar.repository.entity.WinnerEntity;

import java.util.List;

public interface WinnerDao {
    long save(WinnerEntity winnerEntity);

    List<WinnerEntity> findByGameId(final long gameId);
}
