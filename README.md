Sistema Financeiro

Aplicação web desenvolvida na disciplina de Programação de Aplicações Corporativas. É um sistema de controle financeiro, com cadastro de contas a pagar, fornecedores e planos de contas, e acesso por usuário e perfil.

Tecnologias: Java 21, Maven (empacotamento WAR), Jakarta Servlet 6 e JSP, Hibernate 7 (JPA) e MySQL.

O que o projeto aborda

Arquitetura em camadas (MVC): model (entidades), dao (persistência), service (regras de negócio), controller (Servlets) e webapp (páginas JSP).
Persistência com JPA/Hibernate: entidades mapeadas com anotações, relacionamentos @ManyToOne e @OneToMany, e um GenericDAO<T> com o CRUD reutilizado pelas demais classes DAO.
CRUD completo de Usuários, Fornecedores, Planos de Contas e Contas. Cada conta tem descrição, valor, vencimento, fornecedor e plano de contas.
Autenticação e autorização: login com sessão HTTP e dois perfis. O Supervisor Financeiro gerencia usuários, fornecedores e planos de contas. Os demais usuários logados cuidam das contas.
Criação automática das tabelas pelo Hibernate (hbm2ddl.auto=update).
