import aed3.Arquivo;
import aed3.ArvoreBMais;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

public class ArquivoTarefas extends Arquivo<Tarefa> {

    private ArvoreBMais<ParIdId> indiceCategoriaTarefa;

    // Construtor que inicializa o arquivo de tarefas e o índice para o
    // relacionamento 1:N
    public ArquivoTarefas(Constructor<Tarefa> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);
        indiceCategoriaTarefa = new ArvoreBMais<>(ParIdId.class.getConstructor(), 5,
                "dados/arvoreTarefasPorCategoria.db");
    }

    @Override
    public void create(Tarefa tarefa) throws Exception {
        // Verifica duplicidade
        ArrayList<Tarefa> tarefasExistentes = this.list();
        for (Tarefa t : tarefasExistentes) {
            if (t.getNome().equalsIgnoreCase(tarefa.getNome())) {
                throw new Exception("Tarefa com o mesmo nome já existe.");
            }
        }

        // Salva a tarefa no arquivo principal
        super.create(tarefa);

        // Atualiza o índice 1:N
        indiceCategoriaTarefa.create(new ParIdId(tarefa.getIdCategoria(), tarefa.getId()));
    }

    // Método para atualizar uma tarefa existente
    @Override
    public boolean update(Tarefa tarefa) throws Exception {
        Tarefa antigaTarefa = read(tarefa.getId());
        if (antigaTarefa != null) {
            // Atualizar o índice 1:N caso o idCategoria tenha sido modificado
            if (antigaTarefa.getIdCategoria() != tarefa.getIdCategoria()) {
                indiceCategoriaTarefa.delete(new ParIdId(antigaTarefa.getIdCategoria(), antigaTarefa.getId()));
                indiceCategoriaTarefa.create(new ParIdId(tarefa.getIdCategoria(), tarefa.getId()));
            }
            return super.update(tarefa);
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Tarefa tarefa = super.read(id);
        if (tarefa == null) {
            System.out.println("Erro: Registro não encontrado no arquivo para exclusão.");
            return false;
        }

        long posicao = arquivo.getFilePointer() - (1 + 2 + tarefa.toByteArray().length); // Reposiciona para a lápide
        arquivo.seek(posicao);
        arquivo.writeByte('*'); // Marca o registro como excluído

        // Remove do índice direto
        indiceDireto.delete(id);
        System.out.println("Tarefa removida do índice e marcada como excluída.");
        return true;
    }

    public ArrayList<Tarefa> buscarPorCategoria(int idCategoria) throws Exception {
        ArrayList<Tarefa> tarefasAssociadas = new ArrayList<>();
        ArrayList<Tarefa> todasTarefas = list(); // Lista todas as tarefas

        for (Tarefa tarefa : todasTarefas) {
            if (tarefa.getIdCategoria() == idCategoria) { // Verifica se a tarefa pertence à categoria
                tarefasAssociadas.add(tarefa);
            }
        }

        return tarefasAssociadas;
    }

    public ArrayList<Tarefa> listarTodasTarefas() throws Exception {
        return super.list(); // Usa o método `list()` já implementado na superclasse `Arquivo`
    }

}
