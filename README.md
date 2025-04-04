# ItemsPlugin (Also Compatible With 1.25+) 

A lightweight Minecraft plugin that adds special tools and armor with unique abilities.

##Video Showcase
-View At https://anliliwas.com/#/projects

## ✨ Features

### 🔮 Item Features

| Item | Description |
|------|-------------|
| Super Pickaxe | Mine blocks in a 3x3 area for efficient resource gathering |
| Flash Boots | Move at incredible speeds with enhanced jump height |
| Winged Helmet | Experience creative-mode flight while wearing |

### 📝 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/supertool` | Gives you a Super Pickaxe | itemsplugin.supertool |
| `/specialarmor speedboots` | Gives you Flash Boots | itemsplugin.specialarmor |
| `/specialarmor wingedhelmet` | Gives you a Winged Helmet | itemsplugin.specialarmor |

### 🛡️ Permissions

| Permission | Description |
|------------|-------------|
| `itemsplugin.supertool` | Allows use of the Super Pickaxe |
| `itemsplugin.specialarmor` | Allows use of special armor |
| `itemsplugin.keepflight` | Allows admins to retain flight when logging out |

## 📋 Requirements

- Minecraft 1.21.x or later
- Paper/Spigot server

## 🚀 Installation

1. Download the JAR file from the Releases page or build from source
2. Place it in your server's `/plugins` folder
3. Restart your server
4. Enjoy your new special items!

## 🔧 Building from Source

```bash
# Clone the repository
git clone https://github.com/Iwas1512/coolTools-Minecraft-Plugin

# Navigate to the project
cd ItemsPlugin

# Build with Gradle
./gradlew build

# Find the JAR in
build/libs/ItemsPlugin-1.0-SNAPSHOT.jar
```

## 👩‍💻 For Developers

This plugin demonstrates:
- Custom item creation and management
- Event listeners for equipment effects
- Scheduler implementation for recurring tasks
- Permission-based ability control

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Made with ❤️ for the Minecraft community :)
