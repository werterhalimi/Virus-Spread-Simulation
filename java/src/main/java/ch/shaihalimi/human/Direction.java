package ch.shaihalimi.human;

public enum Direction {
    NORTH(0,-1),
    NORTH_EST(1,-1),
    NORTH_WEST(-1,-1),
    SOUTH(0,1),
    SOUTH_EST(1,1),
    SOUTH_WEST(-1,1),
    WEST(-1,0),
    EST(1,0);

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction getOpposite(){
        for (Direction value : Direction.values())
            if (value.getX()==this.x*-1 && value.getY()==this.y*-1) return value;
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
