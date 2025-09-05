import java.util.*;

public class ParkingSystem {

    static final int MAX = 20;  // max parking slots
    static ArrayList<Integer> vehicleList = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== MKCE Car & Bike Parking =====");
            System.out.println("Available slots: " + (MAX - count));
            System.out.println("1. Park Vehicle");
            System.out.println("2. Take Off Vehicle");
            System.out.println("3. Show All Vehicles");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (count < MAX) {
                        System.out.print("Enter vehicle number: ");
                        int vnum = sc.nextInt();
                        System.out.print("Enter hours: ");
                        int hour = sc.nextInt();

                        vehicleList.add(vnum);
                        count++;
                        System.out.println("Vehicle parked successfully.");
                        System.out.println("Payment: " + (hour * 10) + " Rupees");
                    } else {
                        System.out.println("Parking FULL!");
                    }
                    break;

                case 2:
                    if (count > 0) {
                        System.out.print("Enter vehicle number to remove: ");
                        int removeNum = sc.nextInt();

                        // 🔹 Linear Search
                        int pos = linearSearch(vehicleList, removeNum);

                        if (pos != -1) {
                            vehicleList.remove(pos);
                            count--;
                            System.out.println("Vehicle removed. Safe journey!");
                        } else {
                            System.out.println("Vehicle not found.");
                        }
                    } else {
                        System.out.println("Parking is empty!");
                    }
                    break;

                case 3:
                    if (count == 0) {
                        System.out.println("No vehicles parked.");
                    } else {
                        System.out.println("Vehicles currently parked:");
                        recursivePrint(vehicleList, 0);
                    }
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    // 🔹 Linear Search
    static int linearSearch(ArrayList<Integer> list, int target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                return i;
            }
        }
        return -1;
    }

    // 🔹 Binary Search (for sorted list only)
    static int binarySearch(ArrayList<Integer> list, int target) {
        Collections.sort(list);  // ensure sorted
        int left = 0, 
        right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) == target) return mid;
            else if (list.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // 🔹 Recursion: Print all vehicles
    static void recursivePrint(ArrayList<Integer> list, int index) {
        if (index >= list.size()) return;
        System.out.println("Slot " + (index + 1) + " -> Vehicle: " + list.get(index));
        recursivePrint(list, index + 1);
    }
}
