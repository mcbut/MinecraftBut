# MinecraftBut
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=VWG7DUFDRYXDA)  
A Minecraft Plugin created for YouTubers such as BajanCandian, Skeppy, BadBoyHalo, A6D
The YouTubers spawn in a small world with the option to apply multiple different senerarios

## Join the Discord server for support
[Join the Discord](https://discord.gg/fx9Kg5N) (Note: You must be 13 years of age or older to use Discord)

## Reddit
[/r/MinecratBut](https://www.reddit.com/r/MinecratBut) (yes we know it's misspelt)

## Don't wanna set up the plugin yourself?
Join my server `play.minecraftbut.net` where you can play arcade games with friends and play MinecraftBut, as well as regularly hosted events.

## Download
[Download the latest version of the plugin](https://github.com/mcbut/MinecraftBut/releases/latest)

It'll also be nice if you starred the project <3 (It pretty much means 'like' & it's free)

## Versions
Mainly we support [1.8.x](https://getbukkit.org/get/WAH0jXqYA2s3Gzzg3toWHG1R3lHNCNZY) & 1.14.x servers. Other versions are untested and support will generally not be provided when using other versions. I recommend TacoSpigot or PaperSpigot, but as a bare minimum I recommend you use Spigot over Bukkit or CraftBukkit.

## Support <3
We use yourkit for performance tuning of this project

YourKit supports open source projects with innovative and intelligent tools 
for monitoring and profiling Java and .NET applications.
YourKit is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler), 
[YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/), [YourKit YouMonitor](https://www.yourkit.com/youmonitor)

Personally I would strongly recommend them if you're a developer :D

[![yourkit logo](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com)

## Videos
* [Skeppy's video: Lava is rising - 558,119 views](https://www.youtube.com/watch?v=0dvbqzXiA_o)    
* [Skeppy's video: Water is rising - 1,113,071 views](https://www.youtube.com/watch?v=Kcul2iXfPDY)   
* [Wilbur Soot's video: Minecraft, But I Leave a Trail of Bedrock - 972,845 views](https://www.youtube.com/watch?v=Kcul2iXfPDY)  
* [Skeppy's video: TNT Rain](https://www.youtube.com/watch?v=kkU8-3teKS0)  
* [Skeepy's video: 1 Hit](https://www.youtube.com/watch?v=bpvQGu1cwlQ)  
* [Skeppy's video: Anvil rain](https://www.youtube.com/watch?v=IONiD9ZnqEg)  
* [Skeppy's video: the world decays](https://www.youtube.com/watch?v=I9PjCahtpzs)
* [Skeppy's video: Minecraft, But Sunlight Kills You](https://www.youtube.com/watch?v=QW1dvp4xSE8)
##### *(Want to be added to this list? Contact Heath or CyberFlame on Discord)*

## Senerario (Yes, I know it's spelt wrong haha)
* Lava is Rising
* Water is Rising
* Raining TNT
* Raining Blocks
* No Jumping
* Dragon spawns
* One heart - you only have 1 heart
* Bedrock Trail - You leave a bedrock trail behind you
* Gem Eater (Ore Eater) - You can only eat ores to live
* TNT Spawn on Self - Tnt will spawn on you every so often
* Anvil Rain - Anvil falls from the sky
* Death Out - Set into spectator when you die (or kick when you die)
* World Decay - Every  block the sun touches slowly breaks down
* Linked Inventories - everyone shares inventory (1.8 only)
* Linked Health - everyone shares health
* Linked Deaths - One player dies, everyone dies
* FasterAndFaster - get faster every minute
* Maducer World - world truns to bedrock
* Mobs Multiple - Every time you hit a mob, it multiples 
* Shrinking Inventory - you get less and less inventory space as time goes on
* Block Potion - every block gives a potion when broken

Command to view all credits and their YouTube channels:
```
/mb credit
```


## How to install
0. Use Spigot on version [1.8.x](https://getbukkit.org/get/WAH0jXqYA2s3Gzzg3toWHG1R3lHNCNZY) or 1.14 (these are the only officially supported ones)
0. You can buy a Minecraft server from [here](https://mcprohosting.com/order?aff=81875) unless you already have a host or you can self-host it.
0. Configure the server how you want it in server.properties
0. Then just drop the "MinecraftBut.jar" into the "/plugins" folder of the server
0. Start the server and you're done
**Sidenote**: You could probably get away with using PaperSpigot or TacoSpigot, as they're variants of Spigot. You might even be able to use Bukkit and CraftBukkit, however, only Bukkit inherited "jars" are supported, so that means no Sponge etc. but modified spigot jars should be fine.

## Permissions

```
minecraftbut.command.minecraftbut
```
We only have one permission node for the root command

## Commands
```
/MinecraftBut
/mb
```
Opens the GUI to enable and disable Senerarios

```
/MinecraftBut help
/mb help
```
Help command

```
/MinecraftBut Senerario <senerarioId> help
/mb s <senerarioId> help
```
Learn ways that you can configure a Senerario

```
/MinecraftBut Senerario <senerarioId> <Args>
/mb s <senerarioId> <Args>
```
Configure a Senerario

Note: senerarioId can be found in the lore of the item in the menu

```
/MinecraftBut changeworld <WorldName>
/mb cw <Name>
```
Change the world that the plugin is running on, use case is just for Multiverse support

Note: I will not support the plugin running on multiple worlds at once, as it's out of the scope of common need of the YouTubers, however I think it works in the Nether and The End.

## Features
[Feature Requests](https://feathub.com/HeathLoganCampbell/MinecraftBut)
Or request a feature on the Discord server

## Developers
### How To set up the work environment
https://youtu.be/l7zyhCOyD4A

### Tools used
* [Maven](https://maven.apache.org/) this should already be install on most common IDEs 
* [BuildTools](https://www.spigotmc.org/wiki/buildtools/#1-8-8)
* [Lombok](https://projectlombok.org/download)

### Download code
```
git clone git@github.com:HeathLoganCampbell/MinecraftBut.git
```
A folder called  "MinecraftBut" is now created, so from now on you can cd to this folder and type
``git pull`` to download changes made

### Add a Senerario
Simply extend Senerario, like so:
```java
public class DummyExample extends Senerario {
 //This allows players to edit the field through the command 
 //'/mb s DummyExample someNumberToEdit 1' which sets the value to 1
 @Optional
 private int someNumberToEdit = 5;

 public DummyExample(ButWorld butWorld) {
  super("Dummy Name", butWorld, XMaterial.ANVIL.parseMaterial(), new String[] {
   "This is a description of the senerario"
  });
 }

 //called when Senerario is enabled
 @Override
 public void onStart() {
  //If you have a repeating task, I guess using repeat(runnable, ticks)
  //which auto cancels on disabl
  this.repeat(() -> {}, 20l * 1);
 }

 //called when Senerario is disalbed
 @Override
 public void onFinish() {

 }

 //Registers when the Senerario is enabled
 @EventHandler
 public void onPlayerMove(PlayerMoveEvent e) {
  Player player = e.getPlayer();
  player.sendMessage("Hey " + someNumberToEdit);
 }
}
```

Then simply go to the games.bevs.minecraftbut.MinecraftButPlugin class
```java
// and add the following line in games.bevs.minecraftbut.MinecraftButPlugin
private void populateScenerarios(ButWorld butWorld)
{
  ...
  this.scenerarioManager.registerSenerario(new DummyExample(butWorld));
}
```
You can find more examples within [games.bevs.minecraftbut.senerario.senerarios](https://github.com/HeathLoganCampbell/MinecraftBut/tree/master/src/main/java/games/bevs/minecraftbut/senerario/senerarios)


## Looking for a specific release?
[Releases](https://github.com/HeathLoganCampbell/MinecraftBut/releases)

## Contributing
If you modify this plugin in any positive way please consider submitting your changes as a pull request. After all, this is open-source.
If you find a bug please submit an issue describing the bug.

## Donate
I don't expect anyone to really donate, but I have put time into this plugin so it would be dope if I made something back from it.
Baked with love by Sprock (Heath)

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=VWG7DUFDRYXDA)
