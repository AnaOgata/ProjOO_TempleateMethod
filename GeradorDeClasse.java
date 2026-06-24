import java.util.Arrays;
import java.util.Comparator;

/**Exercício 16.2**/
public abstract class GeradorDeClasse {

    public final String gerarClasse() {
        StringBuilder sb = new StringBuilder();

        sb.append(gerarCabecalho());
        sb.append(gerarDeclaracaoClasse()).append(" {\n\n");
        sb.append(gerarAtributos());
        sb.append(gerarConstrutores());
        sb.append(gerarMetodos());
        sb.append("}\n");

        return sb.toString();
    }

    protected String gerarCabecalho() {
        return "// Gerado automaticamente pelo GeradorDeClasse\n\n";
    }

    protected abstract String gerarDeclaracaoClasse();
    protected abstract String gerarAtributos();
    protected abstract String gerarConstrutores();
    protected abstract String gerarMetodos();

    protected String indentar(String codigo) {
        return "    " + codigo.replace("\n", "\n    ");
    }
}

class GeradorClasseSimples extends GeradorDeClasse {
    private final String nomeClasse;

    public GeradorClasseSimples(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    @Override
    protected String gerarDeclaracaoClasse() {
        return "public class " + nomeClasse;
    }

    @Override
    protected String gerarAtributos() {
        return indentar("private String nome;\n") +
               indentar("private int id;\n\n");
    }

    @Override
    protected String gerarConstrutores() {
        return indentar("public " + nomeClasse + "(String nome, int id) {\n") +
               indentar("    this.nome = nome;\n") +
               indentar("    this.id = id;\n") +
               indentar("}\n\n");
    }

    @Override
    protected String gerarMetodos() {
        return indentar("public String getNome() { return nome; }\n") +
               indentar("public int getId() { return id; }\n\n") +
               indentar("@Override\n") +
               indentar("public String toString() {\n") +
               indentar("    return \"" + nomeClasse + "[id=\" + id + \", nome=\" + nome + \"]\";\n") +
               indentar("}\n\n");
    }
}

class GeradorClasseFilha extends GeradorDeClasse {
    private final String nomeClasse;
    private final String classePai;

    public GeradorClasseFilha(String nomeClasse, String classePai) {
        this.nomeClasse = nomeClasse;
        this.classePai  = classePai;
    }

    @Override
    protected String gerarDeclaracaoClasse() {
        return "public class " + nomeClasse + " extends " + classePai;
    }

    @Override
    protected String gerarAtributos() {
        return indentar("private String atributoExtra;\n\n");
    }

    @Override
    protected String gerarConstrutores() {
        return indentar("public " + nomeClasse + "(String nome, int id, String extra) {\n") +
               indentar("    super(nome, id);\n") +
               indentar("    this.atributoExtra = extra;\n") +
               indentar("}\n\n");
    }

    @Override
    protected String gerarMetodos() {
        return indentar("public String getAtributoExtra() { return atributoExtra; }\n\n") +
               indentar("@Override\n") +
               indentar("public String toString() {\n") +
               indentar("    return super.toString() + \", extra=\" + atributoExtra;\n") +
               indentar("}\n\n");
    }
}

class TesteGeradorDeClasse {
    public static void main(String[] args) {
        System.out.println("Exercício 16.2 - Template Method para Geração de Classe\n");

        System.out.println("--- Classe simples ---\n");
        GeradorDeClasse g1 = new GeradorClasseSimples("Produto");
        System.out.println(g1.gerarClasse());

        System.out.println("--- Classe filha ---\n");
        GeradorDeClasse g2 = new GeradorClasseFilha("ProdutoEspecial", "Produto");
        System.out.println(g2.gerarClasse());
    }
}
