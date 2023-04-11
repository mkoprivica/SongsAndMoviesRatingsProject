package ratings;

import ratings.datastructures.BST;
import ratings.datastructures.BinaryTreeNode;
import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;

public class Playlist {

    private BST<Song> bst;
    private LinkedListNode<Song> SongList = null;

    public Playlist(Comparator<Song> comparator){
        bst = new BST<>(comparator);
    }
    public void addSong(Song song){
        bst.insert(song);
    }

    public BinaryTreeNode<Song> getSongTree() {
        return bst.getRoot();
    }

    public LinkedListNode<Song> getSongList(BinaryTreeNode<Song> node){
        if(node != null){
            getSongList(node.getRight());
            SongList = new LinkedListNode<>(node.getValue(),SongList);
            getSongList(node.getLeft());
        }
        return SongList;
    }

    public LinkedListNode<Song> getSongList (){
        return getSongList(getSongTree());
    }
}
