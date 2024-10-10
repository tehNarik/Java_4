import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // Створюємо літери для слів
        Letter[] helloLetters = { new Letter('H'), new Letter('e'), new Letter('l'), new Letter('l'), new Letter('o') };
        Letter[] worldLetters = { new Letter('W'), new Letter('o'), new Letter('r'), new Letter('l'), new Letter('d') };
        Letter[] helloAgainLetters = { new Letter('H'), new Letter('e'), new Letter('l'), new Letter('l'), new Letter('o') };

        Word hello = new Word(helloLetters);
        Word world = new Word(worldLetters);
        Word helloAgain = new Word(helloAgainLetters);
        PunctuationMark exclamation = new PunctuationMark('!');

        // Створюємо речення з цих слів та розділового знака
        SentencePart[] sentenceParts = { hello, world, helloAgain, exclamation };
        Sentence sentence = new Sentence(sentenceParts);

        // Створюємо текст із речень
        Sentence[] sentences = { sentence };
        Text text = new Text(sentences);
        System.out.println("Текст до сортування: " + text);
        // Викликаємо метод для друку унікальних слів в алфавітному порядку
        System.out.print("Текст після сортування: ");
        printUniqueWords(text);
    }

    // Метод для виведення слів без повторень у алфавітному порядку
    public static void printUniqueWords(Text text) {
        Set<String> uniqueWords = new TreeSet<>();  // TreeSet забезпечує сортування та унікальність

        // Перебираємо всі речення тексту
        for (Sentence sentence : text.getSentences()) {
            for (SentencePart part : sentence.getSentenceParts()) {
                if (part instanceof Word) {
                    uniqueWords.add(part.toString());  // Додаємо слово до TreeSet
                }
            }
        }

        // Друкуємо відсортовані унікальні слова
        for (String word : uniqueWords) {
            System.out.print(word + " ");
        }
    }
}

class Letter {
    private final char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return Character.toString(letter);
    }
}

interface SentencePart {}

class PunctuationMark implements SentencePart {
    private final char mark;

    public PunctuationMark(char mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}

class Word implements SentencePart {
    private final Letter[] letters;

    public Word(Letter[] letters) {
        this.letters = letters;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Letter el : letters) {
            res.append(el);
        }
        return res.toString();
    }
}

class Sentence {
    private final SentencePart[] sentenceParts;

    public Sentence(SentencePart[] sentenceParts) {
        this.sentenceParts = sentenceParts;
    }

    public SentencePart[] getSentenceParts() {
        return sentenceParts;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < sentenceParts.length; i++) {
            res.append(sentenceParts[i]);

            // Якщо це не останній елемент і наступний елемент не є розділовим знаком, додаємо пробіл
            if (i < sentenceParts.length - 1 && !(sentenceParts[i + 1] instanceof PunctuationMark)) {
                res.append(" ");
            }
        }
        return res.toString();
    }
}

class Text {
    private final Sentence[] sentences;

    public Text(Sentence[] sentences) {
        this.sentences = sentences;
    }

    public Sentence[] getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Sentence el : sentences) {
            res.append(el).append(" ");
        }
        return res.toString().trim();  // Обрізаємо зайвий пробіл наприкінці тексту
    }
}
