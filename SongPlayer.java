//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: SongPlayer
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
 * This class models an iterable collection of songs. Songs can be played in the forward or reverse
 * order.
 */
public class SongPlayer implements Iterable<Song> {

  private int size; // size of the list
  private LinkedNode<Song> head; // head of this doubly linked list
  private LinkedNode<Song> tail; // tail of this doubly linked list
  private boolean playingBackward; // true if this song player is reading the list backward

  /**
   * Creates a new instance of song player which contains zero songs and set by default to play
   * songs in the forward direction. [Implementing this constructor is optional since it will be
   * added by default by the compiler]
   */
  public SongPlayer() {
    this.size = 0;
    this.playingBackward = false;
  }

  /**
   * Adds a Song as Last Song
   * 
   * @param oneSong the song that is going to be added to the tail of this doubly linked list of
   *                songs
   */
  public void addLast​(Song oneSong) {
    // create a new node
    LinkedNode<Song> newNode = new LinkedNode<Song>(null, oneSong, null);
    // check if the list is empty
    if (isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      // set the new node's previous pointer to the tail and set the tail to be the new node
      newNode.setPrev​(this.tail);
      this.tail.setNext​(newNode);
      this.tail = newNode;
    }
    this.size++;
  }

  /**
   * Adds a Song as First Song
   * 
   * @param oneSong the song that is going to be added to the head of this doubly linked list of
   *                songs
   * @throws NullPointerException with a descriptive error message if the passed oneSong is null
   */
  public void addFirst​(Song oneSong) {
    // throw an exception if the song is null
    if (oneSong == null) {
      throw new NullPointerException("The passed song oneSong is null.");
    }
    // create a new node
    LinkedNode<Song> newNode = new LinkedNode<Song>(null, oneSong, null);
    if (isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      // set the new node to be the head
      newNode.setNext​(this.head);
      this.head.setPrev​(newNode);
      this.head = newNode;
    }
    this.size++;
  }

