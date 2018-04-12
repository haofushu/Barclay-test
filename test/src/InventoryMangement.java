import java.util.*;

//create Tab01 57.00 84.98
//updateBuy Tab01 100
//        updateSell Tab01 2
//report
//        delete Book01

public class InventoryMangement {
    public static void main(String[] args){
            Map<String,Inventory> ls = new HashMap<>();
            double profit = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("please enter the input:(enter '#' to quit) ");

            while(sc.hasNext()){
                String[] s = sc.nextLine().trim().split(" ");
                if(s[0].equals("#")) break;
                switch(s[0]){
                    case "create":
                        ls.put(s[1],create(s));
                        break;
                    case "updateBuy":
                        updateBuy(ls,s);
                        break;
                    case "updateSell":
                        profit += updateSell(ls,s);
                        break;
                    case "report":
                        report(ls,profit);
                        profit = 0;
                        break;
                    case "delete":
                        profit += delete(ls,s);
                        break;
                    case "updateSellPrice":
                        updatePrice(ls,s);
                        break;
                    default:
                        System.out.println("wrong input");
                }
            }
            sc.close();
    }

    private static void updatePrice(Map<String, Inventory> ls, String[] s) {
        if(s.length != 2 || !ls.containsKey(s[1]) || !isNumaric(s[2])){
            System.out.println("wrong input update price");
            return;
        }
        ls.get(s[1]).setSold_at(Double.parseDouble(s[2]));
    }

    private static double delete(Map<String, Inventory> ls, String[] s) {
        if(s.length != 2 || !ls.containsKey(s[1])){
            System.out.println("wrong input delete");
            return 0;
        }
        double profit = 0-ls.get(s[1]).getValue();
        ls.remove(s[1]);

        return profit;
    }

    private static double updateSell(Map<String, Inventory> ls, String[] s) {
        if(s.length != 3 || !ls.containsKey(s[1]) || !isNumaric(s[2])){
            System.out.println("wrong input updatesell");
            return 0;
        }
        double profit =0;
        int v = Integer.parseInt(s[2]);
        Inventory i = ls.get(s[1]);
        if(i.getQty() < v) {
            System.out.println("wrong sell number");
            return 0;
        }
        i.setQty(i.getQty()-v);
        ls.put(s[1],i);
        profit = v*(i.getSold_at()-i.getBought_at());
        return profit;
    }

    public static Inventory create(String[] s){
            if (s.length != 4 || !isNumaric(s[2]) || !isNumaric(s[3])) {
                System.out.println("wrong input create");
                return null;
            }
            Inventory tmp = new Inventory(s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]));
            return tmp;
    }

    public static void updateBuy(Map<String,Inventory> ls, String[] s){
            if(s.length != 3 || !ls.containsKey(s[1]) || !isNumaric(s[2])){
                System.out.println("wrong input updateBuy");
                return;
            }
            int v = Integer.parseInt(s[2]);
            Inventory i = ls.get(s[1]);
            i.setQty(i.getQty()+v);
            ls.put(s[1],i);
    }

    public static void report(Map<String,Inventory> ls,double profit){
        System.out.println("INVENTORY REPORT:");
        if(ls.isEmpty()||ls ==null) return;
        double value = 0;
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s" ,
                "Item Name", "Bought At", "Sold At", "AvailableQty", "Value"));
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s",
                "---------","---------","-------","------------","-------"));
            for(Inventory i : ls.values()){
                System.out.println(String.format("%-15s %-15.2f %-15.2f %-15s %-15.2f" ,
                        i.getName(), i.getBought_at(), i.getSold_at(), i.getQty(), i.getValue()));
                value += i.getValue();
            }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(String.format("%-15s %55.2f","Total value", value));
        System.out.println(String.format("%-10s %40.2f","Profit since previous report", profit));
        System.out.println("");
    }
    public static boolean isNumaric(String s){
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

}
//    create Book01 10.50 13.79
//        create Food01 1.47 3.98
//        create Med01 30.63 34.29
//        create Tab01 57.00 84.98
//        updateBuy Tab01 100
//        updateSell Tab01 2
//        updateBuy Food01 500
//        updateBuy Book01 100
//        updateBuy Med01 100
//        updateSell Food01 1
//        updateSell Food01 1
//        updateSell Tab01 2
//        report
//        delete Book01
//        updateSell Tab01 5
//        create Mobile01 10.51 44.56
//        updateBuy Mobile01 250
//        updateSell Food01 5
//        updateSell Mobile01 4
//        updateSell Med01 10
//        report
//        #
