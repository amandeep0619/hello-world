
public class RoboPath {
    
    public int returnNoOfUniquePath()
    {
        int[][] countPaths = new int[4][4];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(i==0 || j==0)
                {
                    countPaths[i][j]=1;
                }
                else{
                countPaths[i][j]=0;
                }
            }
        }
        
        for(int i=1;i<4;i++)
        {
            for(int j=1;j<4;j++)
            {
                countPaths[i][j]=countPaths[i][j-1]+countPaths[i-1][j];
            }
        }
        return countPaths[3][3];
    }
    public static void main(String[] args)
    {
        RoboPath objRoboPath = new RoboPath();
        System.out.println(objRoboPath.returnNoOfUniquePath());
                
    }
}
