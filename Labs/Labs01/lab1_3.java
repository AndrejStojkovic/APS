import java.util.Scanner;

class QuarterlySales {
    private int numOfSales;
    private int [] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getTotalRevenue()).append("   ");
        return str.toString();
    }

    public int getNumOfSales() {
        return numOfSales;
    }

    public int getRevenue(int idx) {
        if(idx < 0 || idx >= numOfSales) {
            return -1;
        }
        return revenues[idx];
    }

    public int getTotalRevenue() {
        int total = 0;
        for(int i = 0; i < numOfSales; i++) {
            total += revenues[i];
        }
        return total;
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales [] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.name).append("   ");
        int total = 0;
        for(int i = 0; i < quarters.length; i++) {
            str.append(quarters[i]);
            total += quarters[i].getTotalRevenue();
        }
        str.append(total);
        return str.toString();
    }

    public String getName() {
        return name;
    }

    public QuarterlySales [] getQuarters() {
        return quarters;
    }

    public int getTotalRevenue() {
        int total = 0;
        for(int i = 0; i < quarters.length; i++) {
            total += quarters[i].getTotalRevenue();
        }
        return total;
    }
}

public class Main {
    public static SalesPerson salesChampion(SalesPerson [] arr)
    {
        int targetIdx = -1;

        for(int i = 0; i < arr.length; i++) {
            if(targetIdx == -1 || arr[i].getTotalRevenue() > arr[targetIdx].getTotalRevenue()) {
                targetIdx = i;
            }
        }

        if(targetIdx != -1) {
            return arr[targetIdx];
        } else {
            return null;
        }
    }

    public static void table(SalesPerson [] arr)
    {
        System.out.print("SP   1   2   3   4   Total\n");
        for (SalesPerson salesPerson : arr) {
            System.out.println(salesPerson);
        }
        System.out.print("\n");
    }

    static int sumSales(SalesPerson sp) {
        int sum = 0;
        QuarterlySales [] quarters = sp.getQuarters();
        for(int i = 0; i < quarters.length; i++) {
            int revenue = 0;
            for(int j = 0; j < quarters[i].getNumOfSales(); j++) {
                revenue += quarters[i].getRevenue(j);
            }
            sum += revenue;
        }

        return sum;
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.nextLine();
        SalesPerson [] arr = new SalesPerson[n];

        for(int i=0;i < n;i++)  {
            String name = input.nextLine();


            QuarterlySales [] sales = new QuarterlySales[4];

            for(int j = 0; j < 4; j++) {
                int num = input.nextInt();
                int [] salesArr = new int[num];
                for(int k = 0; k < num; k++) {
                    salesArr[k] = input.nextInt();
                }

                sales[j] = new QuarterlySales(num, salesArr, j);
            }
            input.nextLine();

            arr[i] = new SalesPerson(name, sales);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());
    }
}