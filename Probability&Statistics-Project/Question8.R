library(pracma)

seed <- 1820
n <- 8
dados <- c(34.0,39.5,33.2,38.1,29.9,37.4,32.1,36.5,31.4,34.1,33.1,31.5,33.9,33.9)
gamma <- 0.95
set.seed(seed)
  
selected <- sample(dados,n,replace=FALSE)

a <- qchisq((1 - gamma) / 2, df = n - 1)
b <- qchisq((1 + gamma) / 2, df = n - 1)

s2 <- var(selected)

ic_1 <- (-(n - 1) * s2 / b) + ((n - 1) * s2 / a)

F <- function(x, df) { pchisq(x, df) }
f <- function(x, df) { dchisq(x, df) }

equacoes <- function(x) {
  c <- x[1]
  d <- x[2]
  eq1 <- F(d, n - 1) - F(c, n - 1) - gamma
  eq2 <- f(d, n + 3) - f(c, n + 3)
  return(c(eq1, eq2))
}

solucao <- fsolve(equacoes, c(a, b))

ic_2 <- -((n - 1) * s2 / solucao$x[2]) + ((n - 1) * s2 / solucao$x[1])

print(ic_1-ic_2)