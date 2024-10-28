# AEDS III - TP02

## Alunos integrantes da equipe

* Diego Moreira Rocha
* Luan Barbosa Rosa Carrieiros
* Iago Fereguetti Ribeiro 

Neste trabalho prático foi:
  - Acrescentado o atributo de idCategoria na entidade tarefa.
  - Criado a entidade categoria.
  - Extendida a classe Arquivo para ter uma classe arquivo de tarefas, no qual foi implementado o relacionamento 1:n usando a arvore B+.
  - Extendida a classe Arquivo para ter uma classe arquivo categoria, usando de um id indireto com nome.

## Classes e Métodos criados

### Classe Arquivo Tarefa

A classe tem como seu objetivo armazenar as tarefas 

* ## Atributos:

  - IndiceCategoriaTarefa: indece para a arvore

* ## Construtores:
   
  - ArquivoTarefas: Construtor que inicializa o arquivo de tarefas e o índice para o
  - Create: Método para criar uma nova tarefa no arquivo e no índice 1:N
    
* ## Métodos:

   - read: Método para ler uma tarefa pelo ID
   - updade: Método para atualizar uma tarefa existente
   - delete: Método para excluir uma tarefa pelo ID e atualizar o índice 1:N
   - buscarPorCategoria: Método para buscar todas as tarefas por categoria
   - ListarTodasTarefas

### Classe Arquivo Categoria

  A classe tem como seu objetivo armazenar as categorias

* ## Atributos:

  - IndiceCategoriaTarefa: indece para a arvore

* ## Construtores:
   
  - ArquivoCategorias: Construtor que inicializa o arquivo de tarefas e o índice para o
  - Create: Método para criar uma nova tarefa no arquivo e no índice 1:N
    
* ## Métodos:

   - read: Método para ler uma tarefa pelo ID
   - updade: Método para atualizar uma tarefa existente
   - delete: Método para excluir uma tarefa pelo ID e atualizar o índice 1:N
   - buscarPorCategoria: Método para buscar todas as tarefas por categoria
   - ListarTodasTarefas

## Experiência

  Inicialmnente tivemos dificuldade para juntar o que tinhamos com os novos 
  
## Perguntas

  - O trabalho possui um índice direto implementado com a tabela hash extensível? Sim
  - A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro? Sim 
  - A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto? Sim
  - A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro? Sim
  - A operação de exclusão marca o registro como excluído e o remove do índice direto? Sim
  - O trabalho está funcionando corretamente? Sim
  - O trabalho está completo? Sim
  - O trabalho é original e não a cópia de um trabalho de outro grupo? Sim
