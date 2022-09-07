@Playlist
Feature:As a registered user
        I want to create a playlist
        so that I can add new songs and have a playlist to listen and share with anyone

  Background:
    Given User is authenticated
    And User is the owner of the account

  @CreatePlaylist
  Scenario Outline: Create a new playlist
    When User creates a new playlist with <Playlist Name>,<Public> and <Collaborative>
    Then A new playlist is created

    Examples:
      |Playlist Name |Public|Collaborative|
      |Rock Playlist |true  |false        |
      |Pop Playlist  |false |true         |
      |Salsa Playlist|false |false        |

  @AddTrack
  Scenario Outline: Add track songs to a playlist
    Given User has a created playlist
    When User add a new track to the playlist with <Song Name> and <Track uri>
    Then A new track is added to the playlist

    Examples:
     |Song Name          |Track uri                           |
     |Enter Sandman      |spotify:track:5sICkBXVmaCQk5aISGR3x1|
     |Gasolina           |spotify:track:228BxWXUYQPJrJYHDLOHkj|
     |Por Tu Maldito Amor|spotify:track:4pFrn7MBtpwXlsyO5CbCj2|

