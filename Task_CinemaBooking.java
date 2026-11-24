import java.util.*;

public class Task_CinemaBooking {

    static class Seat {
        private final char row;
        private final int number;

        public Seat(char row, int number) {
            this.row = row;
            this.number = number;
        }

        public char getRow() {
            return row;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat)) return false;
            Seat seat = (Seat) o;
            return row == seat.row && number == seat.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }

        @Override
        public String toString() {
            return "" + row + number;
        }
    }

    public static void main(String[] args) {
        Map<Seat, String> bookings = new HashMap<>();
        Set<Seat> broken = new HashSet<>();

        // Initial bookings
        bookings.put(new Seat('A', 2), "Sara");
        bookings.put(new Seat('B', 5), "Omar");
        bookings.put(new Seat('C', 3), "Mona");

        // Check if A2 and E6 are taken
        System.out.println("Is A2 taken: " + bookings.containsKey(new Seat('A', 2)));
        System.out.println("Is E6 taken: " + bookings.containsKey(new Seat('E', 6)));

        // Youssef tries to book B5
        Seat youssefSeat = new Seat('B', 5);
        if (bookings.containsKey(youssefSeat)) {
            System.out.println("Youssef -> B5: refused, taken by " + bookings.get(youssefSeat));
        } else {
            bookings.put(youssefSeat, "Youssef");
            System.out.println("Youssef -> B5: booked");
        }

        // Part E: Ali moves from seat A1 to seat D2
        bookings.remove(new Seat('A', 1));
        bookings.put(new Seat('D', 2), "Ali");
        System.out.println("After Ali moved: " + bookings);

        // Part F: seats E1 and E2 are broken
        broken.add(new Seat('E', 1));
        broken.add(new Seat('E', 2));

        Seat hanaSeat = new Seat('E', 1);
        if (broken.contains(hanaSeat)) {
            System.out.println("Hana -> E1: refused, seat is broken");
        } else {
            bookings.put(hanaSeat, "Hana");
            System.out.println("Hana -> E1: booked");
        }

        // Part G: how many seats are booked
        System.out.println("Seats booked: " + bookings.size());

        // GIVEN - prints the seating chart
        printChart(bookings, broken);
    }

    // Do not change printChart()
    static void printChart(Map<Seat, String> bookings, Set<Seat> broken) {
        char[] rows = {'A', 'B', 'C', 'D', 'E'};
        int cols = 6;

        System.out.print("    ");
        for (int c = 1; c <= cols; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (char r : rows) {
            System.out.print(r + "   ");
            for (int c = 1; c <= cols; c++) {
                Seat s = new Seat(r, c);
                if (broken.contains(s)) {
                    System.out.print("# ");
                } else if (bookings.containsKey(s)) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
        }
          
