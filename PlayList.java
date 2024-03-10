/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if(this.size == this.maxSize)
            return false;
        else{
            tracks[this.size] = track;
            this.size++;
            return true;
        }  
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        //// replace the following statement with your code
        String data = "";
        for(int i=0; i<this.size; i++){
            data += "\n" + tracks[i].toString();
        }
        return data;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if(this.size != 0){
            tracks[this.size-1] = null;
            this.size --;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int total = 0;
        for(int i=0; i<this.size; i++){
            total += tracks[i].getDuration();
        }
        return total;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for(int i=0; i<this.size; i++){
            if(tracks[i].getTitle().toLowerCase().equals(title.toLowerCase()))
              return i;
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if(i < 0 || i > this.size || this.size == this.maxSize)
            return false;
        else if(i == this.size){
            tracks[i] = track;
            this.size ++;
            return true;
        }
        else{
            int index = this.size;
            for(int j=0; j < (this.size) - i; j++){
                tracks[index] = tracks[index-1];
                index --;
            }
            tracks[i] = track;
            this.size ++;
            return true;
        }
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
       if(this.size == 0 || i<0 || i>=this.size)
        System.out.println("-1");
        else if(this.size == 1){
            tracks[i] = null;
            size--;
        }
        else{
            int index = i;
            for(int j=0; j < (this.size) - i - 1; j++){
                tracks[index] = tracks[index+1];
                index++;
            }
            this.size--;
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        for(int i=0; i<this.size; i++){
            if(tracks[i].getTitle().equals(title)){
                remove(i);
            }
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if(this.size != 0){
            remove(0);
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        if(this.size + other.getSize() <= this.maxSize){
            int otherIndex = 0;
            int sizeIndex = this.size; 
            for(int i=0; i<other.getSize(); i++){
                tracks[sizeIndex] = other.getTrack(otherIndex);
                sizeIndex++;
                otherIndex++;
            }
            this.size += other.getSize();
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if(start<0 || start>this.size)
            return -1;
        else{
            int min = tracks[start].getDuration();
            int index = start;
            for(int i=start+1; i<this.size; i++){
                if(tracks[i].getDuration() < min){
                    min = tracks[i].getDuration();
                    index = i;
                }
            }
               return index;
        }
        
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        for(int i=0; i<this.size; i++){
            Track temp = tracks[i];
            int index = minIndex(i);
            tracks[i] = tracks[index];
            tracks[index] = temp;  
        }
    }
}
