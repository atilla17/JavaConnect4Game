import java.io.IOException;

public class Workshop4 {


    public static void main(String[] args)
    {
        System.out.println("hi");
        ConnectGame game = new ConnectGame();

        game.StartGame();
        BabyNames n = new BabyNames();
        n.readNameSpecific();

       
    }


}
