//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: BackwardSongIterator
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
 * This class models an iterator to play songs in the reverse backward direction from a doubly
 * linked list of songs.
 */
public class BackwardSongIterator implements Iterator<Song>{
  private LinkedNode<Song> next; // reference to the next linked node in a list of nodes

  /**
   * Creates a new iterator which iterates through songs in back/tail to front/head order.
   * 
   * @param last reference to the tail of a doubly linked list of songs
   */
  public BackwardSongIterator(LinkedNode<Song> last) {
    this.next = last;
  }

  /**
   * Checks whether there are more songs to return in the reverse order.
   * 
   * @return true if there are more songs to return in the reverse order
   */
  @Override
  public boolean hasNext() {
    // check whether there is a next song in the reverse order
    return next != null;
  }

  /**
   * Returns the next song in the iteration.
   * 
   * @return the next song in the iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more songs to
   *                                return in the reverse order (meaning if this.hasNext() returns
   *                                false)
   */
  @Override
  public Song next() {
    // throw an exception if there are no more songs to return in the reverse order
    if(!this.hasNext()) {
      throw new NoSuchElementException("There no more songs to return in the reverse order.");
    }
    // return the current song and update next
    Song nextSong = next.getData();
    next = next.getPrev();   
    return nextSong;
  }
}
