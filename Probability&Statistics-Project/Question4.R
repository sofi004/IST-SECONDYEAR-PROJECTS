set.seed(2336)

# Número de simulações
n_sim <- 650

# Número de circuitos no sistema
n_circ <- 7

# Probabilidades para cada sinal
probs <- c(1:9)/45

# Inicializar contadores
aviso_sonoro <- 0
sistema_nao_desligado <- 0

for (i in 1:n_sim) {
  # Simular a emissão de sinais para cada circuito
  sinais <- sample(1:9, n_circ, replace = TRUE, prob = probs)
  
  # Verificar se o sistema emite um aviso sonoro (sinal 2 presente) e não é desligado (sinal 1 ausente)
  if (2 %in% sinais && !(1 %in% sinais)) {
    aviso_sonoro <- aviso_sonoro + 1
  }
  
  # Verificar se o sistema não é desligado (sinal 1 ausente)
  if (!(1 %in% sinais)) {
    sistema_nao_desligado <- sistema_nao_desligado + 1
  }
}

# Calcular a proporção de vezes em que é produzido um aviso sonoro num sistema que não é desligado
prop_aviso <- aviso_sonoro / sistema_nao_desligado

# Arredondar a proporção para 2 casas decimais
prop_aviso <- round(prop_aviso, 2)

prop_aviso
