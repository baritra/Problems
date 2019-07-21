package topcoder;

public class BookNumbering {

    public int numberOfBooks(int usedDigits) {
        int totalBooks = 0;
        int digitLeft = usedDigits;
        int curDigitRequiredForEach = 1;
        int upperBound = 9;

        while (true) {
            if (digitLeft == 0) {
                return totalBooks;
            }
            int books = digitLeft/curDigitRequiredForEach;
            if (books == 0 && digitLeft != 0) {
                return -1;
            }
            if ((totalBooks + books) <= upperBound) {
                totalBooks += books;
                digitLeft -= books*curDigitRequiredForEach;

            } else {
                books = (upperBound - totalBooks);
                totalBooks += books;
                digitLeft -= books*curDigitRequiredForEach;

                upperBound = upperBound*10 + 9;
                curDigitRequiredForEach++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new BookNumbering().numberOfBooks(1863927342));
    }
}
