<p>
    <br />
    <img src="https://img.shields.io/badge/Made%20using-Java-red">
    <img src="https://img.shields.io/badge/Made%20Using-Open%20GL%20-yellow">
    <img src="https://img.shields.io/badge/Version-0.4-blue">
    <img src="https://img.shields.io/badge/Platforms-Windows, Linux-lightgrey">
</p>

<br />
<p align="center">
  <h2 align="center">Gprocessing</h2>

  <p align="center">
    Lightweight 2D java game engine
    <br />
    <a href="https://github.com/athaun/Gprocessing/wiki"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">Features</a>
    ·
    <a href="https://github.com/othneildrew/Gprocessing/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Gprocessing/issues">Request Feature</a>
  </p>
</p>


<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
  * [Code samples](#code-samples)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Documentation](#documentation)
* [Milestones](#Milestones)
* [Known issues](#known-issues)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)

<!-- ABOUT THE PROJECT -->
## About The Project

Originally inspired by the Processing 3 graphics library, Gprocessing is easy to use, but also fast.

* Modern OpenGL through the LWJGL 3 library for fast GPU rendering.
* Entity Component System
* Dear ImGui bindings

This is the first game built using Gprocessing, it is a fully functional breakout clone, which I wrote in only three hours.
![screenshot](breakout.png)
<br>

### Built With
* [LWJGL 3](https://www.lwjgl.org/)


## Code Samples
#### BoilerPlate Code:
```java
public class Main extends Scene {
	
	public void awake() {		
		camera = new Camera();
	}

	public void update() {
		background(255, 255, 255); // Sets the clear color
	}
}
```

#### Simple example with sprites
```java
public class Main extends Scene {
	
	GameObject greenRectangle = new GameObject(new Transform(600, 230, 50, 50), 1);
	GameObject mario = new GameObject(new Transform(600, 200, 50, 50), 2);
	
	public void awake() {		
		camera = new Camera();
		
		greenRectangle.addComponent(new SpriteRenderer(new Color(0, 255, 0, 255))); // Creates a new green sprite component
		mario.addComponent(new SpriteRenderer(new Sprite(Assets.getTexture("src/assets/images/marioSprite.png"))));	// Loads the image from the filesystem into a sprite component
	}

	public void update() {
		background(50, 50, 50); // Sets the clear color
	}
}
```
#### Simple example with Dear ImGui
```java
public class Main extends Scene {
	public void awake() {		
		camera = new Camera();
	}

	public void update() {
		background(50, 50, 50);
	}

	public void imgui () {
		ImGui.begin("Demo Window");
		ImGui.text("Hello World");
		ImGui.end();
	}
}
```

## Prerequisites
* OpenGL capable graphics card (minimum `core 330`)
* OpenGL capable graphics driver
  * Linux nouveau drivers for nvidia cards do not currently work, you will have to install proprietary drivers.
  * FOSS AMD Drivers for linux do work.
  
## Installation
You can find additional instructions in the [Wiki](https://github.com/athaun/Gprocessing/wiki/Setting-up-a-new-project.).

Clone:
`git clone https://github.com/athaun/Gprocessing.git`
open Eclipse, and select import project.
* Eclipse:
  Select the run icon > Run As > 1 java application
  
* Intellj:
  you might have to provide a VM option in the build configuration
  `-Dimgui.library.path=libary/LibImGuiBinaries`

## Documentation
* [Github Wiki](https://github.com/athaun/Gprocessing/wiki) (Work in progress)
* [Javadocs]() (Coming soon)

### Milestones:
```
+ 5.1.2020 Started the project
+ 6.2.2020 begin convert to modern OpenGL
+ 6.23.2020 Entity Component System
+ 8.14.2020 Added ImGui
+ 9.21.2020 Fixed imGui input callbacks
! 9.21.2020 Created First game using the Engine!
```

### Known Issues
```
+ Dynamic window resizing doesn't work correctly
+ Transperancy issues on windows
```

### License
Copyright (c) 2020 Asher Haun MIT License
See [LICENSE](https://github.com/athaun/Gprocessing/blob/master/LICENSE.md) for more information.

### Contact
Discord: `Asher#6411`

### Credits
* Games With Gabe on Youtube for his [amazing tutorial series](https://www.youtube.com/channel/UCQP4qSCj1eHMHisDDR4iPzw/videos) on building a java game engine! 
* Java ImGui bindings from [SpaiR/imgui-java](https://github.com/SpaiR/imgui-java)
