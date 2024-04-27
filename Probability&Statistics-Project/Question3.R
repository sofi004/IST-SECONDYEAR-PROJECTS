library(ggplot2)
library(readxl)

# Read the data
data <- read_excel("/home/sofia/Downloads/electricity.xlsx", sheet = "electricity_production")

# Select relevant data since the beginning of 2015 for the countries "IEA Total" and "Latvia"
selected_data <- subset(data, YEAR >= 2015 & (COUNTRY == "IEA Total" | COUNTRY == "Latvia" | COUNTRY == "Italy"))

# Calculate the proportion of electricity produced from renewable sources relative to total electricity production for each month and for the countries "IEA Total", "Latvia", and "Italy"
proportions <- numeric(length(unique(selected_data$TIME)))
for (country in c("IEA Total", "Latvia", "Italy")) {
  for (i in 1:length(unique(selected_data$TIME))) {
    renewables_value <- as.numeric(selected_data$VALUE[selected_data$TIME == unique(selected_data$TIME)[i] & selected_data$PRODUCT == "Renewables" & selected_data$COUNTRY == country])
    non_renewables_value <- as.numeric(selected_data$VALUE[selected_data$TIME == unique(selected_data$TIME)[i] & selected_data$PRODUCT == "Non-renewables" & selected_data$COUNTRY == country])
    proportions[i] <- (renewables_value / (renewables_value + non_renewables_value)) * 100
  }
  
  # Create data frame with the results
  df_proportions <- data.frame(TIME = unique(selected_data$TIME), Renewable_Proportions = proportions, COUNTRY = country)
  
  # Bind data frames
  if (country == "IEA Total") {
    df_merge_proportions <- df_proportions
  } else {
    df_merge_proportions <- rbind(df_merge_proportions, df_proportions)
  }
}

# Reorder the levels of the variable TIME according to the desired order
df_merge_proportions$TIME <- factor(df_merge_proportions$TIME, levels = unique(selected_data$TIME))

# Plot the scatter plot with distinct colors and legend
ggplot(df_merge_proportions, aes(x = TIME, y = Renewable_Proportions, color = COUNTRY)) +
  geom_point() +
  labs(title = "Monthly evolution of the proportion of renewable energy", x = "Time", y = "Proportion of Renewable Energy (%)", color = "Country") +
  scale_y_continuous(limits = c(0, 100)) +
  scale_color_manual(values = c("IEA Total" = "blue", "Latvia" = "red", "Italy" = "green")) +
  guides(color = guide_legend(title = "Country")) +
  theme(axis.text.x = element_text(angle = 90, vjust = 0.5, hjust=1, size = 5)) +
  theme_minimal()