  /**
   * adds a Song at a given position/order within this collection of songs
   * 
   * @param index   the given index where the new song will be added
   * @param oneSong the song that is going to be added
   * @throws NullPointerException      with a descriptive error message if the passed oneSong is
   *                                   null
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the 0 ..
   *                                   size() range
   */
  public void add​(int index, Song oneSong) {
    if (oneSong == null) {
      throw new NullPointerException("The passed song oneSong is null.");
    }
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("The index is out of range.");
    }
    LinkedNode<Song> newNode = new LinkedNode<Song>(null, oneSong, null);
    // check if the element has to be added at the beginning
    if (index == 0) {
      addFirst​(oneSong);
      // check if the element has to be added at the end
    } else if (index == this.size) {
      addLast​(oneSong);
    } else {
      // find the right place to put the song to be added
      LinkedNode<Song> current = head;
      // find the node before which the new song has to be inserted
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      // set the new node to point to the next node and the previous node to point to the new node
      newNode.setNext​(current.getNext());
      current.setNext​(newNode);
      // set the new node's previous node and next node
      newNode.setPrev​(current);
      newNode.getNext().setPrev​(newNode);
      this.size++;
    }
  }

  /**
   * Returns the first Song in this list.
   * 
   * @return the Song at the head of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public Song getFirst() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("This list is empty.");
    }
    // return the first Song
    return this.head.getData();
  }

  /**
   * Returns the last Song in this list.
   * 
   * @return the Song at the tail of this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public Song getLast() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("This list is empty.");
    }
    // return the last Song
    return this.tail.getData();
  }

  /**
   * Returns the song at the specified position in this list.
   * 
   * @param index index of the song to return
   * @return the song at the specified position in this list
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the 0 ..
   *                                   size()-1 range
   */
  public Song get​(int index) {
    // throw an exception if the index is out of bounds
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException("The index is out of range.");
    }
    // assign the current node to the head
    LinkedNode<Song> current = this.head;
    int count = 0;
    // use a while loop to find the node that is at the specified index
    while (current != null && count < index) {
      // traverse the linked list
      current = current.getNext();
      count++;
    }
    return current.getData();
  }

  /**
   * Removes and returns the first song from this list.
   * 
   * @return the first song from this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public Song removeFirst() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("This list is empty.");
    }
    LinkedNode<Song> firstSong = this.head;
    // check if the size of the list is 1
    if (this.size == 1) {
      this.clear();
      return firstSong.getData();
    } else {
      // set the head to be the next node and set its previous pointer to null
      this.head = this.head.getNext();
      this.head.setPrev​(null);
      // decrement the size
      this.size--;
      // return the first song
      return firstSong.getData();
    }
  }

  /**
   * Removes and returns the last song from this list.
   * 
   * @return the last song from this list
   * @throws NoSuchElementException with a descriptive error message if this list is empty
   */
  public Song removeLast() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("This list is empty.");
    }
    LinkedNode<Song> lastSong = this.tail;
    // check if the size of the list is 1
    if (this.size == 1) {
      this.clear();
      return lastSong.getData();
    } else {
      // set the tail to be the previous node and set its next pointer to null
      this.tail = this.tail.getPrev();
      this.tail.setNext​(null);
      // decrement the size
      this.size--;
      // return the last song
      return lastSong.getData();
    }
  }

  /**
   * Removes the song at the specified position in this list and returns the song that was removed
   * from the list. The order of precedence of the other songs in the list should not be modified.
   * 
   * @param index the index of the song to be removed
   * @return the song previously at the specified position
   * @throws IndexOutOfBoundsException with a descriptive error message if index is out of the 0 ..
   *                                   size()-1 range
   */
  public Song remove​(int index) {
    // throw an exception if the index is out of bounds
    if (index < 0 || index > this.size - 1) {
      throw new IndexOutOfBoundsException("The index is out of range.");
    }
    Song songRemoved;
    // check if the element to be removed is at the beginning
    if (index == 0) {
      songRemoved = removeFirst();
      // check if the element to be removed is at the end
    } else if (index == this.size - 1) {
      songRemoved = removeLast();
    } else {
      // assign the current node to the head
      LinkedNode<Song> current = this.head;
      int count = 0;
      // use a while loop to find the node that is at the specified index
      while (current != null && count < index) {
        // traverse the linked list
        current = current.getNext();
        count++;
      }
      // set the next pointer of the previous node to the node after the removed node
      current.getPrev().setNext​(current.getNext());
      // set the previous pointer of the next node to the node before the removed node
      current.getNext().setPrev​(current.getPrev());
      // set both the pointers of the removed node to null
      current.setNext​(null);
      current.setPrev​(null);
      // decrement the size
      this.size--;
      // return the song to be removed
      return current.getData();
    }
    return songRemoved;
  }

  /**
   * Returns true if this list contains a match with the specified song. More formally, returns true
   * if and only if this list contains at least one song e such that Objects.equals(o, e).
   * 
   * @param o song whose presence in this list is to be tested
   * @return true if this list contains the specified song
   */
  public boolean contains​(Song o) {
    // set current to be the head of the list
    LinkedNode<Song> current = this.head;
    while (current != null) {
      if (current.getData().equals(o)) {
        return true;
      }
      // update current to be the next node in the list
      current = current.getNext();
    }
    return false;
  }

  /**
   * Removes all of the songs from this list. The list will be empty after this call returns.
   */
  public void clear() {
    // set the head and tail to null and the size to 0
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  /**
   * Returns true if this list is empty.
   * 
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    // the list is empty if its size is 0
    return this.size == 0;
  }

  /**
   * Returns the number of songs in this list.
   * 
   * @return the number of songs in this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns an iterator to iterate through the songs in this list with respect to current playing
   * direction of this song player (either in the forward or in the backward direction).
   * 
   * @return an Iterator to traverse the list of songs in this SongPlayer with respect to the
   *         current playing direction specified by the playingBackward data field
   */
  @Override
  public java.util.Iterator<Song> iterator() {
    // check the current playing direction
    if (this.playingBackward) {
      return new BackwardSongIterator(this.tail);
    } else {
      return new ForwardSongIterator(this.head);
    }
  }

  /**
   * Mutator of the playingDirection of this song player. It switches the playing direction by
   * setting playingBackward to its opposite value.
   */
  public void switchPlayingDirection() {
    this.playingBackward = !this.playingBackward;
  }

  /**
   * Plays the songs in this song player in the current playing direction. This method MUST be
   * implemented using an enhanced for-each loop.
   * 
   * @return a String representation of the songs in this song player. String representations of
   *         each song are separated by a newline. If this song player is empty, this method returns
   *         an empty string.
   */
  public java.lang.String play() {
    String songs = "";
    iterator();
    // use a loop to go through each element of the list
    for (Song oneSong : this) {
      songs += oneSong.toString() + "\n";
    }
    return songs;
  }
}
