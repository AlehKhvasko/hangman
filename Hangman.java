package hangman;

import java.util.Random;
import java.util.Scanner;

/**
 * 2* Создать массив из слов
 * String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
 * "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
 * "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
 * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
 * сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
 * apple – загаданное
 * apricot - ответ игрока
 * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
 * Угаданные в прошлые ответы буквы запоминать не надо. То есть при следующем ответе:
 * carpet (ковер, не фрукт, но это всего лишь пример), будет выведено:
 * ####e##########
 *
 * Для сравнения двух слов посимвольно можно пользоваться:
 * String str = "apple";
 * char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
 * Играем до тех пор, пока игрок не отгадает слово.
 * Используем только буквы в нижнем регистре.
 */
public class Hangman {
    public static void main(String[] args) {
        hangmanStartGame();
    }

    public static void hangmanStartGame(){
        System.out.println("Welcome to 'Hangman'.");
        String randomWord = randomWord();
        boolean gameIsOn = true;
        String hidedWord = "###############";
        System.out.println(hidedWord);
        int guessedLetters = 0;
        while(gameIsOn) {
            String usersAnswer = askUser();
            //System.out.println(hidedWord);
            String[] checkedAnswer = checkLetter(usersAnswer, randomWord, hidedWord);
            hidedWord = checkedAnswer[0];
            guessedLetters += Integer.parseInt(checkedAnswer[1]);
            System.out.printf("You guessed %d letter(s) \n", guessedLetters);
            System.out.println(hidedWord);
            if (randomWord.length() == guessedLetters){
                System.out.println("Congratulation you won!");
                gameIsOn = false;
            }
        }

    }


    public static String[] checkLetter(String usersWord, String computersWord, String hidedWord){
        char[] charWordToGuess = hidedWord.toCharArray();
        int guessedLetters = 0;
        for (int i = 0; i < computersWord.length(); i++) {
            for (int j = 0; j < usersWord.length(); j++) {
                if (computersWord.charAt(i) == usersWord.charAt(j)){
                    charWordToGuess[i] = computersWord.charAt(i);
                    guessedLetters++;
                }
            }
        }

        String wordToGuess = "";
        for (char el: charWordToGuess) {
            wordToGuess += el;
        }
        String letters = guessedLetters + "";
        String[] arr = {wordToGuess, letters};
        return arr;
    }

    public static String randomWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot","cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea","peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        Random random = new Random();
        int randomNumber = random.nextInt(words.length );
        System.out.println("Computer picked a word.");
        return words[randomNumber];
    }

    public static String askUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Try to guess a letter or a word.:>");
        return scanner.nextLine();
    }
}
