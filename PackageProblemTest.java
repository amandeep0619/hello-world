import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class PackageProblemTest {

    private static double maxPackageWeight=0;
    private static String result="";
    
    private static void printItemsList(ArrayList<Item> itemArrayList,double maxCost) {
        double totalSum = 0;
        for(int i=0;i<itemArrayList.size();i++)
        {
            totalSum = totalSum+itemArrayList.get(i).getCost();
        }
        
        // Arraylist to store the subset of items whose sum is equal to MaxCost of package
        ArrayList<Item> resArrayList = new ArrayList<>();
        
        printResultItemList(itemArrayList,resArrayList,0,0,0,maxCost);    
        
    }

    /************************************************************************
     * Method gives us all subset of items whose sum is equal to maxCost of package 
     * @param itemArrayList
     * @param resArrayList
     * @param calCost
     * @paramresultListSize
     * @param j
     * @param maxCost
     */
    private static void printResultItemList(ArrayList<Item> itemArrayList,
            ArrayList<Item> resArrayList, double calCost, int resultListSize, int j, double maxCost) {
            if(Double.compare(calCost, maxCost)==0)
            {
                printFinalresult(resArrayList,resultListSize);
            }
            else
                {
                    if(j<itemArrayList.size() && !(Double.compare(calCost+itemArrayList.get(j).getCost(), maxCost)>0) )
                    {
                        for(int i=j;i<itemArrayList.size();i++)
                        {
                            resArrayList.add(resultListSize, itemArrayList.get(i));;
                            if(!(Double.compare(calCost+itemArrayList.get(i).getCost(), maxCost)>0))
                            {
                                printResultItemList(itemArrayList  , resArrayList, calCost+itemArrayList.get(i).getCost(),resultListSize+1, j+1, maxCost);
                            }
                        }
                    }
                }
        
    }

    /**********************************************************
     * MEthod to get the required set of items from all subsets 
     * @param resArrayList
     * @param n
     ************************************************************/
    private static void printFinalresult(ArrayList<Item> resArrayList, int n) {
        double weight=0;
        String result = "";
        for(int i=0;i<n;i++)
          {
              weight = weight+resArrayList.get(i).getWeight(); 
//              System.out.print(resArrayList.get(i).getIndex());
              if(i!=0){
              result=result+","+resArrayList.get(i).getIndex();
              }
              else
              {
                  result = result+resArrayList.get(i).getIndex();
              }
              
          }
        if(Double.compare(PackageProblemTest.maxPackageWeight, 0)==0)
        {
            PackageProblemTest.maxPackageWeight = weight;
            PackageProblemTest.result = result;
        }
        else if(Double.compare(PackageProblemTest.maxPackageWeight, weight)>0)
        {
            PackageProblemTest.maxPackageWeight = weight;
            PackageProblemTest.result = result;
        }
            
//       System.out.println();
        
    }
    
    public Item getMax(Item cost1, Item cost2)
    {
        if(Double.compare(cost1.getCost(),cost2.getCost())>0)
        {
            return cost1;
        }
        else  if(Double.compare(cost1.getCost(),cost2.getCost())<0){
            return cost2;
        }
        else
        {
            if(Double.compare(cost1.getWeight(), cost2.getWeight())>0)
            {
                return cost2;
            }
            else
            {
                return cost1;
            }
        }
    }
    
    public static double returnMaxCost(double maxWeight, ArrayList<Item> itemArrayList, int n)
    {
        if(n==0 || maxWeight<=0)
        {
            return 0;
        }
        if(Double.compare(itemArrayList.get(n-1).getWeight(), maxWeight)>0)
        {
           
            return returnMaxCost(maxWeight, itemArrayList, n-1);
        }
        else{
            double result =max(itemArrayList.get(n-1).getCost()+returnMaxCost(maxWeight-itemArrayList.get(n-1).getWeight(), itemArrayList, n-1),returnMaxCost(maxWeight, itemArrayList, n-1));
            return result;
        }
    }

    private static double max(double d, double returnMaxCost) {
        if(Double.compare(d, returnMaxCost)>0)
        {
            return d;
        }
        else
        {
            return returnMaxCost;
        }
    }

    public static void main(String[] args) throws Exception
    {
        try {
            
            
            // read file PathName from console.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line  = br.readLine().trim();
            File f = new File(line);
            
            // condition to check if file exists or not
            if(f.exists())
            {
                if(f.isFile())
                {
                    //read input from file
                    br = new BufferedReader(new FileReader(f));
                    String fileLine;
                    
                    //read the file linewise
                    while((fileLine = br.readLine())!=null)
                    {
                        // parse the line into specific format
                        String[] arr = fileLine.split(" ");
                        if(arr!=null && arr.length>0)
                        {
                            double maxWeight = Double.parseDouble(arr[0]);
                            ArrayList<Item> itemArrayList = new ArrayList<>();
                            if(arr.length>2)
                            {
                                for(int i=2;i<arr.length;i++)
                                {
                                    if(arr[i].contains("("))
                                    {
                                        arr[i] = arr[i].replace("(", "");
                                    }
                                    if(arr[i].contains(")"))
                                    {
                                        arr[i] = arr[i].replace(")", "");
                                    }
                                    if(arr[i].contains("$"))
                                    {
                                        arr[i] = arr[i].replace("$", "");
                                    }
                                    String[] itemArr = arr[i].trim().split(",");
                                    if(itemArr!=null && itemArr.length>2 )
                                    {
                                        double weight = Double.parseDouble(itemArr[1]);
                                        
                                        // ignore the input whose weight is greater the maximum weight of package
                                        if(weight<=maxWeight)
                                        {
                                            //Our Class Item contains Index, weight and cost of item
                                                Item item = new Item(Long.parseLong(itemArr[0]), Double.parseDouble(itemArr[1]), Double.parseDouble(itemArr[2]));
                                                itemArrayList.add(item);
                                        }
                                    }
                                }
                            }
                            
                            // we are using knapsack 01 problem logic to find the maxCost in which use are using maxWeight of package as size of knapsack
                            double maxCost = returnMaxCost(maxWeight, itemArrayList, itemArrayList.size());
//                            System.out.println("------------ "+ maxWeight +" ------------------");
                            
                            /*******************************************************************************************************************
                             * once we get the maxCost for package using max Weight as knapsack
                             * we are finding the subset of items whose sum of cost is equal to maxCost which we getting from knapsack problem
                             *******************************************************************************************************************/
                            printItemsList(itemArrayList,maxCost);
                            
                            if(PackageProblemTest.result.equals(""))
                            {
                                System.out.println("-");
                            }
                            else
                            {
                            System.out.println(PackageProblemTest.result);
                            }
                        }
                        
                    }
                }
                else
                {
                    System.out.println("Enter a valid File Name");
                }
            }
            else
            {
                System.out.println("Invalid Path");
            }
                
            
        }
        catch(NumberFormatException numberFormatException)
        {
            numberFormatException.printStackTrace();
            throw new NumberFormatException();
        }
        catch(FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
            throw new FileNotFoundException();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
    }

  

}
