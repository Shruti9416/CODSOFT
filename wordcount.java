import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class countwords{
    public static void main(String[] args) {
        String text = getUserInput();
        if (text != null) {
            int totalWords = countWords(text);
            System.out.println("Total number of words: " + totalWords);
            
        
        }
    }
    
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text or provide a file path: ");
        String input = scanner.nextLine();
        scanner.close();
        
        String text = null;
        
        // Check if the input is a file path
        File file = new File(input);
        if (file.exists()) {
            try {
                text = readFile(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + input);
            }
        } else {
            text = input;
        }
        
        return text;
    }
    
    public static String readFile(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            sb.append(System.lineSeparator());
        }
        
        scanner.close();
        return sb.toString();
    }
    
    public static int countWords(String text) {
        String[] words = text.split("[\\p{Punct}\\s]+");
        return words.length;
    }
    
    
}
