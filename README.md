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

   - read: Método para ler uma categoria pelo ID
   - updade: Método para atualizar uma categoria existente
   - delete: Método para excluir uma categoria pelo ID e atualizar o índice 1:N
   - buscarPorNome: Método para buscar uma categoria pelo nome
   - ListarTodasCategorias

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

  - O CRUD (com índice direto) de categorias foi implementado? Sim
  - Há um índice indireto de nomes para as categorias? Sim
  - O atributo de ID de categoria, como chave estrangeira, foi criado na classe Tarefa? Sim
  - Há uma árvore B+ que registre o relacionamento 1:N entre tarefas e categorias? Sim
  - É possível listar as tarefas de uma categoria? Sim
  - A remoção de categorias checa se há alguma tarefa vinculada a ela? Sim
  - A inclusão da categoria em uma tarefa se limita às categorias existentes? Sim
  - O trabalho está funcionando corretamente? Sim
  - O trabalho está completo? Sim
  - O trabalho é original e não a cópia de um trabalho de outro grupo? Sim
