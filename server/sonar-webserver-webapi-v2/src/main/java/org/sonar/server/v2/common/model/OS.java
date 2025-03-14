/*
 * SonarQube
 * Copyright (C) 2009-2025 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.v2.common.model;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;
import static java.util.Locale.ENGLISH;

public enum OS {
  WINDOWS("win", "windows", "win32"),
  LINUX("linux"),
  MACOS("mac", "macos", "darwin"),
  ALPINE("alpine");

  private final List<String> aliases;

  OS(String... aliases) {
    this.aliases = Arrays.stream(aliases).toList();
  }

  public static OS from(String alias) {
    return Arrays.stream(values())
      .filter(os -> os.aliases.contains(alias.toLowerCase(ENGLISH)))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported OS: '%s'. Supported values are '%s'", alias, join(", ", supportedValues()))));
  }

  public static List<String> supportedValues() {
    return Arrays.stream(values())
      .flatMap(os -> os.aliases.stream())
      .toList();
  }
}
