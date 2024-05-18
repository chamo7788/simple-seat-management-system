import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    public Ticket (String row, int seat, double price, Person person){
        this.row = row;
        this.seat =seat;
        this.price =price;
        this.person =person;
    }
    //Getter method
    public String getRow(){
        return row;
    }
    public int getSeat(){
        return seat;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return person;
    }

    //Setter method
    public void setRow(String row){
        this.row = row;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }
    public void setPrice(double price){
        this.price =price;
    }
    public void setPerson(Person person){
        this.person = person;
    }

    public void printInfo(){
        System.out.println("Ticket Information: ");
        System.out.println("Row: " + row);
        System.out.println("Seat: "+seat);
        System.out.println("Price: " +price);
        System.out.println("Person Information: ");
        person.printInfo();
    }

    public void save(){
        String fileName = row + seat + ".txt";

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Ticket Information: \n");
            writer.write("Row " + row + "\n");
            writer.write("Seat: " +seat+ "\n");
            writer.write("Price: " + seat+ "\n");
            writer.write("Person Information: \n");
            writer.write("Name: "+person.getName()+"\n");
            writer.write("Surname: "+person.getSurname()+"\n");
            writer.write("Email: "+ person.getEmail()+"\n");
            writer.close();
            System.out.println("Ticket information saved to "+fileName);
        }catch (IOException e){
            System.out.println("An error Occurred.");
        }
    }


}

