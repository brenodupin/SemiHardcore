# **SemiHardcore - v1.1**
A simple plugin that deletes a percentage of items on a Player Death. This percentage changes the more the player survives until it reaches a minimum.  
**The percentage values can be changed on the config.yml file.**
## JAR download
Check [Releases] (TODO)

### Config.yml

The config.yml file will be created after the first time the plugin is loaded.
Defaults values are:  
```yml
start-percentage: 80
end-percentage: 50
decrement-percentage: 0.2
```
The decrement happens when a player survives an entire day (2400 ticks).

## Maven Build ([IntelliJ IDEA](https://www.jetbrains.com/idea/))
1. Download or clone this repo
```shell
git clone https://github.com/brenodupin/semihardcore
```
2. Import folder SemiHardcore as a project.
3. Create dev.properties file inside the project folder and add
```properties
build-output=path/to/your/output/folder
```
4. Run Maven Install
5. JAR output will be on both your folder defined in the `build-output` in the `dev.properties` file and the `target` folder created inside the project folder after the Maven Install.