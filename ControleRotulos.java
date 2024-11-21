import java.util.ArrayList;

public class ControleRotulos {
    private ArquivoRotulos arquivoRotulos;
    private ArquivoTarefas arquivoTarefas;

    // Construtor
    public ControleRotulos(ArquivoRotulos arquivoRotulos, ArquivoTarefas arquivoTarefas) {
        this.arquivoRotulos = arquivoRotulos;
        this.arquivoTarefas = arquivoTarefas;
    }

    // Criar um novo rótulo, verificando se já existe
    public boolean criarRotulo(Rotulo rotulo) throws Exception {
        // Verificar se um rótulo com o mesmo nome já existe
        ArrayList<Rotulo> rotulosExistentes = arquivoRotulos.listarTodos();
        for (Rotulo r : rotulosExistentes) {
            if (r.getRotulo().equalsIgnoreCase(rotulo.getRotulo())) {
                System.out.println("Rótulo já existe: " + r.getRotulo());
                return false; // Não cria o rótulo duplicado
            }
        }

        // Cria o rótulo caso não exista
        arquivoRotulos.create(rotulo);
        return true;
    }

    // Atualizar um rótulo existente
    public boolean atualizarRotulo(Rotulo rotulo) throws Exception {
        return arquivoRotulos.update(rotulo);
    }

    // Excluir um rótulo apenas se não houver tarefas associadas
    public boolean excluirRotulo(int idRotulo) throws Exception {
        // Verifica se há tarefas associadas
        ArrayList<Tarefa> tarefasAssociadas = arquivoRotulos.buscarTarefasPorRotulo(idRotulo, arquivoTarefas);

        if (!tarefasAssociadas.isEmpty()) {
            System.out.println("Rótulo está associado a tarefas e não pode ser excluído diretamente.");
            System.out.println("Tarefas associadas: " + tarefasAssociadas.size());
            return false;
        }

        // Remove o rótulo caso não esteja associado a nenhuma tarefa
        return arquivoRotulos.delete(idRotulo);
    }

    // Listar todos os rótulos
    public ArrayList<Rotulo> listarTodosRotulos() throws Exception {
        return arquivoRotulos.listarTodos();
    }

    // Associar um rótulo a uma tarefa
    public void associarRotuloATarefa(int idTarefa, int idRotulo) throws Exception {
        arquivoRotulos.associarRotuloATarefa(idTarefa, idRotulo);
    }

    // Remover associação entre rótulo e tarefa
    public void desassociarRotuloDeTarefa(int idTarefa, int idRotulo) throws Exception {
        arquivoRotulos.desassociarRotuloDeTarefa(idTarefa, idRotulo);
    }

    // Listar tarefas associadas a um rótulo
    public ArrayList<Tarefa> listarTarefasPorRotulo(int idRotulo) throws Exception {
        return arquivoRotulos.buscarTarefasPorRotulo(idRotulo, arquivoTarefas);
    }

    // Listar rótulos associados a uma tarefa
    public ArrayList<Rotulo> listarRotulosPorTarefa(int idTarefa) throws Exception {
        return arquivoRotulos.buscarRotulosPorTarefa(idTarefa);
    }
}
