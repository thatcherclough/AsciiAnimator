# AsciiAnimator
AsciiAnimator is a stop motion ASCII art animator.

## Features
AsciiAnimator uses plain text files and stop motion to animate ASCII art frame by frame.

See [syntax](https://github.com/ThatcherDev/AsciiAnimator#syntax) for animation file syntax.

## Demo
<a href="https://asciinema.org/a/wZ8jpm9NfuRTgC8pz8bwTYpD1" target="_blank"><img src="https://asciinema.org/a/wZ8jpm9NfuRTgC8pz8bwTYpD1.svg" width="600"/></a>

Note: the cursor flickering was not present in real-time.

## Requirements
- A Java JDK distribution >=8 must be installed and added to PATH.

## Compatibility
AsciiAnimator is compatible with Windows, Mac, and Linux.

## Installation
```
# clone AsciiAnimator
git clone https://github.com/ThatcherDev/AsciiAnimator.git

# change the working directory to AsciiAnimator
cd AsciiAnimator

# build AsciiAnimator with Maven
# for Windows run
mvnw.cmd clean package

# for Linux run
chmod +x mvnw
./mvnw clean package

# for Mac run
sh mvnw clean package
```

Alternatively, you can download the jar from the [release page](https://github.com/ThatcherDev/AsciiAnimator/releases).

## Usage
```
java -jar asciianimator.jar
AsciiAnimator: A stop motion ASCII art animator (1.0.0)

Usage:
	java -jar asciianimator.jar [-h] [-v] [-f FILE -l BOOLEAN -fps INTEGER]

Arguments:
	-h, --help			Display this message.
	-v, --version			Display current version.
	-f, --file			Specify file to use for animation. (See README.md for syntax)
	-l, --loop			Specify if the animation should loop. (Set to false by default)
	-fps, --frames-per-second	Specify FPS for animation. (Must be an integer greater than 0)

Note: When running, CTRL + C can be used to terminate.
```

## Syntax
Any plain text file can be used with AsciiAnimator.
This file must contain each frame of the animation.
These frames must be followed by "[frame]" to indicate the end of the frame.
Below is an example of 2 frames:
```
     __
    (  )
     ||
     ||
 ___|""|__.._
/____________\
\____________/~~~.
[frame]
        __
       (  )
       //
      //
 ___|""|__.._
/____________\
\____________/~~~.
[frame]
```
An example of a valid animation file can be found in src/main/resources/.

## License
- [MIT](https://choosealicense.com/licenses/mit/)
- Copyright 2019 ©️ ThatcherDev.
