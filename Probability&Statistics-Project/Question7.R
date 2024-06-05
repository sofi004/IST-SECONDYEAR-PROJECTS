# Carregar o pacote necessário
library(stats4)

# Definir os dados e os parâmetros
x <- c(4.37, 4.3, 5.15, 5.11, 5.15, 4.66, 6.15, 5.72, 5.87, 5.64, 4.05)
a <- 4
n <- length(x)

# Definir a função de log-verossimilhança
log_likelihood <- function(theta) {
  if (theta <= 0) return(Inf)  # para evitar valores não positivos de theta
  logL <- n * log(theta) + n * theta * log(a) - (theta + 1) * sum(log(x))
  return(-logL)  # função negativa para a maximização
}

# Estimar theta usando a função mle
mle_result <- mle(log_likelihood, start = list(theta = 3.6))
theta_hat <- coef(mle_result)
theta_hat

# Calcular o quantil p=0.75 usando a estimativa theta_hat
p <- 0.75
quantile_75_hat <- a * (1 - p)^(-1 / theta_hat)
quantile_75_hat

# Calcular o verdadeiro valor do quantil para theta = 3.6
theta_true <- 3.6
quantile_75_true <- a * (1 - p)^(-1 / theta_true)

# Calcular o desvio absoluto
desvio_absoluto <- abs(quantile_75_hat - quantile_75_true)
round(desvio_absoluto, 4)
