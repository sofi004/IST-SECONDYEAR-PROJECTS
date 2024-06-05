rm(list=ls())

set.seed(1973) #semente
a <- 4 #valor esperado
n <- 40 #dimensão da amostra

# Valor Simulado:

samples <- matrix(rexp(1000 * n, rate = 1/a), nrow = 1000)

Y_simulated <- rowSums(samples)

valor_simulado <- mean(Y_simulated > 126)

# Valor Exato:

valor_exato <- 1 - pgamma(126, shape = n, scale = 4) #escala 1/4

# Diferença absoluta entre os valores, multiplicada por 100 e arredondada a 4 casas decimais:

diferenca <- abs(valor_simulado - valor_exato) * 100
resultado_final <- round(diferenca, 4)

# Resultado:
resultado_final

