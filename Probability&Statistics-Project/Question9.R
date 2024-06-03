set.seed(4588)

# Parâmetros
lambda0 <- 2.40
lambda1 <- 2.65
n <- 100
k <- 2.623
m <- 5000

# Vetores para armazenar os resultados
erro_tipo_I <- numeric(m)
erro_tipo_II <- numeric(m)

# Simulação
for (i in 1:m) {
  # Amostras sob H0 e H1
  amostra_H0 <- rpois(n, lambda0)
  amostra_H1 <- rpois(n, lambda1)
  
  # Calcular médias
  media_H0 <- mean(amostra_H0)
  media_H1 <- mean(amostra_H1)
  
  # Testes
  erro_tipo_I[i] <- media_H0 > k
  erro_tipo_II[i] <- media_H1 <= k
}

# Frequências relativas dos erros
freq_erro_tipo_I <- mean(erro_tipo_I)
freq_erro_tipo_II <- mean(erro_tipo_II)

# Quociente entre a probabilidade de erro de 2ª espécie e a probabilidade de erro de 1ª espécie
quociente <- freq_erro_tipo_II / freq_erro_tipo_I

# Exibir resultados
cat("Frequência relativa de erros do tipo I:", freq_erro_tipo_I, "\n")
cat("Frequência relativa de erros do tipo II:", freq_erro_tipo_II, "\n")
cat("Quociente entre a probabilidade de erro de 2ª espécie e a probabilidade de erro de 1ª espécie:", quociente, "\n")
