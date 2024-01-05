//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Song
// Course: CS 300 Spring 2022
//
// Author: Roshni Venkat
// Email: rvenkat@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Shraddha Byndoor
// Partner Email: sbyndoor@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models a simple version of a song.
 */
public class Song {
  private String songName; // name or title of the song
  private String artist; // artist of the song
  private String duration; // duration of the song

  /**
   * Creates a new Song given the song name, the name of the artist, and the duration of the song.
   * 
   * @param songName title of the song
   * @param artist   name of the artist who performed this song
   * @param duration duration of the song in the format mm:ss
   * @throws IllegalArgumentException with descriptive error message if either of songName, or
   *                                  artist, or duration is null or is blank, or if the duration is
   *                                  not formatted as mm:ss where both mm and ss are in the 0 .. 59
   *                                  range.
   */
  public Song(java.lang.String songName, java.lang.String artist, java.lang.String duration) {
    // throw an exception if the song name, artist and duration is null
    if (songName == null || artist == null || duration == null) {
      throw new IllegalArgumentException(
          "This song cannot be created as either songName, or artist, or duration is null.");
    }
    // throw an exception if the song name, artist and duration is blank
    if (songName.isBlank() || artist.isBlank() || duration.isBlank()) {
      throw new IllegalArgumentException(
          "This song cannot be created as either songName, or artist, or duration is blank.");
    }
    // Split the string duration with reference to ":" to check the format
    String[] splitDuration = duration.trim().split(":");
    // throw an exception if the duration is not formatted correctly
    if (splitDuration.length != 2) {
      throw new IllegalArgumentException("The duration is not formatted as mm:ss.");
    }
    try {
      int minutes = Integer.parseInt(splitDuration[0]);
      // throw an exception if minutes is not between 0 and 59
      if (minutes < 0 || minutes > 59) {
        throw new IllegalArgumentException("The minutes should be in the 0-59 range.");
      }
      // throw an exception if minutes is not an integer
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The minutes is not parsable to a positive integer.");
    }
    try {
      int seconds = Integer.parseInt(splitDuration[1]);
      // throw an exception if minutes is not between 0 and 59
      if (seconds < 0 || seconds > 59) {
        throw new IllegalArgumentException("The seconds should be in the 0-59 range.");
      }
      // throw an exception if seconds is not an integer
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The seconds is not parsable to a positive integer.");
    }
    this.songName = songName;
    this.artist = artist;
    this.duration = duration;
  }

  /**
   * Gets the title or name of this song.
   * 
   * @return the title or name of this song
   */
  public java.lang.String getSongName() {
    return this.songName;
  }

  /**
   * Gets the name of the artist who performed this song
   * 
   * @return the name of the artist who performed this song
   */
  public java.lang.String getArtist() {
    return this.artist;
  }

  /**
   * Gets the duration of this song
   * 
   * @return the duration of this song
   */
  public java.lang.String getDuration() {
    return this.duration;
  }

  /**
   * Returns a string representation of this song. This string should be formatted as follows
   * "songName---artist--duration" where songName is the title of the song, artist is the name of
   * the artist, and duration is the duration of the song.
   * 
   * @return a string representation of this song
   */
  @Override
  public java.lang.String toString() {
    return this.songName + "---" + this.artist + "---" + this.duration;
  }

  /**
   * Returns true when this song's name and artist strings equals to the other song's name and
   * artist strings, and false otherwise. Note that this method takes an Object rather than a Song
   * argument, so that it Overrides Object.equals(Object). If an object that is not an instance of
   * Song is ever passed to this method, it should return false.
   * 
   * @param other Song object to compare this object to
   * @return true when the this song has matching name and artist with respect to another song (case
   *         insensitive comparison)
   */
  @Override
  public boolean equals(Object other) {
    // check of if the other object is an instance of Song
    if (!(other instanceof Song)) {
      return false;
    }
    Song otherSong = (Song) other;
    // check if song name and artist are equal
    return (this.songName.equalsIgnoreCase(otherSong.getSongName())
        && this.artist.equalsIgnoreCase(otherSong.getArtist()));
  }
}
