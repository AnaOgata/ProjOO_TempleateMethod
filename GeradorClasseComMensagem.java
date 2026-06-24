import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**Exercício 16.3**/
public class GeradorClasseComMensagem {

    public static String gerarCodigo(String nomeClasse, String mensagem) {
        return "/**\n"
             + " * Classe gerada automaticamente por GeradorClasseComMensagem.\n"
             + " */\n"
             + "public class " + nomeClasse + " {\n\n"
             + "    public static void main(String[] args) {\n"
             + "        System.out.println(\"" + mensagem + "\");\n"
             + "    }\n\n"
             + "}\n";
    }

    public static void gravarArquivo(String nomeClasse, String codigo) throws IOException {
        String nomeArquivo = nomeClasse + ".java";
        try (FileWriter fw = new FileWriter(nomeArquivo)) {
            fw.write(codigo);
        }
        System.out.println("Arquivo gerado: " + nomeArquivo);
    }

    public static boolean nomeValido(String nome) {
        return nome != null
            && !nome.isEmpty()
            && Character.isLetter(nome.charAt(0))
            && nome.matches("[A-Za-z_][A-Za-z0-9_]*");
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exercício 16.3 - Gerador de Classe Java\n");

        String nomeClasse;
        while (true) {
            System.out.print("Digite o nome da classe (ex: OlaUniverso): ");
            nomeClasse = scanner.nextLine().trim();
            if (nomeValido(nomeClasse)) break;
            System.out.println("  Nome inválido. Use apenas letras, dígitos e '_', começando com letra.\n");
        }

        System.out.print("Digite a mensagem a ser impressa: ");
        String mensagem = scanner.nextLine();

        String codigo = gerarCodigo(nomeClasse, mensagem);

        System.out.println("\n--- Código gerado ---");
        System.out.println(codigo);

        gravarArquivo(nomeClasse, codigo);

        System.out.println("\nPara compilar e executar:");
        System.out.println("  javac " + nomeClasse + ".java");
        System.out.println("  java  " + nomeClasse);

        scanner.close();
    }
}
