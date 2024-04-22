# Load necessary package
library(ggplot2)

# Read the CSV file
data <- read.csv("https://web.tecnico.ulisboa.pt/~paulo.soares/pe/projeto/master.csv")

# Selects data referring to the year 1986 and the age group 25-34 years
selected_data <- subset(data, year == 1986 & age == "25-34 years")

# Create graph
ggplot(selected_data, aes(x = country, y = `suicides.100k.pop`, fill = sex)) +
  geom_boxplot() +
  labs(title = "Comparison of suicides per 100k inhabitants by Gender and Country (1986)",
       x = "Country", y = "Suicides per 100k inhabitants",
       fill = "Gender") +
  theme(axis.text.x = element_text(angle = 90, vjust = 0.5, hjust = 1))