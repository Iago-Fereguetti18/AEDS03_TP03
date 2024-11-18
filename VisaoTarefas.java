import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class VisaoTarefas {
    private ControleTarefas controleTarefas;
    private ControleCategorias controleCategorias;
    private Scanner scanner;

    public VisaoTarefas(ControleTarefas controleTarefas, ControleCategorias controleCategorias) {
        this.controleTarefas = controleTarefas;
        this.controleCategorias = controleCategorias;
        this.scanner = new Scanner(System.in);
    }

    public void menu() throws Exception {
        int opcao;
        do {
            System.out.println("\n\tPUCBOOK 1.0");
            System.out.println("-------------------");
            System.out.println("> Início > Tarefas\n");
            System.out.println("\t1) Incluir");
            System.out.println("\t2) Excluir");
            System.out.println("\t3) Listar");
            System.out.println("\t4) Atualizar Tarefa");
            System.out.println("\t0) Retornar ao menu anterior");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    excluirTarefa();
                    break;
                case 3:
                    listarTarefas();
                    break;
                case 4:
                    atualizarTarefa();
                    break;
                case 0:
                    System.out.println("Saindo do menu de tarefas...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private void atualizarTarefa() throws Exception {
        // Listar as tarefas disponíveis
        ArrayList<Tarefa> tarefas = controleTarefas.listarTodasTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para atualizar.");
            return;
        }

        // Exibir as tarefas para o usuário escolher
        System.out.println("Escolha a tarefa que deseja atualizar:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println("\t(" + (i + 1) + ") " + tarefas.get(i).getNome());
        }

        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (escolha < 1 || escolha > tarefas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Tarefa tarefaSelecionada = tarefas.get(escolha - 1);

        // Solicitar novos dados para a tarefa
        System.out.println("Atualizando a tarefa: " + tarefaSelecionada.getNome());

        System.out.print("Novo nome (ou Enter para manter o mesmo): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) {
            tarefaSelecionada.setNome(novoNome);
        }

        System.out.print("Nova prioridade (0 - Baixa, 1 - Média, 2 - Alta, ou -1 para manter): ");
        byte novaPrioridade = scanner.nextByte();
        scanner.nextLine(); // Limpar o buffer
        if (novaPrioridade >= 0 && novaPrioridade <= 2) {
            tarefaSelecionada.setPrioridade(novaPrioridade);
        }

        // Atualiza a tarefa no controle
        if (controleTarefas.atualizarTarefa(tarefaSelecionada)) {
            System.out.println("Tarefa atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar a tarefa.");
        }
    }

    private void adicionarTarefa() throws Exception {
        System.out.print("Nome da Tarefa: ");
        String nome = scanner.nextLine();

        // Listar categorias para seleção
        System.out.println("Escolha uma categoria:");
        ArrayList<Categoria> categorias = controleCategorias.listarTodasCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println("\t(" + (i + 1) + ") " + categorias.get(i).getNome());
        }

        System.out.print("Opção: ");
        int escolhaCategoria;
        try {
            escolhaCategoria = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            // Verificar se a escolha está dentro do limite
            if (escolhaCategoria < 1 || escolhaCategoria > categorias.size()) {
                throw new IndexOutOfBoundsException("Opção fora do limite.");
            }
            // Mapeia a escolha do usuário para o ID real da categoria
            int idCategoria = categorias.get(escolhaCategoria - 1).getId();

            // Solicitar outros atributos
            System.out.print("Data de criação (formato: YYYY-MM-DD): ");
            LocalDate dataCriacao = LocalDate.parse(scanner.nextLine());

            System.out.print("Data de conclusão (formato: YYYY-MM-DD): ");
            LocalDate dataConclusao = LocalDate.parse(scanner.nextLine());

            System.out.print("Status (0 - Pendente, 1 - Em Progresso, 2 - Concluída): ");
            byte status = scanner.nextByte();

            System.out.print("Prioridade (0 - Baixa, 1 - Média, 2 - Alta): ");
            byte prioridade = scanner.nextByte();
            scanner.nextLine(); // Limpar o buffer

            // Cria a nova tarefa com todos os atributos
            Tarefa tarefa = new Tarefa(-1, nome, dataCriacao, dataConclusao, status, prioridade, idCategoria);
            if (controleTarefas.adicionarTarefa(tarefa)) {
                System.out.println("Tarefa adicionada com sucesso!");
            } else {
                System.out.println("Erro ao adicionar tarefa.");
            }

        } catch (Exception e) {
            System.out.println("Opção inválida.");
            return;
        }

    }

    private void excluirTarefa() throws Exception {
        ArrayList<Tarefa> tarefas = controleTarefas.listarTodasTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para excluir.");
            return;
        }

        System.out.println("Escolha a tarefa a ser excluída:");
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ") " + tarefas.get(i).getNome());
        }

        System.out.print("Opção: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (escolha < 1 || escolha > tarefas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        int idTarefa = tarefas.get(escolha - 1).getId();
        if (controleTarefas.excluirTarefa(idTarefa)) {
            System.out.println("Tarefa excluída com sucesso!");
        } else {
            System.out.println("Erro ao excluir tarefa.");
        }
    }

    private void listarTarefas() throws Exception {
        ArrayList<Tarefa> tarefas = controleTarefas.listarTodasTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("Lista de Tarefas:");
            for (Tarefa tarefa : tarefas) {
                System.out.println("ID: " + tarefa.getId() + " - Nome: " + tarefa.getNome());
            }
        }
    }
}
