import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FindNumberPuzzle {
    private List<String> numbers = new ArrayList<>();

    FindNumberPuzzle(List<String> numbers) {
        this.numbers = numbers;
    }

    FindNumberPuzzle(String filename){
        loadResources(filename);
    }

    public void loadResources(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidNumber(line)) {
                    numbers.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + filePath, e);
        }
    }

    private boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return str.length() >= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public String convertBiggestPuzzle(List<String> biggestPuzzle) { // перетворення масиву в стрічку

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(biggestPuzzle.getFirst());
        for (int i = 1; i < biggestPuzzle.size(); i++) {
            stringBuilder.append(biggestPuzzle.get(i).substring(2));
        }
        return stringBuilder.toString();
    }

    private String findBiggestPuzzle() { // знаходження найбільшого масиву
        List<String> biggestPuzzle = new ArrayList<>();

        for (String startNumber : numbers) { // Пробуємо всі можливі стартові числа
            List<String> candidate = findBiggestForStartValue(startNumber, new ArrayList<>(List.of(startNumber)));

            if (candidate.size() > biggestPuzzle.size()) {
                biggestPuzzle = candidate;
            }
        }
        return convertBiggestPuzzle(biggestPuzzle);
    }

    // я використала рекурсію, яка шукає найкщий варіант для певного числа з ще доступних значень
    private List<String> findBiggestForStartValue(String number, List<String> currentList) {
        List<String> possibleNext = findPossibleNext(currentList, number);
        List<String> biggestList = new ArrayList<>(currentList);

        for (String next : possibleNext) {
            List<String> newList = new ArrayList<>(currentList);
            newList.add(next);
            List<String> candidate = findBiggestForStartValue(next, newList);

            if (candidate.size() > biggestList.size()) {
                biggestList = candidate;
            }
        }
        return biggestList;
    }

    // метод, щоб визначити всі можливі наступні числа для якогось певного числа
    private List<String> findPossibleNext(List<String> currentList, String number) {
        String suffix = number.substring(number.length() - 2);
        List<String> possibleNext = new ArrayList<>();

        for (String i : numbers) {
            if (!currentList.contains(i) && i.substring(0, 2).equals(suffix)) {
                possibleNext.add(i);
            }
        }
        return possibleNext;
    }


    //Для виклику функції потрібно передати шлях до файлу з числами, або ж просто передатти список чисел
    public static void main(String[] args) throws IOException {
        //List<String> numbers = List.of("608017", "248460", "962282", "994725", "177092");
        //FindNumberPuzzle puzzle = new FindNumberPuzzle(numbers);
        FindNumberPuzzle puzzle = new FindNumberPuzzle("C:/Users/User/IdeaProjects/untitled4/src/main/resources/numbers.txt");
        System.out.println(puzzle.findBiggestPuzzle());
    }
}
