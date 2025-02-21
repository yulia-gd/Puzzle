import java.util.ArrayList;
import java.util.List;

public class PuzzleSolver {

    public static String findLongestChain(List<String> numbers) {
        List<String> bestChain = new ArrayList<>();

        // Пробуємо кожен елемент як початок
        for (int i = 0; i < numbers.size(); i++) {
            List<String> chain = new ArrayList<>();
            List<String> remainingNumbers = new ArrayList<>(numbers);
            chain.add(remainingNumbers.get(i)); // Починаємо з i-го числа
            remainingNumbers.remove(i);

            while (!remainingNumbers.isEmpty()) {
                boolean found = false;
                for (int j = 0; j < remainingNumbers.size(); j++) {
                    String lastTwoOfChain = chain.get(chain.size() - 1).substring(chain.get(chain.size() - 1).length() - 2);
                    String firstTwoOfNum = remainingNumbers.get(j).substring(0, 2);

                    // Перевіряємо можливість з'єднання
                    if (lastTwoOfChain.equals(firstTwoOfNum)) {
                        chain.add(remainingNumbers.get(j));
                        remainingNumbers.remove(j);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    break;
                }
            }

            // Якщо поточний ланцюг довший за попередній, зберігаємо його
            if (chain.size() > bestChain.size()) {
                bestChain = new ArrayList<>(chain);
            }
        }

        // Повертаємо з'єднаний ланцюг
        StringBuilder result = new StringBuilder();
        for (String s : bestChain) {
            result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> numbers = new ArrayList<>();
        numbers.add("608017");
        numbers.add("248460");
        numbers.add("962282");
        numbers.add("994725");
        numbers.add("177092");
        numbers.add("928022");
        numbers.add("928034");
        numbers.add("348035");
    List<String> numbers2 = new ArrayList<>();
        numbers2.add("248460");
        numbers2.add("962282");
        numbers2.add("994725");
        numbers2.add("177092");
        numbers2.add("928034");
        numbers2.add("928022");
        numbers2.add("348035");

        String result = findLongestChain(numbers);
        String result2 = findLongestChain(numbers2);
        System.out.println(result.length());
        System.out.println(result2.length());
        System.out.println(result);  // Виведе 24846080177092
        System.out.println(result2);
    }
}
