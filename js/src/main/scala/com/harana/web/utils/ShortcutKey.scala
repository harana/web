package com.harana.web.utils

sealed trait Platform
case object Windows extends Platform
case object Mac extends Platform
case object Linux extends Platform

case class ShortcutKey(title: String, code: String, icon: String, supportedPlatforms: Set[Platform])

object ShortcutKey {
  object Command       extends ShortcutKey("Command", "Command", "", Set(Mac))
  object Ctrl          extends ShortcutKey("Control", "Ctrl", "", Set(Mac, Windows, Linux))
  object Shift         extends ShortcutKey("Shift", "Shift", "", Set(Mac, Windows, Linux))
  object Option        extends ShortcutKey("Shift", "s", "", Set())
  object Win           extends ShortcutKey("Windows", "super", "s", Set(Windows, Linux))
  object AltLeft       extends ShortcutKey("Alt", "s", "", Set(Windows, Linux))
  object AltRight      extends ShortcutKey("Alt (Gr)", "s", "", Set(Windows, Linux))
  object Digit0        extends ShortcutKey("0", "0", "s", Set(Mac, Windows, Linux))
  object Digit1        extends ShortcutKey("1", "1", "s", Set(Mac, Windows, Linux))
  object Digit2        extends ShortcutKey("2", "2", "s", Set(Mac, Windows, Linux))
  object Digit3        extends ShortcutKey("3", "3", "s", Set(Mac, Windows, Linux))
  object Digit4        extends ShortcutKey("4", "4", "s", Set(Mac, Windows, Linux))
  object Digit5        extends ShortcutKey("5", "5", "s", Set(Mac, Windows, Linux))
  object Digit6        extends ShortcutKey("6", "6", "s", Set(Mac, Windows, Linux))
  object Digit7        extends ShortcutKey("7", "7", "s", Set(Mac, Windows, Linux))
  object Digit8        extends ShortcutKey("8", "8", "s", Set(Mac, Windows, Linux))
  object Digit9        extends ShortcutKey("9", "9", "s", Set(Mac, Windows, Linux))
  object Equal         extends ShortcutKey("Equal", "s", "", Set(Mac, Windows, Linux))
  object KeyA          extends ShortcutKey("A", "a", "", Set(Mac, Windows, Linux))
  object KeyB          extends ShortcutKey("B", "b", "", Set(Mac, Windows, Linux))
  object KeyC          extends ShortcutKey("C", "c", "", Set(Mac, Windows, Linux))
  object KeyD          extends ShortcutKey("D", "d", "", Set(Mac, Windows, Linux))
  object KeyE          extends ShortcutKey("E", "e", "", Set(Mac, Windows, Linux))
  object KeyF          extends ShortcutKey("F", "f", "", Set(Mac, Windows, Linux))
  object KeyG          extends ShortcutKey("G", "g", "", Set(Mac, Windows, Linux))
  object KeyH          extends ShortcutKey("H", "h", "", Set(Mac, Windows, Linux))
  object KeyI          extends ShortcutKey("I", "i", "", Set(Mac, Windows, Linux))
  object KeyJ          extends ShortcutKey("J", "j", "", Set(Mac, Windows, Linux))
  object KeyK          extends ShortcutKey("K", "k", "", Set(Mac, Windows, Linux))
  object KeyL          extends ShortcutKey("L", "l", "", Set(Mac, Windows, Linux))
  object KeyM          extends ShortcutKey("M", "m", "", Set(Mac, Windows, Linux))
  object KeyN          extends ShortcutKey("N", "n", "", Set(Mac, Windows, Linux))
  object KeyO          extends ShortcutKey("O", "o", "", Set(Mac, Windows, Linux))
  object KeyP          extends ShortcutKey("P", "p", "", Set(Mac, Windows, Linux))
  object KeyQ          extends ShortcutKey("Q", "q", "s", Set(Mac, Windows, Linux))
  object KeyR          extends ShortcutKey("R", "r", "s", Set(Mac, Windows, Linux))
  object KeyS          extends ShortcutKey("S", "s", "s", Set(Mac, Windows, Linux))
  object KeyT          extends ShortcutKey("T", "t", "s", Set(Mac, Windows, Linux))
  object KeyU          extends ShortcutKey("U", "u", "s", Set(Mac, Windows, Linux))
  object KeyV          extends ShortcutKey("V", "v", "s", Set(Mac, Windows, Linux))
  object KeyW          extends ShortcutKey("W", "w", "s", Set(Mac, Windows, Linux))
  object KeyX          extends ShortcutKey("X", "x", "s", Set(Mac, Windows, Linux))
  object KeyY          extends ShortcutKey("Y", "y", "s", Set(Mac, Windows, Linux))
  object KeyZ          extends ShortcutKey("Z", "z", "s", Set(Mac, Windows, Linux))
  object Minus         extends ShortcutKey("Minus", "s", "", Set(Mac, Windows, Linux))
  object Period        extends ShortcutKey("Period", "period", "", Set(Mac, Windows, Linux))
  object Plus          extends ShortcutKey("Plus", "plus", "", Set(Mac, Windows, Linux))
  object Quote         extends ShortcutKey("Quote", "quote", "", Set(Mac, Windows, Linux))
  object Semicolon     extends ShortcutKey("Semicolon", "semicolon", "", Set(Mac, Windows, Linux))
  object Slash         extends ShortcutKey("Slash", "slash", "", Set(Mac, Windows, Linux))

