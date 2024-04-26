# Load necessary package
library(ggplot2)

# Read the CSV file
data <- read.csv("https://web.tecnico.ulisboa.pt/~paulo.soares/pe/projeto/Paises_PIB_ICH.csv")

# Select countries from Europe and Americas continents
selected_data <- subset(data, Continent %in% c("Europe", "Americas"))

# Create graph
ggplot(selected_data, 
    aes(x = log(GDP), y = HCI, label = Country, color = Continent)) +
  geom_point() +
  geom_text(data = subset(selected_data, Country %in% 
    c("Lithuania", "Iceland", "United States", "Saint Lucia")),
    aes(label = Country), size = 3, nudge_y = -0.007) +
  scale_color_manual(values = c("Europe" = "blue", "Americas" = "red")) +
  labs(title = "Human Capital Index as a function of GDP per capita",
    x = "GDP per capita",
    y = "Human Capital Index") +
  theme_minimal()
  theme(plot.title = element_text(hjust = 0.5))
