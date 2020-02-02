public class ConnectGameDisk {

    private int color;
    private int x;
    private int y;

    public ConnectGameDisk(int playerColor, int posX, int posY)
    {
        color = playerColor;
        x = posX;
        y = posY;
    }
    public int GetColor()
    {
        return color;
    }
    public void Print()
    {
        System.out.println("disk owned by player " + color + " located at " + x + " " + y +" Added to grid");
    }

}
