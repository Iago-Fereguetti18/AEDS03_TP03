import java.util.ArrayList;

public class ControleTarefas {
    private ArquivoTarefas arquivoTarefas;
    private ArquivoCategorias arquivoCategorias;
    private ControleIndiceInvertido controleIndiceInvertido;


    public ControleTarefas(ArquivoTarefas arquivoTarefas, ArquivoCategorias arquivoCategorias) throws Exception{
        this.arquivoTarefas = arquivoTarefas;
        this.arquivoCategorias = arquivoCategorias;
        this.controleIndiceInvertido = new ControleIndiceInvertido();
    }

    // Método para adicionar uma nova tarefa
    public boolean adicionarTarefa(Tarefa tarefa) throws Exception {
        arquivoTarefas.create(tarefa);
        controleIndiceInvertido.adicionarAoIndice(tarefa.getId(), tarefa.getNome());
        return true;
    }

    // Método para atualizar uma tarefa existente
    public boolean atualizarTarefa(Tarefa tarefa) throws Exception {
        // Recupera a tarefa antiga para obter sua descrição
        Tarefa tarefaAntiga = arquivoTarefas.read(tarefa.getId());
    
        // Atualiza a tarefa no arquivo
        boolean atualizado = arquivoTarefas.update(tarefa);
        if (atualizado) {
            // Atualiza o índice invertido
            controleIndiceInvertido.atualizarIndice(tarefa.getId(), tarefaAntiga.getNome(), tarefa.getNome());
        }
    
        return atualizado;
    }
    

    // Método para excluir uma tarefa
    public boolean excluirTarefa(int idTarefa) throws Exception {
        // Recupera a tarefa antes de excluí-la
        Tarefa tarefa = arquivoTarefas.read(idTarefa);
    
        // Exclui a tarefa no arquivo
        boolean excluido = arquivoTarefas.delete(idTarefa);
        if (excluido) {
            // Remove do índice invertido
            controleIndiceInvertido.removerDoIndice(idTarefa, tarefa.getNome());
        }
    
        return excluido;
    }
    

    // Método para listar todas as tarefas
    public ArrayList<Tarefa> listarTodasTarefas() throws Exception {
        return arquivoTarefas.listarTodasTarefas();
    }

    // Método para listar todas as categorias (para seleção na visão de tarefas)
    public ArrayList<Categoria> listarTodasCategorias() throws Exception {
        return arquivoCategorias.listarTodasCategorias();
    }
}
