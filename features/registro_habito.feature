# language: pt

Funcionalidade: Registro diário de hábitos
  Para acompanhar minha consistência
  Como um usuário do aplicativo
  Quero registrar meus hábitos concluídos diariamente

Cenário: Registrar um hábito concluído no dia atual
  Dado que o usuário possui hábitos cadastrados
  E hoje é um dia ainda não registrado para o hábito "Beber 2L de água"
  Quando o usuário marca o hábito "Beber 2L de água" como concluído
  Então o sistema deve registrar a conclusão com sucesso
  E exibir a mensagem "Hábito registrado com sucesso"
  E atualizar o calendário de contribuições

Cenário: Tentativa de registrar o mesmo hábito duas vezes no mesmo dia
  Dado que o usuário já registrou hoje o hábito "Beber 2L de água"
  Quando o usuário tenta marcar novamente o hábito "Beber 2L de água"
  Então o sistema deve impedir o registro duplicado
  E exibir a mensagem "Este hábito já foi registrado hoje"

Cenário: Visualizar calendário após registro
  Dado que o usuário registrou o hábito "Beber 2L de água" hoje
  Quando o usuário abre a tela de calendário
  Então o dia atual deve aparecer como "concluído" para esse hábito
