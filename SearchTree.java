public interface SearchTree<E> {
    /**
     * Inserts item where it belongs in the tree. Ignores duplicates.
     * @param item  a reference to the data item to add
     * @return true if added, and false if duplicate
     */
    void add(E item);
    
    /**
     * Searches for the specified item
     * @param target    The item to search for
     */
    boolean contains(E target);
    
    /**
     * Removes the specified item from the search tree
     * @param target    The item to be removed
     * @return a reference to the removed item, or null if not found
     */
    void remove(E target);
}
