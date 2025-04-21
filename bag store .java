import java.util.*;

public class Main {
    static String[] bags = new String[50];
    static int[] ID = new int[50];
    static int[] Price = new int[50];
    static String[] Color = new String[50];
    static String[] Brand = new String[50];
    static boolean[] deletedbags = new boolean[50];

    public static void main(String[] args) {
        String username = "admin";
        String password = "123";
        input(username, password);
        showbags();
        System.out.println("---------- Available Bags ----------");
        bags();
        Scanner scan = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------------------Store Menu-----------------");
            System.out.println("1-Show all bags\n2-Search by ID\n3-Add a bag\n4-Delete a bag\n5-Restore deleted bag\n6-Update a bag's info\n7-Rate our service\n-1-Exit");
            System.out.print("Select an operation: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    bags();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    adding();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    restoring();
                    break;
                case 6:
                    update();
                    break;
                case 7:
                    System.out.println("Rate our store (1-5):");
                    int rate = scan.nextInt();
                    if (rate > 5 || rate <= 0) {
                        System.out.println("Invalid rating!");
                    } else {
                        System.out.println("Thanks for rating our store!");
                    }
                    break;
                case -1:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while ( choice != -1 );
    }
    

    public static void input(String username, String password) {
        Scanner scan = new Scanner(System.in);
        int attempts = 3;
        while (attempts > 0) {
            System.out.println("ENTER USERNAME: ");
            String preusername = scan.next();
            System.out.println("ENTER PASSWORD: ");
            String prepassword = scan.next();
            if (preusername.equals("admin") && prepassword.equals("123")) {
                System.out.println("WELCOME TO MP STORE!");
                System.out.println("MP (since 1990) was founded by Barack Obama, our store promises you the most chic and elegant bags that you certainly won't find elsewhere.");
                break;
            }
            if (attempts > 0) {
                attempts--;
                System.out.println("INCORRECT USERNAME OR PASSWORD, Attempts left: " + attempts);
            }
            if (attempts == 0)
                System.out.println("EXITING...");
        }
    }

    public static void bags() {
        System.out.println("ID\tBrand\tColor\tPrice");
        for (int i = 0; i < ID.length; i++) {
            if (ID[i] != 0 && !deletedbags[i]) {
                System.out.println("--------------------------------");
                System.out.println(ID[i] + "\t" + Brand[i] + "\t" + Color[i] + "\t" + Price[i]);
            }
        }
    }

    public static void adding() {
        Scanner scan = new Scanner(System.in);
        System.out.print("=== Add a new bag ===");
        int i;
        boolean added = false;  
        for (i = 0; i < ID.length; i++) {
            if (ID[i] == 0) {  
                System.out.println("Please Enter the Brand: ");
                Brand[i] = scan.next();
                System.out.println("Please Enter the Color: ");
                Color[i] = scan.next();
                System.out.println("Please Enter the Price: ");
                Price[i] = scan.nextInt();
                if (Brand[i] == null || Brand[i].length() < 2 || Brand[i].length() > 20) {
                    System.out.println("ERROR: Brand name must be between 2 and 20 characters.");
                    Brand[i] = null; 
                    Color[i] = null; 
                    Price[i] = 0;   
                    
                }
      
                if (Price[i] <= 0) {
                 System.out.println("ERROR: Price must be a positive number.");
                Brand[i] = null; 
                Color[i] = null; 
                Price[i] = 0;   
             return; 
            }

            ID[i] = i + 1;  
            System.out.println("Added successfully.");
            added = true;  
            break;  
            }
        }

            if (!added) {
            System.out.println("ERROR: No space to add a new bag.");
         } else {
        validate(Brand, Price, ID);  
        }
}

    public static void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.print("=== Delete a bag by ID ===");
        System.out.println(" Please Enter The ID: ");
        int id = scan.nextInt();
        for (int i = 0; i < ID.length; i++) {
            if (ID[i] == id && !deletedbags[i]) {
                deletedbags[i] = true;
                System.out.println("Bag deleted successfully.");
                return;  
            }
        }
        System.out.println("Bag not found or already deleted.");
    }

    public static void search() {
        Scanner scan = new Scanner(System.in);
        System.out.println("==== Search a bag by ID ====");
        System.out.println("Please Enter The ID: ");
        int id = scan.nextInt();
        boolean found = false;
        for (int i = 0; i < ID.length; i++) {
            if (id == ID[i] && !deletedbags[i]) {
                System.out.println("Bag " + id);
                System.out.println("The Brand: " + Brand[i]);
                System.out.println("The Color: " + Color[i]);
                System.out.println("The Price: " + Price[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("NOT FOUND!");
        } else {
            validate(Brand, Price, ID); 
        }
    }

    public static void update() {
            Scanner scan = new Scanner(System.in);
            System.out.println("=== Update a bag by ID ===");
            System.out.println("Please enter the ID:");
            int id = scan.nextInt();
            boolean found = false;
        
            for (int i = 0; i < ID.length; i++) {
                if (ID[i] == id && !deletedbags[i]) {
                    System.out.println("Please enter the updated Brand:");
                    String updatedBrand = scan.next();
                    if (updatedBrand.length() < 2 || updatedBrand.length() > 20) {
                        System.out.println("ERROR: Brand name must be between 2 and 20 characters.");
                        return;  
                    }
        
                    System.out.println("Please enter the updated Color:");
                    Color[i] = scan.next();
        
                    System.out.println("Please enter the updated Price:");
                    int updatedPrice = scan.nextInt();
                    if (updatedPrice <= 0) {
                        System.out.println("ERROR: Price must be a positive number.");
                        return;  
                    }
        
                   
                    Brand[i] = updatedBrand;
                    Price[i] = updatedPrice;
                    System.out.println("Update successful!");
                    found = true;
                    break;
                }
            }
        
            if (!found) {
                System.out.println("Bag not found or already deleted.");
            }
        }
        
    public static void showbags() {
        int[] id = { 1, 2, 3, 4 };
        String[] brand = { "Gucci", "LV", "Dior", "coco" };
        String[] color = { "blue", "gray", "black", "black" };
        int[] price = { 200, 300, 150, 250 };
        
        for (int i = 0; i < id.length; i++) {
            ID[i] = id[i];
            Price[i] = price[i];
            Brand[i] = brand[i];
            Color[i] = color[i];
        }
        validate(Brand, Price, ID);  
    }

    public static void restoring() {
        System.out.println("==== Restore Deleted bag ====");
        System.out.print("Enter the ID of the bag to restore: ");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        boolean found = false;

        for (int i = 0; i < ID.length; i++) {
            if (ID[i] == id && deletedbags[i]) {
                deletedbags[i] = false;
                System.out.println("Bag restored.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid choice or no deleted items.");
        }
    }

    public static void validate(String[] brand, int[] price, int[] id) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] != 0) {
                if (brand[i] != null && !brand[i].isEmpty()) {
                    if (brand[i].length() < 2 || brand[i].length() > 20) {
                        System.out.println("Brand name '" + brand[i] + "' for bag ID " + id[i] + " must be between 2-20 characters.");
                    }
                } else {
                    System.out.println("Brand name is not set for bag ID " + id[i]);
                }
                if (price[i] <= 0) {
                    System.out.println("Price for bag ID " + id[i] + " must be a positive number.");
                }
            }
        }
    }
}