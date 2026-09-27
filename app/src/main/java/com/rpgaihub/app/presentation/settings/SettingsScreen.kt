package com.rpgaihub.app.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rpgaihub.app.R
import com.rpgaihub.app.domain.model.AppThemeMode
import com.rpgaihub.app.presentation.common.GlowCard
import com.rpgaihub.app.presentation.common.RpgTopAppBar
import com.rpgaihub.app.presentation.common.StatusBadge
import com.rpgaihub.app.presentation.theme.Dimensions

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.testTag("settings_screen"),
        topBar = {
            RpgTopAppBar(
                title = stringResource(R.string.settings_title),
                onBackClick = onNavigateBack
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = Dimensions.maxContentWidth)
                    .padding(horizontal = Dimensions.spacingRegular),
                verticalArrangement = Arrangement.spacedBy(Dimensions.spacingLarge)
            ) {
                item {
                    Spacer(modifier = Modifier.height(Dimensions.spacingSmall))
                    Text(
                        text = stringResource(R.string.settings_section_appearance),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = Dimensions.spacingSmall)
                    )
                }

                item {
                    GlowCard(testTag = "appearance_card") {
                        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)) {
                            ThemeOptionRow(
                                title = stringResource(R.string.settings_theme_system),
                                subtitle = "Follow system-wide theme setting",
                                icon = Icons.Default.SettingsBrightness,
                                selected = uiState.themeMode == AppThemeMode.SYSTEM,
                                testTag = "theme_option_system",
                                onClick = { viewModel.onEvent(SettingsEvent.SetThemeMode(AppThemeMode.SYSTEM)) }
                            )

                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                modifier = Modifier.padding(vertical = Dimensions.spacingSmall)
                            )

                            ThemeOptionRow(
                                title = stringResource(R.string.settings_theme_dark),
                                subtitle = "Obsidian void & arcane neon accents",
                                icon = Icons.Default.DarkMode,
                                selected = uiState.themeMode == AppThemeMode.DARK,
                                testTag = "theme_option_dark",
                                onClick = { viewModel.onEvent(SettingsEvent.SetThemeMode(AppThemeMode.DARK)) }
                            )

                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                modifier = Modifier.padding(vertical = Dimensions.spacingSmall)
                            )

                            ThemeOptionRow(
                                title = stringResource(R.string.settings_theme_light),
                                subtitle = "Clean daylight high-contrast palette",
                                icon = Icons.Default.LightMode,
                                selected = uiState.themeMode == AppThemeMode.LIGHT,
                                testTag = "theme_option_light",
                                onClick = { viewModel.onEvent(SettingsEvent.SetThemeMode(AppThemeMode.LIGHT)) }
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = stringResource(R.string.settings_section_about),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = Dimensions.spacingSmall)
                    )
                }

                item {
                    GlowCard(testTag = "about_card") {
                        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Column {
                                    Text(
                                        text = stringResource(R.string.app_name),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = stringResource(R.string.settings_about_desc),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

                            AboutInfoRow(
                                label = stringResource(R.string.settings_version_label),
                                value = uiState.appVersion
                            )

                            AboutInfoRow(
                                label = stringResource(R.string.settings_build_phase_label),
                                value = uiState.buildPhase
                            )

                            AboutInfoRow(
                                label = stringResource(R.string.settings_architecture_label),
                                value = uiState.architectureSummary
                            )

                            Spacer(modifier = Modifier.height(Dimensions.spacingSmall))

                            StatusBadge(
                                text = "PHASE 1 SPECIFICATION COMPLIANT",
                                accentColor = MaterialTheme.colorScheme.secondary,
                                testTag = "settings_status_badge"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Dimensions.spacingXLarge))
                }
            }
        }
    }
}

@Composable
private fun ThemeOptionRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    selected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag)
            .clickable(onClick = onClick)
            .padding(vertical = Dimensions.spacingSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
private fun AboutInfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
