library(ggplot2)
library(readxl)

data <- read_excel("/home/sofia/Downloads/electricity.xlsx", sheet = "electricity_production")

# Selecionar os dados relevantes desde o início de 2015 para o país IEA Total
selected_data <- subset(data, YEAR >= 2015 & COUNTRY == "IEA Total")

# Calcular a proporção de energia elétrica produzida a partir de fontes renováveis em relação à produção total de eletricidade para cada mês
proporciones <- numeric(length(unique(selected_data$TIME)))
for (i in 1:length(unique(selected_data$TIME))) {
  renewables_value <- sum(as.numeric(selected_data$VALUE[selected_data$TIME == unique(selected_data$TIME)[i] & selected_data$PRODUCT == "Renewables"]))
  total_value <- sum(as.numeric(selected_data$VALUE[selected_data$TIME == unique(selected_data$TIME)[i]]))
  proporciones[i] <- (renewables_value / total_value) * 100
}

# Criar um dataframe com os resultados
df_proporciones <- data.frame(TIME = unique(selected_data$TIME), Proporcao_Renovaveis = proporciones)

# Reordenar os níveis da variável TIME de acordo com a ordem desejada
df_proporciones$TIME <- factor(df_proporciones$TIME, levels = unique(selected_data$TIME))

# Plotar o gráfico de pontos com escala de eixo y definida
ggplot(df_proporciones, aes(x = TIME, y = Proporcao_Renovaveis)) +
  geom_point() +
  labs(x = "TIME", y = "Proporção de Renováveis (%)") +
  scale_y_continuous(limits = c(0, 100)) +
  theme(axis.text.x = element_text(angle = 90, vjust = 0.5, hjust=1, size = 5))
