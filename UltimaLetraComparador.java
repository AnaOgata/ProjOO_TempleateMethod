import java.util.Arrays;
import java.util.Comparator;

/**Exercício 16.1**/
public class UltimaLetraComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        char ultima1 = s1.charAt(s1.length() - 1);
        char ultima2 = s2.charAt(s2.length() - 1);

        if (ultima1 > ultima2) return 1;
        if (ultima1 < ultima2) return -1;
        return 0;
    }

    public static void main(String[] args) {
        String[] palavras = {"banana", "abacaxi", "uva", "kiwi", "limão", "manga", "caju", "pera", "melão", "figo"};

        System.out.println(Exercício 16.1 - Ordenação pela Última Letra\n");

        System.out.println("Antes da ordenação:");
        System.out.println(Arrays.toString(palavras));

        // Arrays.sort é o Template Method — ele chama compare() do nosso Comparator
        Arrays.sort(palavras, new UltimaLetraComparator());

        System.out.println("\nDepois da ordenação pela última letra:");
        System.out.println(Arrays.toString(palavras));

        System.out.println("\nDetalhado (palavra -> última letra):");
        for (String p : palavras) {
            System.out.printf("  %-10s -> '%c'%n", p, p.charAt(p.length() - 1));
        }
    }
}