  object Backquote     extends ShortcutKey("Backquote", "Backquote", "s", Set(Mac, Windows, Linux))
  object Backslash     extends ShortcutKey("Backslash", "Backslash", "s", Set(Mac, Windows, Linux))
  object BracketLeft   extends ShortcutKey("Bracket Left", "BracketLeft", "s", Set(Mac, Windows, Linux))
  object BracketRight  extends ShortcutKey("Bracket Right", "BracketRight", "s", Set(Mac, Windows, Linux))
  object Comma         extends ShortcutKey("Comma", "Comma", "s", Set(Mac, Windows, Linux))
  object Space         extends ShortcutKey("Space", "s", "", Set(Mac, Windows, Linux))
  object Tab           extends ShortcutKey("Tab", "s", "", Set(Mac, Windows, Linux))
  object Backspace     extends ShortcutKey("Backspace", "s", "", Set(Windows, Linux))
  object Return        extends ShortcutKey("Return", "s", "", Set(Mac, Windows, Linux))

  object Delete        extends ShortcutKey("Delete", "s", "", Set(Mac))
  object End           extends ShortcutKey("End", "s", "", Set(Mac, Windows, Linux))
  object Home          extends ShortcutKey("Home", "s", "", Set(Mac, Windows, Linux))
  object Insert        extends ShortcutKey("Insert", "insert", "", Set(Mac, Windows, Linux))
  object PageDown      extends ShortcutKey("Page Down", "s", "", Set(Mac, Windows, Linux))
  object PageUp        extends ShortcutKey("Page Up", "s", "", Set(Mac, Windows, Linux))
  object ArrowLeft     extends ShortcutKey("Arrow Left", "s", "", Set(Mac, Windows, Linux))
  object ArrowRight    extends ShortcutKey("Arrow Right", "s", "", Set(Mac, Windows, Linux))
  object ArrowDown     extends ShortcutKey("Arrow Down", "s", "", Set(Mac, Windows, Linux))
  object ArrowUp       extends ShortcutKey("Arrow Up", "s", "", Set(Mac, Windows, Linux))

  object F1            extends ShortcutKey("F1", "F1", "s", Set(Mac, Windows, Linux))
  object F2            extends ShortcutKey("F2", "F2", "s", Set(Mac, Windows, Linux))
  object F3            extends ShortcutKey("F3", "F3", "s", Set(Mac, Windows, Linux))
  object F4            extends ShortcutKey("F4", "F4", "s", Set(Mac, Windows, Linux))
  object F5            extends ShortcutKey("F5", "F5", "s", Set(Mac, Windows, Linux))
  object F6            extends ShortcutKey("F6", "F6", "s", Set(Mac, Windows, Linux))
  object F7            extends ShortcutKey("F7", "F7", "s", Set(Mac, Windows, Linux))
  object F8            extends ShortcutKey("F8", "F8", "s", Set(Mac, Windows, Linux))
  object F9            extends ShortcutKey("F9", "F9", "s", Set(Mac, Windows, Linux))
  object F10           extends ShortcutKey("F10", "F10", "s", Set(Mac, Windows, Linux))
  object F11           extends ShortcutKey("F11", "F11", "s", Set(Mac, Windows, Linux))
  object F12           extends ShortcutKey("F12", "F12", "s", Set(Mac, Windows, Linux))
  object F13           extends ShortcutKey("F13", "F13", "s", Set(Mac, Windows, Linux))
  object F14           extends ShortcutKey("F14", "F14", "s", Set(Mac, Windows, Linux))
  object F15           extends ShortcutKey("F15", "F15", "s", Set(Mac, Windows, Linux))
  object F16           extends ShortcutKey("F16", "F16", "s", Set(Mac, Windows, Linux))
  object F17           extends ShortcutKey("F17", "F17", "s", Set(Mac, Windows, Linux))
  object F18           extends ShortcutKey("F18", "F18", "s", Set(Mac, Windows, Linux))
  object F19           extends ShortcutKey("F19", "F19", "s", Set(Mac, Windows, Linux))
  object F20           extends ShortcutKey("F20", "F20", "s", Set(Mac, Windows, Linux))
  object F21           extends ShortcutKey("F21", "F21", "s", Set(Mac, Windows, Linux))
  object F22           extends ShortcutKey("F22", "F22", "s", Set(Mac, Windows, Linux))
  object F23           extends ShortcutKey("F23", "F23", "s", Set(Mac, Windows, Linux))
}