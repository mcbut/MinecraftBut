# MinecraftBut
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=VWG7DUFDRYXDA)  
A Minecraft Plugin for youtubers such as BajanCandian, Skeppy, BadBoyHalo, A6D
The youtubers will spawn in a small world with the option to apply multiple different senerarios

## Download
[Download the plugin](https://github.com/HeathLoganCampbell/MinecraftBut/releases/tag/1.3)

## Versions
Mainly we support 1.8 & 1.14, versions in between are up in the air in terms of bugginess

## Videos
[Skeppy's video, Lava is rising - 558,119 views](https://www.youtube.com/watch?v=0dvbqzXiA_o)    
[Skeppy's video, Water is rising - 1,113,071 views](https://www.youtube.com/watch?v=Kcul2iXfPDY)   
[Wilbur Soot's video, Minecraft, But I Leave a Trail of Bedrock - 972,845 views](https://www.youtube.com/watch?v=Kcul2iXfPDY)


## Senerario
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

## Permissions

```
minecraftbut.command.minecraftbut
```
We only have one permission for the root command

## Commands
```
/MinecraftBut
/mb
```
Open's the Menu to enable and disable Senerarios

```
/MinecraftBut help
/mb help
```
help for top level command

```
/MinecraftBut Senerario <senerarioId> help
/mb s <senerarioId> help
```
learn ways that you can configure a Senerario

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
Change the world that the plugin is running on, Use case is just for multiverse support

Note: I will not support the plugin running on multiple worlds at once, as it's out of the scope of common need of the youtubers


## Developers
### Add a Senerario
Simply extend Senerario, like so
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


## Download
[Download the plugin](https://github.com/HeathLoganCampbell/MinecraftBut/releases/tag/1.3)


## Donate
I don't expect anyone to really donate, but I have put time into this plugin. 
So it would be dope if I made something back from it  

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=VWG7DUFDRYXDA)
