import java.util.*;
public class w2051728_PlaneManagement {
    //creating the arrays out of the main function to get access easily
    public static int[] row_A = {0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //creating 1st row with 14 seats
    public static int[] row_B = {0,0,0,0,0,0,0,0,0,0,0,0}; //creating 1st row with 14 seats
    public static int[] row_C = {0,0,0,0,0,0,0,0,0,0,0,0}; //creating 1st row with 14 seats
    public static int[] row_D = {0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //creating 1st row with 14 seats


    public static Ticket[] soldTickets = new Ticket[50];
    public static int ticketIndex = 0;

    public static void main(String[] args) {
        System.out.println("‘Welcome to the Plane Management application");
        Scanner input = new Scanner(System.in);
        
        //Menu
        boolean existState = false;
        while (!existState){
            System.out.println("*********************************************");
            System.out.println("*                MENU OPTIONS               *");
            System.out.println("*********************************************");
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Print tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("**********************************************");
            System.out.println("Please select an option: ");

            int choice = input.nextInt();
            if (choice == 0){
                existState = true;
            }

            switch (choice){
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_information();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Good bye...!");
                    break;
                default:
                    return;
            }
        }
    }
    public static void buy_seat(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a row letter (A-D): ");
        char row_letter = input.next().charAt(0);
        if (row_letter< 'A' || row_letter> 'D'){
            System.out.println("Invalid row. Please try again.");
            return;
        }
        System.out.println("Please enter a seat number: ");
        int seat = input.nextInt();

        if ((row_letter == 'A' && seat > 14 ) || (row_letter == 'B' && seat > 12) || (row_letter =='C' && seat > 12) || (row_letter == 'D' && seat > 14)){
                System.out.println("Invalid seat number. Pleas try again");
                return;
        }
        // Checking seats availability
        int[] selected_row = null;
        switch (row_letter){
            case 'A':
                selected_row = row_A;
                break;
            case 'B':
                selected_row = row_B;
                break;
            case 'C':
                selected_row = row_C;
                break;
            case 'D':
                selected_row = row_D;
                break;
            default:
                System.out.println("Invalid row. Please try again.");
        }
        if (selected_row[seat-1] == seat){
            System.out.println("Seat already taken.");
        }else {
            System.out.println("Seat " + seat + " in row " + row_letter + " has been reserved.");
        }


        if (row_letter == 'A'){
            selected_row[seat-1] = seat;
        }else if (row_letter == 'B'){
            selected_row[seat-1] = seat;
        }else if (row_letter == 'C'){
            selected_row[seat-1] =seat;
        }else
            selected_row[seat-1]=seat;

        System.out.println("Please enter your name: ");
        String name = input.next();
        System.out.println("Please enter your surname: ");
        String surname = input.next();
        System.out.println("Please enter your email: ");
        String email = input.next();

        //create person object
        Person person = new Person(name, surname, email);

        //calculate ticket price
        double price;
        if (seat<= 5){
            price = 200;
        }else if (seat<=9){
            price = 150;
        }else {
            price = 180;
        }

        //create a ticket object
        Ticket ticket = new Ticket(String.valueOf(row_letter), seat, price, person);

        ticket.save();
        soldTickets[ticketIndex] = ticket;
        ticketIndex++;

        System.out.println(price);
        System.out.println(Arrays.toString(row_A));
        System.out.println("Seat successfully reserved");
        System.out.println("1) Go to main menu ");
        System.out.println("2) Quit");

        int option = input.nextInt();
        switch (option){
            case 1:
                return;
            case 2:
                break;
            default:
                System.out.println("Invalid Input.");
        }
    }

    public static void cancel_seat(){
        System.out.println("-----Cancel a ticket-----");
        System.out.println();

        //get which row seat user to cancel
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your row letter:");
        char row = input.next().charAt(0);
        if (row< 'A' || row> 'D'){
            System.out.println("Invalid row. Please try again.");
            return;
        }

        int[] selected_row =null;
        switch (row){
            case 'A':
                selected_row = row_A;
                break;
            case 'B':
                selected_row =row_B;
                break;
            case 'C':
                selected_row = row_C;
                break;
            case 'D':
                selected_row = row_D;
                break;
            default:
                System.out.println("Invalid row.");
        }
        //Ask user to enter the seat no. that wanted to cancel
        System.out.println("Enter seat number(1-"+selected_row.length + "): ");
        int seat = input.nextInt();
        if (seat<1 || seat >selected_row.length){
            System.out.println("Invalid seat number.");
            return;
        }

        //cancel the seat
        if (selected_row[seat-1] == 0){
            System.out.println("Seat is already available.");
            return;
        }
        // access the array and access the row
        int ticketIndexToRemove = -1;
        for (int i = 0; i < ticketIndex; i++) {
            Ticket ticket =soldTickets[i];
            if (ticket != null && ticket.getRow().charAt(0) == row && ticket.getSeat() == seat){
                ticketIndexToRemove = i;
                break;
            }

        }
        if (ticketIndexToRemove != -1){
            for (int i = ticketIndexToRemove; i <ticketIndex-1 ; i++) {
                soldTickets[i] = soldTickets[i+1];
            }
            soldTickets[ticketIndex -1] = null;
            ticketIndex--;
            if (row == 'A'){
                selected_row[seat-1] = 0;
            }else if (row == 'B'){
                selected_row[seat-1] = 0;
            }else if (row == 'C'){
                selected_row[seat-1] = 0;
            }else
                selected_row[seat-1]= 0;
            selected_row[seat-1] =0;
            System.out.println("Ticket for row " + row + ",seat " + seat + " has been cancelled.");
        }else {
            System.out.println("Ticket not found. ");
        }

    }
    public static void find_first_available(){
        char rowLetter = 'A';

        while (rowLetter <= 'D'){
            int[] selectedRow = null;
            switch (rowLetter){
                case 'A':
                    selectedRow = row_A;
                    break;
                case 'B':
                    selectedRow =row_B;
                    break;
                case 'C':
                    selectedRow=row_C;
                    break;
                case 'D':
                    selectedRow=row_D;
                    break;
            }
            int availableSeat = 0;
            for (int i = 0; i < selectedRow.length; i++) {
                if (selectedRow[i] == 0){
                    availableSeat = i+1;
                    break;
                }

            }
            if (availableSeat != 0){
                System.out.println("The first available seat is in row "+ rowLetter+ " seat " + availableSeat);
                return;
            }
            rowLetter++;
        }
        System.out.println("Sorry there are no available seats.");

    }
    public static void show_seating_plan(){
        System.out.println("Seating Plan: ");
        System.out.print("A:  ");
        for (int seat : row_A){
            if (seat == 0){
                System.out.print("O ");
            }else {
                System.out.print("X ");
            }
        }
        System.out.println();

        System.out.print("B:  ");
        for (int seat : row_B){
            if (seat == 0){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();

        System.out.print("C:  ");
        for (int seat :row_C){
            if (seat == 0){
                System.out.print("O ");
            }else {
                System.out.print("X ");
            }
        }
        System.out.println();

        System.out.print("D:  ");
        for (int seat : row_D){
            if (seat == 0){
                System.out.print("O ");
            }else {
                System.out.print("X ");
            }
        }
        System.out.println();
    }
    //print ticket information
    public static void print_tickets_information(){
        double totalSales = 0.0;

        for (int i = 0; i < ticketIndex; i++) {
            Ticket ticket = soldTickets[i];
            if (ticket != null){
                ticket.printInfo();
                totalSales += ticket.getPrice();
            }
        }

        System.out.println("Total sales: " + totalSales);

    }

    //Search Tickets
    public static void search_ticket(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter row letter (A-D): ");
        char row = input.next().toUpperCase(Locale.ROOT).charAt(0);
        if (row< 'A' || row> 'D'){
            System.out.println("Invalid row. Please try again.");
            return;
        }
        System.out.println("Enter seat number: ");
        int seat = input.nextInt();

        if ((row == 'A' && seat > 14 ) || (row == 'B' && seat > 12) || (row =='C' && seat > 12) || (row == 'D' && seat > 14)){
            System.out.println("Invalid seat number. Pleas try again");
            return;
        }
        try {
            for (Ticket ticket : soldTickets){
                if (ticket.getRow().charAt(0) == row && ticket.getSeat() == seat){
                    System.out.println("Ticket found: ");
                    ticket.printInfo();
                    return;
                }
            }

        }catch (NullPointerException e) {
            System.out.println("This seat is available.");
        }



    }

}


