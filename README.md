# AEDS III - TP01

## Alunos integrantes da equipe

* Diego Moreira Rocha
* Luan Barbosa Rosa Carrieiros
* Iago Fereguetti Ribeiro 

Neste trabalho prático, foi adicionada uma nova classe 'Tarefa' para as funções do CRUD, que são gerenciadas por meio de um Hash com índice direto.

## Classes e Métodos criados

### Classe Tarefa

A classe Tarefa representa uma tarefa com atributos como id, nome, data de criação, data de conclusão, status e prioridade. A classe implementa a 
interface Registro, permitindo a manipulação de dados em formato binário.

* ## Atributos:

  - id: Identificador único da tarefa.
  - nome: Nome da tarefa.
  - dataCriacao: Data de criação da tarefa.
  - dataConclusao: Data de conclusão da tarefa.
  - status: Status da tarefa (0 - Pendente, 1 - Em Progresso, 2 - Concluída).
  - prioridade: Prioridade da tarefa (0 - Baixa, 1 - Média, 2 - Alta).

* ## Construtores:
   
  - Tarefa(): Construtor padrão que inicializa a tarefa com valores padrão.
  - Tarefa(String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade):
  - Construtor que inicializa a tarefa com os valores fornecidos, exceto o id.
  - Tarefa(int id, String nome, LocalDate dataCriacao, LocalDate dataConclusao, byte status, byte prioridade):
  - Construtor que inicializa a tarefa com todos os valores fornecidos.
    
* ## Métodos:

   - setId(int id): Define o id da tarefa.
   - getId(): Retorna o id da tarefa.
   - setNome(String nome): Define o nome da tarefa.
   - getNome(): Retorna o nome da tarefa.
   - setDataCriacao(LocalDate dataCriacao): Define a data de criação da tarefa.
   - getDataCriacao(): Retorna a data de criação da tarefa.
   - setDataConclusao(LocalDate dataConclusao): Define a data de conclusão da tarefa.
   - getDataConclusao(): Retorna a data de conclusão da tarefa.
   - setStatus(byte status): Define o status da tarefa.
   - getStatus(): Retorna o status da tarefa.
   - setPrioridade(byte prioridade): Define a prioridade da tarefa.
   - getPrioridade(): Retorna a prioridade da tarefa.
   - toByteArray(): Converte a tarefa para um array de bytes.
   - fromByteArray(byte[] b): Converte um array de bytes para uma tarefa.
   - toString(): Retorna uma representação em string da tarefa.
   - compareTo(Object p): Compara a tarefa com outra tarefa com base no id. 
  

## Experiência

  Achávamos que a lápide estava com algum bug, porque, após deletar, continuava aparecendo, mas era o processo de reorganização que estava causando isso.
  
## Perguntas

  - O trabalho possui um índice direto implementado com a tabela hash extensível? Sim
  - A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro? Sim 
  - A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto? Sim
  - A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro? Sim
  - A operação de exclusão marca o registro como excluído e o remove do índice direto? Sim
  - O trabalho está funcionando corretamente? Sim
  - O trabalho está completo? Sim
  - O trabalho é original e não a cópia de um trabalho de outro grupo? Sim
