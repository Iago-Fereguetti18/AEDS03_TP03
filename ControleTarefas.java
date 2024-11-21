import java.util.ArrayList;

public class ControleTarefas {
    private ArquivoTarefas arquivoTarefas;
    private ArquivoCategorias arquivoCategorias;
    private ControleIndiceInvertido controleIndiceInvertido;
    private ArquivoRotulos arquivoRotulos;

    public ControleTarefas(ArquivoTarefas arquivoTarefas, ArquivoCategorias arquivoCategorias, ArquivoRotulos arquivoRotulos) throws Exception {
        this.arquivoTarefas = arquivoTarefas;
        this.arquivoCategorias = arquivoCategorias;
        this.arquivoRotulos = arquivoRotulos; // Inicializa corretamente o atributo
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
        // Verifica se há rótulos associados
        ArrayList<Rotulo> rotulosAssociados = arquivoRotulos.buscarRotulosPorTarefa(idTarefa);
        if (!rotulosAssociados.isEmpty()) {
            // Chama o método que gerencia a exclusão completa
            return excluirTarefaComAssociacoes(idTarefa);
        }

        // Exclusão simples se não houver associações
        Tarefa tarefa = arquivoTarefas.read(idTarefa);
        boolean excluido = arquivoTarefas.delete(idTarefa);
        if (excluido) {
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

    // Método para excluir uma tarefa e remover todas as associações com rótulos
    public boolean excluirTarefaComAssociacoes(int idTarefa) throws Exception {
        // Recupera os rótulos associados à tarefa
        ArrayList<Rotulo> rotulosAssociados = arquivoRotulos.buscarRotulosPorTarefa(idTarefa);

        // Remove todas as associações entre a tarefa e os rótulos
        for (Rotulo rotulo : rotulosAssociados) {
            arquivoRotulos.desassociarRotuloDeTarefa(idTarefa, rotulo.getId());
        }

        // Remove a tarefa do índice invertido
        Tarefa tarefa = arquivoTarefas.read(idTarefa);
        controleIndiceInvertido.removerDoIndice(idTarefa, tarefa.getNome());

        // Exclui a tarefa do arquivo
        return arquivoTarefas.delete(idTarefa);
    }

    public ArrayList<Rotulo> listarRotulosPorTarefa(int idTarefa) throws Exception {
        return arquivoRotulos.buscarRotulosPorTarefa(idTarefa);
    }

}
