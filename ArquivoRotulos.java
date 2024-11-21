import aed3.Arquivo;
import aed3.ArvoreBMais;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.util.Comparator;

public class ArquivoRotulos extends Arquivo<Rotulo> {

    // Árvores B+ para o relacionamento N:N
    private ArvoreBMais<ParIdId> tarefaParaRotulo;
    private ArvoreBMais<ParIdId> rotuloParaTarefa;

    // Construtor
    public ArquivoRotulos(Constructor<Rotulo> construtor, String nomeArquivo) throws Exception {
        super(construtor, nomeArquivo);

        // Inicializa as Árvores B+
        tarefaParaRotulo = new ArvoreBMais<>(ParIdId.class.getConstructor(), 5, "dados/tarefaParaRotulo.db");
        rotuloParaTarefa = new ArvoreBMais<>(ParIdId.class.getConstructor(), 5, "dados/rotuloParaTarefa.db");
    }

    // Criar um novo rótulo
    @Override
    public void create(Rotulo rotulo) throws Exception {
        super.create(rotulo);
    }

    // Ler um rótulo pelo ID
    @Override
    public Rotulo read(int id) throws Exception {
        return super.read(id);
    }

    // Atualizar um rótulo existente
    @Override
    public boolean update(Rotulo rotulo) throws Exception {
        return super.update(rotulo);
    }

    // Excluir um rótulo pelo ID
    @Override
    public boolean delete(int id) throws Exception {
        // Remover todas as associações na árvore Rotulo → Tarefas
        ArrayList<ParIdId> tarefasAssociadas = rotuloParaTarefa.read(new ParIdId(id, -1));
        for (ParIdId par : tarefasAssociadas) {
            tarefaParaRotulo.delete(new ParIdId(par.getId2(), id));
        }
        rotuloParaTarefa.delete(new ParIdId(id, -1));
        return super.delete(id);
    }

    // Método para listar todos os rótulos usando a árvore B+
    public ArrayList<Rotulo> listarTodos() throws Exception {
        ArrayList<Rotulo> rotulos = new ArrayList<>();

        // Busca todas as entradas na árvore B+
        ArrayList<ParIdId> pares = tarefaParaRotulo.read(null); // Tarefa -> Rótulo

        // Itera pelos pares para recuperar os rótulos correspondentes
        for (ParIdId par : pares) {
            Rotulo rotulo = read(par.getId2()); // Obtém o ID do rótulo associado
            if (rotulo != null) { // Ignora rótulos excluídos
                rotulo.setRotulo(formatarRotulo(rotulo.getRotulo())); // Formata o nome
                rotulos.add(rotulo);
            }
        }

        // Opcional: Ordena os rótulos por nome (não é estritamente necessário, pois a
        // B+ já mantém a ordem)
        rotulos.sort(Comparator.comparing(Rotulo::getRotulo));
        return rotulos;
    }

    // Método auxiliar para formatar o nome com a primeira letra maiúscula e o
    // restante minúsculo
    private String formatarRotulo(String nome) {
        if (nome == null || nome.isEmpty()) {
            return nome;
        }
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    // Associar um rótulo a uma tarefa
    public void associarRotuloATarefa(int idTarefa, int idRotulo) throws Exception {
        tarefaParaRotulo.create(new ParIdId(idTarefa, idRotulo));
        rotuloParaTarefa.create(new ParIdId(idRotulo, idTarefa));
    }

    // Remover associação entre rótulo e tarefa
    public void desassociarRotuloDeTarefa(int idTarefa, int idRotulo) throws Exception {
        tarefaParaRotulo.delete(new ParIdId(idTarefa, idRotulo));
        rotuloParaTarefa.delete(new ParIdId(idRotulo, idTarefa));
    }

    // Buscar tarefas associadas a um rótulo
    public ArrayList<Tarefa> buscarTarefasPorRotulo(int idRotulo, ArquivoTarefas arquivoTarefas) throws Exception {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        ArrayList<ParIdId> pares = rotuloParaTarefa.read(new ParIdId(idRotulo, -1)); // Busca pares Rótulo -> Tarefa

        for (ParIdId par : pares) {
            Tarefa tarefa = arquivoTarefas.read(par.getId2()); // Lê a tarefa associada pelo ID
            if (tarefa != null) {
                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    // Buscar rótulos associados a uma tarefa
    public ArrayList<Rotulo> buscarRotulosPorTarefa(int idTarefa) throws Exception {
        ArrayList<Rotulo> rotulos = new ArrayList<>();
        ArrayList<ParIdId> pares = tarefaParaRotulo.read(new ParIdId(idTarefa, -1)); // Busca pares Tarefa -> Rótulo

        for (ParIdId par : pares) {
            Rotulo rotulo = read(par.getId2()); // Lê o rótulo associado pelo ID
            if (rotulo != null) {
                rotulos.add(rotulo);
            }
        }

        return rotulos;
    }

}
