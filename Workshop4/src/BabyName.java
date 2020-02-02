import java.util.Hashtable;

public class BabyName {
    private String name;
    private  String gender;

    private Hashtable maleRankings;
    private Hashtable femaleRankings;

    public BabyName(String name_, String gender_, int year, int rank)
    {
        name = name_;
        gender = gender_;
    }
                                    //update to rankinfo class {rankFemale, rank Male, }
    public void addRanking(int year, int rank)
    {
        maleRankings.put(year, rank);
    }

}
