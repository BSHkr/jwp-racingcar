package racingcar.repository.entity;

public class WinnerEntity {
    private final long id;
    private final long gameId;
    private final long uesrId;

    public WinnerEntity(final long id, final long gameId, final long uesrId) {
        this.id = id;
        this.gameId = gameId;
        this.uesrId = uesrId;
    }

    public WinnerEntity(final long gameId, final long userId) {
        this(0, gameId, userId);
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public long getUesrId() {
        return uesrId;
    }
}
