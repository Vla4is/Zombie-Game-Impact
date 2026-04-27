# Knight

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## Running The App

You have a few valid ways to start the desktop app:

- Windows PowerShell: `.\gradlew.bat lwjgl3:run`
- Run the `lwjgl3` Gradle task from IntelliJ's Gradle tool window.
- Run the `com.game.knight.lwjgl3.Lwjgl3Launcher` main class directly from IntelliJ.

If you run `Lwjgl3Launcher` directly, keep these settings in mind:

- Use JDK 17 for the run configuration.
- Set the working directory to the project `assets` folder if asset loading or icons fail.
- If your IntelliJ setup behaves differently from Gradle, prefer running `lwjgl3:run`, because that task already configures the desktop launcher correctly.

The desktop launcher class is:

- `lwjgl3/src/main/java/com/game/knight/lwjgl3/Lwjgl3Launcher.java`

The Gradle desktop run setup is defined in:

- `lwjgl3/build.gradle`

## Gameplay

- The game starts on a `PLAY GAME` screen.
- Press `ENTER` or `SPACE` to start.
- Move with `WASD` or the arrow keys.
- Press `SPACE` to attack.
- After death, the game returns to the start screen so you can play again.

To change the player character in code, edit:

- `core/src/main/java/com/game/knight/factory/PlayerCharacterFactory.java`
