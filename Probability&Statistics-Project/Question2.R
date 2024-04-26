# Load necessary package
library(ggplot2)
theme_set(theme_gray())

# Read the CSV file
data <- read.csv("https://web.tecnico.ulisboa.pt/~paulo.soares/pe/projeto/master.csv")

# Selects data referring to the year 1986 and the age group 25-34 years
selected_data <- subset(data, year == 1986 & age == "25-34 years")

# Create graph
ggplot(selected_data, aes(x = sex, y = `suicides.100k.pop`, fill = sex)) +
  geom_boxplot() +
  labs(title = "Comparison of Suicides per 100k Inhabitants by Gender (1986)",
       x = "Gender", y = "Suicides per 100k Inhabitants",
       fill = "Gender")
