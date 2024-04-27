library(ggplot2)
library(readxl)
theme_set(theme_gray())

data <- read_excel("/home/sofia/Downloads/electricity.xlsx", sheet = "electricity_production")

# Selecionar os dados relevantes desde o início de 2015 para o país IEA Total
selected_data <- subset(data, YEAR >= 2015 & COUNTRY == "IEA Total")

# Calcular a proporção de energia elétrica produzida a partir de fontes renováveis em relação à produção total de eletricidade para cada mês
proporciones <- numeric(length(unique(selected_data$TIME)))
for (i in 1:length(unique(selected_data$TIME))) {
  num_renewables <- nrow(selected_data[selected_data$TIME == unique(selected_data$TIME)[i] & selected_data$PRODUCT == "Renewables", ])
  num_non_renewables <- nrow(selected_data[selected_data$TIME == unique(selected_data$TIME)[i], ])
  proporciones[i] <- (num_renewables / num_non_renewables) * 100
}

# Criar um dataframe com os resultados
df_proporciones <- data.frame(TIME = unique(selected_data$TIME), Proporcao_Renovaveis = proporciones)

# Plotar o gráfico de pontos
ggplot(df_proporciones, aes(x = TIME, y = Proporcao_Renovaveis)) +
  geom_point() +
  labs(x = "TIME", y = "Proporção de Renováveis (%)")
  