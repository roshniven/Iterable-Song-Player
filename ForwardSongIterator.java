import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator to play songs in the forward direction from a doubly linked list
 * of songs.
 */
public class ForwardSongIterator implements Iterator<Song> {
  private LinkedNode<Song> next; // reference to the next linked node in a list of nodes

  /**
   * Creates a new iterator which iterates through songs in front/head to back/tail order.
   * 
   * @param first reference to the head of a doubly linked list of songs
   */
  public ForwardSongIterator(LinkedNode<Song> first) {
    this.next = first;
  }

  /**
   * Checks whether there are more songs to return.
   * 
   * @return true if there are more songs to return
   */
  @Override
  public boolean hasNext() {
    // check whether there is a next song
    return next != null;
  }

  /**
   * Returns the next song in the iteration.
   * 
   * @return the next song in the iteration
   * @throws NoSuchElementException with a descriptive error message if there are no more songs to
   *                                return in the forward order (meaning if this.hasNext() returns
   *                                false)
   */
  @Override
  public Song next() {
    // throw an exception if there are no more songs to return in the forward order
    if(!this.hasNext()) {
      throw new NoSuchElementException("There no more songs to return in the forward order.");
    }
    // return the current song and update next
    Song nextSong = next.getData();
    next = next.getNext();   
    return nextSong;
  }
}
