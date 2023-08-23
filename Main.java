import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class LRUCache {
    private final int capacity;
    private final HashMap<String, LinkedList<String>> userSongsMap;
    private final Map<String, Integer> songTimestamps;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        userSongsMap = new HashMap<>();
        songTimestamps = new HashMap<>();
    }
    public void playSong(String user, String song) {
        if (!userSongsMap.containsKey(user)) {
            userSongsMap.put(user, new LinkedList<>());
        }
        LinkedList<String> songsList = userSongsMap.get(user);
        if (songsList.contains(song)) {
            songsList.remove(song);
        } else if (songsList.size() >= capacity) {
            String leastRecentSong = songsList.removeFirst();
            songTimestamps.remove(leastRecentSong);
        }
        songsList.add(song);
        songTimestamps.put(song, songsList.size());
    }
    public List<String> getRecentlyPlayed(String user) {
        if (userSongsMap.containsKey(user)) {
            LinkedList<String> songsList = userSongsMap.get(user);
            ArrayList <String> recentlyPlayed = new ArrayList<>(songsList);
            Collections.reverse(recentlyPlayed);
            return recentlyPlayed;
        }
        return new ArrayList<>();
    }
}
public class Main {
    public static void main(String[] args) {
        int capacity = 3;
        LRUCache store = new LRUCache(capacity);

        store.playSong("user1", "S1");
        store.playSong("user1", "S2");
        store.playSong("user1", "S3");
        System.out.println(store.getRecentlyPlayed("user1"));  // Output: [S3, S2, S1]

        store.playSong("user1", "S4");
        System.out.println(store.getRecentlyPlayed("user1"));  // Output: [S4, S2, S1]

        store.playSong("user1", "S2");
        System.out.println(store.getRecentlyPlayed("user1"));  // Output: [S2, S4, S1]
    }
}
