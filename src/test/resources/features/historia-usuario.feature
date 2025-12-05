# language: pt
Funcionalidade: Busca e Filtro de Animes
  Como um usuário do AniMatch
  Eu quero buscar e filtrar animes por diferentes critérios
  Para encontrar rapidamente os animes que me interessam

  Contexto:
    Dado que existem animes cadastrados no sistema
    E que alguns animes têm gênero "Action"
    E que alguns animes têm classificação "PG-13 - Teens 13 or older"
    E que alguns animes têm status "Currently Airing"
    E que existe um anime com título "One Punch Man"

  # Cenário 1: Busca por palavra-chave
  Cenário: Buscar anime por nome parcial
    Dado que estou na página de busca de animes
    Quando eu digito "One" no campo de busca por nome
    Então o sistema deve retornar animes que contenham "One" no título
    E o anime "One Punch Man" deve estar nos resultados
    E a busca não deve diferenciar maiúsculas e minúsculas

  # Cenário 2: Filtro por gênero
  Cenário: Filtrar animes por gênero específico
    Dado que estou na página de busca de animes
    Quando eu seleciono o gênero "Action" no filtro de gênero
    Então o sistema deve retornar apenas animes do gênero "Action"
    E todos os animes retornados devem ter "Action" em seus gêneros
    E animes de outros gêneros não devem aparecer nos resultados

  # Cenário 3: Filtro por classificação indicativa
  Cenário: Filtrar animes por classificação indicativa
    Dado que estou na página de busca de animes
    Quando eu seleciono a classificação "PG-13 - Teens 13 or older"
    Então o sistema deve retornar apenas animes com essa classificação
    E todos os animes retornados devem ter a classificação "PG-13 - Teens 13 or older"
    E animes com outras classificações não devem aparecer

  # Cenário 4: Filtro por status
  Cenário: Filtrar animes por status de exibição
    Dado que estou na página de busca de animes
    Quando eu seleciono o status "Currently Airing"
    Então o sistema deve retornar apenas animes que estão em exibição
    E todos os animes retornados devem ter status "Currently Airing"
    E animes finalizados ou não lançados não devem aparecer

  # Cenário 5: Combinação de filtros
  Cenário: Buscar anime combinando múltiplos filtros
    Dado que estou na página de busca de animes
    Quando eu seleciono o gênero "Action"
    E seleciono a classificação "PG-13 - Teens 13 or older"
    E seleciono o status "Currently Airing"
    Então o sistema deve retornar apenas animes que atendam a todos os critérios
    E todos os animes retornados devem ser do gênero "Action"
    E todos devem ter classificação "PG-13 - Teens 13 or older"
    E todos devem ter status "Currently Airing"

  # Cenário 6: Busca combinada com filtros
  Cenário: Buscar por nome e aplicar filtros simultaneamente
    Dado que estou na página de busca de animes
    Quando eu digito "One" no campo de busca por nome
    E seleciono o gênero "Action"
    Então o sistema deve retornar animes que contenham "One" no título
    E que sejam do gênero "Action"
    E o anime "One Punch Man" deve aparecer se atender aos critérios

  # Cenário 7: Limpar filtros
  Cenário: Limpar todos os filtros aplicados
    Dado que estou na página de busca de animes
    E que tenho filtros aplicados (gênero "Action" e classificação "PG-13")
    Quando eu clico no botão "Limpar Filtros"
    Então todos os filtros devem ser removidos
    E a lista de animes deve voltar ao estado inicial (sem filtros)
    E todos os animes devem ser exibidos novamente

  # Cenário 8: Nenhum resultado encontrado
  Cenário: Exibir mensagem quando nenhum anime é encontrado
    Dado que estou na página de busca de animes
    Quando eu aplico filtros que não correspondem a nenhum anime
    E não existem animes que atendam aos critérios selecionados
    Então o sistema deve exibir uma mensagem informativa
    E a mensagem deve indicar que nenhum anime foi encontrado
    E deve sugerir ajustar os filtros

  # Cenário 9: Busca case-insensitive
  Cenário: Busca não deve diferenciar maiúsculas e minúsculas
    Dado que estou na página de busca de animes
    Quando eu digito "ONE" no campo de busca
    Então o sistema deve encontrar animes com "One", "ONE" ou "one" no título
    E o anime "One Punch Man" deve aparecer nos resultados

  # Cenário 10: Busca por correspondência parcial
  Cenário: Buscar anime usando parte do nome
    Dado que estou na página de busca de animes
    Quando eu digito "Punch" no campo de busca
    Então o sistema deve retornar animes que contenham "Punch" no título
    E o anime "One Punch Man" deve estar nos resultados

  # Cenário 11: Filtro vazio não deve aplicar restrições
  Cenário: Quando nenhum filtro é selecionado, mostrar todos os animes
    Dado que estou na página de busca de animes
    Quando nenhum filtro está selecionado
    E o campo de busca está vazio
    Então o sistema deve exibir todos os animes disponíveis
    E não deve aplicar nenhuma restrição de busca

  # Cenário 12: Animes inadequados são filtrados automaticamente
  Cenário: Sistema deve ocultar animes com classificação proibida
    Dado que existem animes com classificação "R+ - Mild Nudity" no sistema
    Quando eu busco animes sem filtros
    Então animes com classificações proibidas não devem aparecer nos resultados
    E apenas animes com classificações permitidas devem ser exibidos

