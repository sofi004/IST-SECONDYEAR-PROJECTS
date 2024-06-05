#semente
set.seed(1950)

# outros dados
n <- 23
r <- 300
m <- 170
threshold <- 1.5 #o valor T <= threshold

# Função para gerar um valor de T
generate_T <- function(n) {
  Z <- rnorm(n + 1)
  sqrt(n) * Z[1] / sqrt(sum(Z[-1]^2))
}

# Gerar r amostras de m valores de T cada uma
T_samples <- replicate(r, replicate(m, generate_T(n)))

# Calcular a proporção de valores T menores ou iguais a -0.9 para cada amostra
proportions <- apply(T_samples, 2, function(x) mean(x <= threshold))

# Calcular a média das proporções
mean_proportion <- mean(proportions)

# Calcular a probabilidade teórica usando a distribuição t-Student
theoretical_prob <- pt(threshold, df = n)

# Calcular a diferença absoluta e multiplicar por 100
difference <- abs(mean_proportion - theoretical_prob) * 100

# Mostrar o resultado arredondado a 5 casas decimais
cat("Resultado final arredondado a 5 casas decimais:", round(difference, 5), "\n")
