package dungeonmania.response.models;

import dungeonmania.util.Position;

public final class EntityResponse {
    private final int id;
    private final String type;
    private final Position position;
    private final boolean isInteractable;

    public EntityResponse(int id, String type, Position position, boolean isInteractable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
    }

    public boolean isInteractable() {
        return isInteractable;
    }

    public final int getId() {
        return id;
    }

    public final String getType() {
        return type;
    }

    public final Position getPosition() {
        return position;
    }
}
