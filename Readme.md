# DiscordBot - GLaDOS

This is a bot for my own private Server

## Getting Started

To run the bot you have to execute the .jar file in ./out/artifacts/DiscrodBot2/DiscordBot2.jar
Will only work on my Server unless you change the Tokens to your Bot Token.
Please dont use mine.

### Prerequisites

Of course you will need java

```
Give examples
```

### Commands

```
Prefix: "!"
```

|   Category    |  Command   | Description  |   Example   |
|---------------|------------|--------------|-------------|
| * Utility       |  help/h:   |Gives a List of Commands plus their description, you can execute with this Bot| !help | 
|		|   ping:    |Testing if the Bot is working| !ping |
|               |   list:    |gives a List of Users viewing the current channel, also just for tests| !list |
|               |   poke:    |Will send a message to a User in Private Chat to wake him up| !poke @user msg |
|               |   clear:   |Will clear a given amount of Messages in the current Channel, (Admin use only)| !clear [int] |
|               |   chat:    |Can restrict Channel Writing for Admin, Mod & Mitlgied, (Admin use only) restricted on Channels "meme"  & "nsfw"| !chat [ban/free] |
|* Music        |  p/play:   | Will play a Song from a given link, can also search for YT videos | !m p url/query|
|               |[user short] + [sound]: | you can specify to wich user the Bot will join (p will not work after this), after this you can play a sound from the soundboard see, [Sondboard](https://helferfa.bplaced.net/Discord/index.html) for Optios| !m f wtf |
|               |  skip/s:   | Skips the current Song/Track | !m skip |
|               |  stop: 

```
music: Music Command Arguments: p/play: Will play a Song from a given link, can also search for vids on YT
				"user short" you can specify for which User the Bot play the Song (p will not work after that), 
					you can continue with playing a sound from the soundboard, see [Discord Soundboard](https://helferfa.bplaced.net/Discord/index.htnl) for Options
				skip/s: Skips the current Song/Track
				stop: Stops Playing, and leaves Channel
				quit: Stronger Stop, Use if Bot gets stuck/broken
				shuffle: Shuffles all Songs in current Playlist
				now/info: Gives a Information about the current Track
				queue: Shows whats in the curretn Queue
	Usage: !m [args1] [args2]
```
* Entertainment:
```
image:  Image Api Arguments: 500px [topic]: Will give you a Image from [500px](https://500px.com) Topic selection isnt implemented yet
			     Pinterest: Gives a image from a specific Pinterest Board
			     "NSFW"
			     boobs: Uses Russian Website Api to give you some nudes [oboobs](https://obutts.ru)
			     butts: Uses Russian Website Api to give you some nudes [obutts](https://obutts.ru)
LoL: gives you informations about a League of Legends Profile

```


## Test

| Day     | Meal    | Price |
| --------|---------|-------|
| Monday  | pasta   | $6    |
| Tuesday | chicken | $8    |

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **freshdumb** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the ... - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc
