import java.util.ArrayList;
import java.util.Scanner;

public class VisaoCategorias {
    private ControleCategorias controleCategorias;
    @SuppressWarnings("unused")
    private ControleTarefas controleTarefas;
    private Scanner scanner;

    // Construtor que recebe ControleCategorias e ControleTarefas
    public VisaoCategorias(ControleCategorias controleCategorias, ControleTarefas controleTarefas) {
        this.controleCategorias = controleCategorias;
        this.controleTarefas = controleTarefas;
        this.scanner = new Scanner(System.in);
    }

    public void menu() throws Exception {
        int opcao;
        do {
            System.out.println("\n\tPUCBOOK 1.0");
            System.out.println("--------------------");
            System.out.println("> Início > Categorias\n");
            System.out.println("\t1) Incluir");
            System.out.println("\t2) Excluir");
            System.out.println("\t3) Listar");
            System.out.println("\t4) Gerar Relatório de Tarefas por Categoria");
            System.out.println("\t0) Retornar ao menu anterior");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarCategoria();
                    break;
                case 2:
                    excluirCategoria();
                    break;
                case 3:
                    listarCategorias();
                    break;
                case 4:
                    gerarRelatorioTarefasPorCategoria();
                    break;
                case 0:
                    System.out.println("Saindo do menu de categorias...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private void adicionarCategoria() throws Exception {
        System.out.print("Nome da nova categoria: ");
        String nome = scanner.nextLine();
        Categoria categoria = new Categoria(-1, nome); // ID será gerado automaticamente no `create`
        if (controleCategorias.adicionarCategoria(categoria)) {
            System.out.println("Categoria adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar categoria.");
        }
    }

    private void excluirCategoria() throws Exception {
        ArrayList<Categoria> categorias = controleCategorias.listarTodasCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada para excluir.");
            return;
        }

        // Exibe as categorias com numeração sequencial
        System.out.println("Escolha a categoria a ser excluída:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("\t("+(i + 1) + ") " + categorias.get(i).getNome());
        }

        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        // Valida a escolha do usuário
        if (escolha < 1 || escolha > categorias.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        // Mapeia a escolha para o ID real da categoria
        int idCategoria = categorias.get(escolha - 1).getId();
        if (controleCategorias.excluirCategoria(idCategoria)) {
            System.out.println("Categoria excluída com sucesso!");
        } else {
            System.out.println("Não foi possível excluir a categoria.");
        }
    }

    private void listarCategorias() throws Exception {
        System.out.println("Lista de Categorias:\n");
        for (Categoria categoria : controleCategorias.listarTodasCategorias()) {
            System.out.println("ID: [" + categoria.getId() + "] - NOME: " + categoria.getNome());
        }
    }

    private void gerarRelatorioTarefasPorCategoria() throws Exception {
        controleCategorias.gerarRelatorioTarefasPorCategoria();
    }
}
